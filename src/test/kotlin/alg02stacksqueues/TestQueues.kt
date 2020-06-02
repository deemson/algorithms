package alg02stacksqueues

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class TestQueues {
    private companion object {
        @JvmStatic
        fun <T> queues() = listOf(arrayQueue<T>(), linkedQueue<T>())
    }

    @ParameterizedTest()
    @MethodSource("queues")
    fun `test it works with strings`(queue: Queue<String>) {
        assertTrue(queue.isEmpty)
        assertEquals(0, queue.size)
        queue.enqueue("hello")
        assertFalse(queue.isEmpty)
        assertEquals(1, queue.size)
        assertEquals("hello", queue.dequeue())
        queue.enqueue("hello")
        queue.enqueue("world")
        assertEquals("hello", queue.dequeue())
        assertEquals("world", queue.dequeue())
        queue.enqueue("hello")
        queue.enqueue("magnificent")
        queue.enqueue("world")
        assertEquals(3, queue.size)
        assertArrayEquals(arrayOf("hello", "magnificent", "world"), queue.toList().toTypedArray())
    }

    @ParameterizedTest()
    @MethodSource("queues")
    fun `test it works with integers`(queue: Queue<Int>) {
        queue.enqueue(42)
        assertEquals(42, queue.dequeue())
        queue.enqueue(42)
        queue.enqueue(100500)
        assertEquals(42, queue.dequeue())
        assertEquals(100500, queue.dequeue())
        queue.enqueue(100500)
        queue.enqueue(42)
        queue.enqueue(13)
        assertArrayEquals(arrayOf(100500, 42, 13), queue.toList().toTypedArray())
    }
}
