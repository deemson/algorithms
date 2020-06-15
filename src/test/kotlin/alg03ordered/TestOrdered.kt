package alg03ordered

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class TestOrdered {
    @Test
    fun `test equals`() {
        val ordered = orderedOf(1, 2, 3)
        assertEquals(ordered, ordered)
        assertEquals(OrderedArrayAdapter(arrayOf(1, 2, 3)), ordered)
        assertNotEquals(orderedOf(1, 2), ordered)
        assertNotEquals(orderedOf(1, 2, 5), ordered)
    }
}
