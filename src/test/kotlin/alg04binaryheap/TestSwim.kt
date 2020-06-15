package alg04binaryheap

import alg03comparators.MinComparator
import alg03indexable.indexableOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class TestSwim {
    @Test
    fun `test simple`() {
        val indexable = indexableOf(1, 3, 5)
        indexable.swim()
        indexable.assertBinaryHeap()
    }

    @Test
    fun `test simple min`() {
        val indexable = indexableOf(5, 3, 1)
        indexable.swim(MinComparator())
        assertEquals(listOf(1, 3, 5), indexable.toList())
        indexable.assertBinaryHeap(MinComparator())
    }

    @Test
    fun `test at`() {
        val indexable = indexableOf(1, 3, 5, 6, 4)
        indexable.swim(3)
        assertEquals(listOf(6, 1, 5, 3, 4), indexable.toList())
        indexable.swim()
        assertEquals(listOf(6, 4, 5, 3, 1), indexable.toList())
        indexable.assertBinaryHeap()
    }

    @Test
    fun `test swim empty indexable`() {
        val indexable = indexableOf<Int>()
        val e = assertThrows(IllegalStateException::class.java) {
            indexable.swim()
        }
        assertEquals("indexable is empty", e.message!!)
    }

    @Test
    fun `test swim crossed indexes`() {
        val indexable = indexableOf(1, 3, 5)
        val e = assertThrows(IllegalStateException::class.java) {
            indexable.swim(1, 2)
        }
        assertEquals("fromIndex must be smaller than downToIndex (got 2 < 1)", e.message!!)
    }

    @Test
    fun `test swim up to negative`() {
        val indexable = indexableOf(1, 3, 5)
        val e = assertThrows(IllegalStateException::class.java) {
            indexable.swim(1, -1)
        }
        assertEquals("from index must be positive (got -1)", e.message!!)
    }

    @Test
    fun `test swim from out of bounds`() {
        val indexable = indexableOf(1, 3, 5)
        val e = assertThrows(IllegalStateException::class.java) {
            indexable.swim(4)
        }
        assertEquals("to index must be smaller than indexable size (got 4 < 3)", e.message!!)
    }

    @Test
    fun `test swim single element`() {
        val indexable = indexableOf(1)
        indexable.swim()
        assertEquals(listOf(1), indexable.toList())
    }
}
