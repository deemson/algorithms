package alg02deques

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestHelpers {
    @Test
    fun `test arrayDequeOf`() {
        val actual = arrayDequeOf(1, 2, 3)
        val expected = ArrayDeque<Int>()
        for (item in arrayOf(1, 2, 3)) {
            expected.addLast(item)
        }
        assertEquals(expected, actual)
    }

    @Test
    fun `test linkedDequeOf`() {
        val actual = linkedDequeOf(1, 2, 3)
        val expected = LinkedDeque<Int>()
        for (item in arrayOf(1, 2, 3)) {
            expected.addLast(item)
        }
        assertEquals(expected, actual)
    }

    @Test
    fun `test dequeOf`() {
        val actual = dequeOf("hello", "world")
        val expected = LinkedDeque<String>()
        for (item in arrayOf("hello", "world")) {
            expected.addLast(item)
        }
        assertEquals(expected, actual)
    }
}
