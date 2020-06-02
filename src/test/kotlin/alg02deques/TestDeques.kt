package alg02deques

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
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

    @ParameterizedTest()
    @MethodSource("deques")
    fun `test setting values and out of bounds exception`(deque: Deque<Int>) {
        assertTrue(deque.isEmpty)
        deque.addFirst(42)
        assertFalse(deque.isEmpty)
        assertEquals(42, deque[0])
        deque[0] = 123
        assertEquals(123, deque[0])
        assertThrows(IndexOutOfBoundsException::class.java) {
            deque[1]
        }
    }

    @ParameterizedTest()
    @MethodSource("deques")
    fun `test removing from empty deque`(deque: Deque<Int>) {
        assertThrows(IllegalStateException::class.java) {
            deque.removeFirst()
        }
    }

    @ParameterizedTest()
    @MethodSource("deques")
    fun `test adding first removing last`(deque: Deque<Int>) {
        deque.addFirst(42)
        assertEquals(42, deque.removeLast())
    }
}
