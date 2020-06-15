package alg03indexable

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class TestIndexable {
    @Test
    fun `test equals`() {
        val ordered = orderedOf(1, 2, 3)
        assertEquals(ordered, ordered)
        assertEquals(IndexableArrayAdapter(arrayOf(1, 2, 3)), ordered)
        assertNotEquals(orderedOf(1, 2), ordered)
        assertNotEquals(orderedOf(1, 2, 5), ordered)
    }
}
