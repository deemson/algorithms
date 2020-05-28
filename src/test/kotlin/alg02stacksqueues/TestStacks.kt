package alg02stacksqueues

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class TestStacks {
    private companion object {
        @JvmStatic
        fun <T> stacks() = listOf(ArrayStack<T>())
    }

    @ParameterizedTest()
    @MethodSource("stacks")
    fun `test it works with strings`(stack: ArrayStack<String>) {
        stack.push("hello")
        assertEquals("hello", stack.pop())
        stack.push("world")
        stack.push("hello")
        assertEquals("hello", stack.pop())
        assertEquals("world", stack.pop())
        stack.push("world")
        stack.push("magnificent")
        stack.push("hello")
        assertArrayEquals(arrayOf("hello", "magnificent", "world"), stack.toList().toTypedArray())
    }

    @ParameterizedTest()
    @MethodSource("stacks")
    fun `test it works with integers`(stack: ArrayStack<Int>) {
        stack.push(42)
        assertEquals(42, stack.pop())
        stack.push(100500)
        stack.push(42)
        assertEquals(42, stack.pop())
        assertEquals(100500, stack.pop())
        stack.push(13)
        stack.push(42)
        stack.push(100500)
        assertArrayEquals(arrayOf(100500, 42, 13), stack.toList().toTypedArray())
    }
}
