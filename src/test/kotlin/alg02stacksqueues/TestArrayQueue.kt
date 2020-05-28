package alg02stacksqueues

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestArrayQueue {
    @Test
    fun `test resizing`() {
        // first at 0 last at 0 size is 0
        val q = ArrayQueue<String>()
        // first at 0 last at 1 size is 1
        q.enqueue("test")
        // first at 1 last at 1 size is 0
        assertEquals("test", q.dequeue())
        // first at 1 last at 0 size is 1
        q.enqueue("one")
        // first at 1 last at 1 size is 2
        q.enqueue("two")
        // resizing happens
        q.enqueue("three")
        assertArrayEquals(arrayOf("one", "two", "three"), q.toList().toTypedArray())
    }
}
