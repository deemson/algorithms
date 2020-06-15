package alg03indexable

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestHelpers {
    @Test
    fun `test orderedArrayDequeOf`() {
        val ordered = orderedArrayDequeOf(1, 2, 3)
        assertEquals(IndexableArrayAdapter(arrayOf(1, 2, 3)), ordered)
    }

    @Test
    fun `test orderedLinkedDequeOf`() {
        val ordered = orderedLinkedDequeOf(1, 2, 3)
        assertEquals(IndexableArrayAdapter(arrayOf(1, 2, 3)), ordered)
    }

    @Test
    fun `test orderedOf`() {
        val ordered = orderedOf(1, 2, 3)
        assertEquals(orderedDequeOf(1, 2, 3), ordered)
    }
}
