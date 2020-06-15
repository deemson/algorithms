package alg04binaryheap

import alg03indexable.indexableOf
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TestAssertBinaryHeap {
    @Test
    fun `test simple`() {
        indexableOf(5, 3, 1).assertBinaryHeap()
    }

    @Test
    fun `test bigger`() {
        indexableOf(42, 13, 19, 5, 7, 15, 3).assertBinaryHeap()
    }

    @Test
    fun `test violation`() {
        val e = assertThrows(IllegalStateException::class.java) {
            indexableOf(42, 5, 3, 1, 42).assertBinaryHeap()
        }
        assertNotEquals(null, e.message)
        assertTrue("value 5 at index 1 violates binary heap order with a child 42 at index 4" in e.message!!)
    }

    @Test
    fun `test sub simple`() {
        indexableOf(42, 5, 3, 1, 42).assertBinaryHeap(1, 3)
    }
}
