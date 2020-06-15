package alg04priorityqueues

import alg02deques.ArrayDeque
import alg02deques.swap
import alg03comparators.MaxComparator
import alg03comparators.MinComparator

/**
 * Basic PQ implementation
 * Scales as O(1) for inserts and as O(N) for deletes
 */
class PQ1UnorderedPriorityQueue<T>(private val comparator: Comparator<T>) : PriorityQueue<T> {
    private val deque = ArrayDeque<T>()

    companion object {
        operator fun <T : Comparable<T>> invoke(): PQ1UnorderedPriorityQueue<T> {
            return max()
        }

        fun <T : Comparable<T>> max(): PQ1UnorderedPriorityQueue<T> {
            return PQ1UnorderedPriorityQueue(MaxComparator())
        }

        fun <T : Comparable<T>> min(): PQ1UnorderedPriorityQueue<T> {
            return PQ1UnorderedPriorityQueue(MinComparator())
        }
    }

    override val size: Int
        get() = this.deque.size

    override fun insert(item: T) {
        this.deque.addLast(item)
    }

    override fun delete(): T {
        var topIndex = 0
        for (index in 1 until this.deque.size) {
            if (this.comparator.compare(this.deque[index], this.deque[topIndex]) < 0) {
                topIndex = index
            }
        }
        this.deque.swap(topIndex, this.deque.size - 1)
        return this.deque.removeLast()
    }
}
