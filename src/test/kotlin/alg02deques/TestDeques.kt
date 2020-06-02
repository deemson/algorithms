package alg02deques

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class TestDeques {
    private companion object {
        @JvmStatic
        fun <T> deques() = listOf(ArrayDeque<T>(), LinkedDeque<T>())
    }

    @ParameterizedTest()
    @MethodSource("deques")
    fun `test index shifts`(deque: Deque<Int>) {
        deque.addFirst(42)
        assertEquals(42, deque[0])
        deque.addLast(123)
        assertEquals(deque[1], 123)
    }

    @ParameterizedTest()
    @MethodSource("deques")
    fun `test grow by adding last`(deque: Deque<Int>) {
        assertTrue(deque.isEmpty)
        deque.addLast(1)
        deque.addLast(2)
        deque.addLast(3)
        assertEquals(3, deque.size)
        assertArrayEquals(arrayOf(1, 2, 3), deque.toList().toTypedArray())
    }

    @ParameterizedTest()
    @MethodSource("deques")
    fun `test grow by adding first`(deque: Deque<Int>) {
        assertTrue(deque.isEmpty)
        deque.addFirst(1)
        deque.addFirst(2)
        deque.addFirst(3)
        assertEquals(3, deque.size)
        assertArrayEquals(arrayOf(3, 2, 1), deque.toList().toTypedArray())
    }

    @ParameterizedTest()
    @MethodSource("deques")
    fun `test shrink by adding last and removing first`(deque: Deque<Int>) {
        assertTrue(deque.isEmpty)
        deque.addLast(1)
        deque.addLast(2)
        deque.addLast(3)
        assertEquals(3, deque.size)
        assertEquals(1, deque.removeFirst())
        assertEquals(2, deque.removeFirst())
        assertEquals(1, deque.size)
        assertEquals(3, deque.removeFirst())
    }

    @ParameterizedTest()
    @MethodSource("deques")
    fun `test shrink by adding first and removing last`(deque: Deque<Int>) {
        assertTrue(deque.isEmpty)
        deque.addFirst(1)
        deque.addFirst(2)
        deque.addFirst(3)
        assertEquals(3, deque.size)
        assertEquals(1, deque.removeLast())
        assertEquals(2, deque.removeLast())
        assertEquals(1, deque.size)
        assertEquals(3, deque.removeLast())
    }
}
