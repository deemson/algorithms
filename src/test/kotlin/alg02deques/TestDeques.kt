package alg02deques

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
        assertEquals(listOf(1, 2, 3), deque.toList())
    }

    @ParameterizedTest()
    @MethodSource("deques")
    fun `test grow by adding first`(deque: Deque<Int>) {
        assertTrue(deque.isEmpty)
        deque.addFirst(1)
        deque.addFirst(2)
        deque.addFirst(3)
        assertEquals(3, deque.size)
        assertEquals(listOf(3, 2, 1), deque.toList())
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
        assertEquals(listOf(3, 2), deque.toList())
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

    @ParameterizedTest()
    @MethodSource("deques")
    fun `test indices`(deque: Deque<Int>) {
        val array = arrayOf(42, 13, 100500)
        for (value in array) {
            deque.addLast(value)
        }
        for (index in deque.indices) {
            assertEquals(array[index], deque[index])
        }
    }

    @ParameterizedTest()
    @MethodSource("deques")
    fun `test addAt and removeAt`(deque: Deque<Int>) {
        deque.addAt(0, 42)
        assertEquals(listOf(42), deque.toList())
        deque.addAt(1, 13)
        assertEquals(listOf(42, 13), deque.toList())
        deque.addAt(1, 100500)
        assertEquals(listOf(42, 100500, 13), deque.toList())
        deque.addAt(2, 7)
        assertEquals(listOf(42, 100500, 7, 13), deque.toList())
        deque.addAt(2, 123)
        assertEquals(listOf(42, 100500, 123, 7, 13), deque.toList())
        assertEquals(100500, deque.removeAt(1))
        assertEquals(listOf(42, 123, 7, 13), deque.toList())
        assertEquals(7, deque.removeAt(2))
        assertEquals(listOf(42, 123, 13), deque.toList())
        assertEquals(13, deque.removeAt(2))
        assertEquals(listOf(42, 123), deque.toList())
        assertEquals(42, deque.removeAt(0))
        assertEquals(listOf(123), deque.toList())
    }

    @ParameterizedTest()
    @MethodSource("deques")
    fun `test addLast, grow, remove then addFirst and removeAt`(deque: Deque<Int>) {
        deque.addLast(1)
        deque.addLast(2)
        deque.addFirst(42)
        deque.addFirst(100500)
        assertEquals(2, deque.removeLast())
        assertEquals(1, deque.removeLast())
        deque.addFirst(13)
        deque.addFirst(7)
        assertEquals(listOf(7, 13, 100500, 42), deque.toList())
        assertEquals(13, deque.removeAt(1))
        assertEquals(listOf(7, 100500, 42), deque.toList())
    }
}
