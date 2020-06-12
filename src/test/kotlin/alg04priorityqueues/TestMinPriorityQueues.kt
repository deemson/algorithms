package alg04priorityqueues

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class TestMinPriorityQueues {
    private companion object {
        @JvmStatic
        fun <T : Comparable<T>> priorityQueues() = listOf(
            PQ1UnorderedPriorityQueue.min<T>(),
            PQ2OrderedPriorityQueue.min<T>(),
            PQ3BinaryHeapPriorityQueue.min<T>()
        )
    }

    @ParameterizedTest()
    @MethodSource("priorityQueues")
    fun `test it works`(priorityQueue: PriorityQueue<Int>) {
        assertTrue(priorityQueue.isEmpty)
        assertEquals(0, priorityQueue.size)
        priorityQueue.insert(42)
        priorityQueue.insert(100500)
        priorityQueue.insert(13)
        priorityQueue.insert(1)
        priorityQueue.insert(5)
        assertEquals(1, priorityQueue.delete())
        assertEquals(5, priorityQueue.delete())
        assertEquals(13, priorityQueue.delete())
        assertEquals(42, priorityQueue.delete())
        assertEquals(100500, priorityQueue.delete())
    }
}
