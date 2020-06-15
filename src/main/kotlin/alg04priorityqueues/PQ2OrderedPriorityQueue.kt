package alg04priorityqueues

import alg02deques.ArrayDeque
import alg02deques.swap
import alg03comparators.MaxComparator
import alg03comparators.MinComparator

/**
 * Another basic PQ implementation
 * Scales as O(N) for inserts and as O(1) for deletes
 */
class PQ2OrderedPriorityQueue<T>(private val comparator: Comparator<T>) : PriorityQueue<T> {
    private val deque = ArrayDeque<T>()

    companion object {
        operator fun <T : Comparable<T>> invoke(): PQ2OrderedPriorityQueue<T> {
            return max()
        }

        fun <T : Comparable<T>> max(): PQ2OrderedPriorityQueue<T> {
            return PQ2OrderedPriorityQueue(MaxComparator())
        }

        fun <T : Comparable<T>> min(): PQ2OrderedPriorityQueue<T> {
            return PQ2OrderedPriorityQueue(MinComparator())
        }
    }

    override val size: Int
        get() = this.deque.size

    override fun insert(item: T) {
        this.deque.addLast(item)
        for (index in this.size - 2 downTo 0) {
            if (this.comparator.compare(this.deque[index], this.deque[index + 1]) >= 0) {
                break
            }
            this.deque.swap(index, index + 1)
        }
    }

    override fun delete(): T {
        return this.deque.removeLast()
    }
}
