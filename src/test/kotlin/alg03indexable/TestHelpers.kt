package alg03indexable

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestHelpers {
    @Test
    fun `test orderedArrayDequeOf`() {
        val ordered = indexableArrayDequeOf(1, 2, 3)
        assertEquals(listOf(1, 2, 3), ordered.toList())
    }

    @Test
    fun `test orderedLinkedDequeOf`() {
        val ordered = indexableLinkedDequeOf(1, 2, 3)
        assertEquals(listOf(1, 2, 3), ordered.toList())
    }

    @Test
    fun `test orderedOf`() {
        val ordered = indexableOf(1, 2, 3)
        assertEquals(listOf(1, 2, 3), ordered.toList())
    }
}
