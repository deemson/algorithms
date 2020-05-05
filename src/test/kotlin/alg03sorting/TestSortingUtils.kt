package alg03sorting

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class TestSortingUtils {
    @Test
    fun `exchange two elements array`() {
        val array = arrayOf(42, 123)
        exchange(array, 0, 1)
        assertArrayEquals(arrayOf(123, 42), array)
    }

    @Test
    fun `exchange three elements array`() {
        val array = arrayOf(17, 42, 123)
        exchange(array, 0, 2)
        exchange(array, 1, 2)
        assertArrayEquals(arrayOf(123, 17, 42), array)
    }

}