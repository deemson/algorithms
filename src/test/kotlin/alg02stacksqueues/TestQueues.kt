package alg02stacksqueues

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class TestQueues {
    private companion object {
        @JvmStatic
        fun <T> queues() = listOf(ArrayQueue<T>())
    }

    @ParameterizedTest()
    @MethodSource("queues")
    fun `test it works with strings`(queue: ArrayQueue<String>) {
        queue.enqueue("hello")
        assertEquals("hello", queue.dequeue())
        queue.enqueue("hello")
        queue.enqueue("world")
        assertEquals("hello", queue.dequeue())
        assertEquals("world", queue.dequeue())
        queue.enqueue("hello")
        queue.enqueue("magnificent")
        queue.enqueue("world")
        for ((expected, actual) in listOf("hello", "magnificent", "world") zip queue) {
            assertEquals(expected, actual)
        }
    }

    @ParameterizedTest()
    @MethodSource("queues")
    fun `test it works with integers`(queue: ArrayQueue<Int>) {
        queue.enqueue(42)
        assertEquals(42, queue.dequeue())
        queue.enqueue(42)
        queue.enqueue(100500)
        assertEquals(42, queue.dequeue())
        assertEquals(100500, queue.dequeue())
        queue.enqueue(100500)
        queue.enqueue(42)
        queue.enqueue(13)
        for ((expected, actual) in listOf(100500, 42, 13) zip queue) {
            assertEquals(expected, actual)
        }
    }
}
