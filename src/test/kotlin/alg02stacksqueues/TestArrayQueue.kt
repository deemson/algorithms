package alg02stacksqueues

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TestArrayQueue {
    @Test
    fun `test growth`() {
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
        // grow happens
        q.enqueue("three")
        assertArrayEquals(arrayOf("one", "two", "three"), q.toList().toTypedArray())
    }

    @Test
    fun `test shrinking`() {
        val q = ArrayQueue<String>()
        q.enqueue("one")
        q.enqueue("two")
        // grow happens
        q.enqueue("three")
        assertEquals("one", q.dequeue())
        // shrink happens
        assertEquals("two", q.dequeue())
        assertEquals(1, q.size())
        assertEquals("three", q.dequeue())
        assertEquals(0, q.size())
        assertTrue(q.isEmpty())
    }
}
