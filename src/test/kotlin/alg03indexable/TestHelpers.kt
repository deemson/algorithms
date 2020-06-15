package alg03indexable

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestHelpers {
    @Test
    fun `test orderedArrayDequeOf`() {
        val indexable = indexableArrayDequeOf(1, 2, 3)
        assertEquals(listOf(1, 2, 3), indexable.toList())
    }

    @Test
    fun `test orderedLinkedDequeOf`() {
        val indexable = indexableLinkedDequeOf(1, 2, 3)
        assertEquals(listOf(1, 2, 3), indexable.toList())
    }

    @Test
    fun `test orderedOf`() {
        val indexable = indexableOf(1, 2, 3)
        assertEquals(listOf(1, 2, 3), indexable.toList())
    }
}
