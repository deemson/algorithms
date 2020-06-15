package alg04binaryheap

import alg03comparators.MinComparator
import alg03indexable.indexableOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class TestSink {
    @Test
    fun `test simple`() {
        val indexable = indexableOf(1, 3, 5)
        indexable.sink()
        assertEquals(listOf(5, 3, 1), indexable.toList())
        indexable.assertBinaryHeap()
    }

    @Test
    fun `test simple min`() {
        val indexable = indexableOf(5, 3, 1)
        indexable.sink(MinComparator())
        assertEquals(listOf(1, 3, 5), indexable.toList())
        indexable.assertBinaryHeap(MinComparator())
    }

    @Test
    fun `test at`() {
        val indexable = indexableOf(1, 3, 5, 6, 4)
        indexable.sink(1)
        assertEquals(listOf(1, 6, 5, 3, 4), indexable.toList())
        indexable.sink()
        assertEquals(listOf(6, 4, 5, 3, 1), indexable.toList())
        indexable.assertBinaryHeap()
    }

    @Test
    fun `test sink empty indexable`() {
        val indexable = indexableOf<Int>()
        val e = assertThrows(IllegalStateException::class.java) {
            indexable.sink()
        }
        assertEquals("indexable is empty", e.message!!)
    }

    @Test
    fun `test sink crossed indexes`() {
        val indexable = indexableOf(1, 3, 5)
        val e = assertThrows(IllegalStateException::class.java) {
            indexable.sink(2, 1)
        }
        assertEquals("fromIndex must be smaller than downToIndex (got 2 < 1)", e.message!!)
    }

    @Test
    fun `test sink from negative`() {
        val indexable = indexableOf(1, 3, 5)
        val e = assertThrows(IllegalStateException::class.java) {
            indexable.sink(-1)
        }
        assertEquals("from index must be positive (got -1)", e.message!!)
    }

    @Test
    fun `test sink down to out of bounds`() {
        val indexable = indexableOf(1, 3, 5)
        val e = assertThrows(IllegalStateException::class.java) {
            indexable.sink(1, 4)
        }
        assertEquals("to index must be smaller than indexable size (got 4 < 3)", e.message!!)
    }

    @Test
    fun `test not able to sink lower than second child`() {
        val indexable = indexableOf(1, 1, 1)
        indexable.sink()
        assertEquals(listOf(1, 1, 1), indexable.toList())
    }

    @Test
    fun `test sink single element`() {
        val indexable = indexableOf(1)
        indexable.sink()
        assertEquals(listOf(1), indexable.toList())
    }
}
