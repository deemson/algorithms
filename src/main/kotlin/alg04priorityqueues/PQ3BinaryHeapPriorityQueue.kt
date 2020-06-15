package alg04priorityqueues

import alg02deques.swap
import alg03comparators.MaxComparator
import alg03comparators.MinComparator
import alg03indexable.indexableArrayDequeOf
import alg04binaryheap.sink
import alg04binaryheap.swim

class PQ3BinaryHeapPriorityQueue<T>(private val comparator: Comparator<T>) : PriorityQueue<T> {
    private val indexable = indexableArrayDequeOf<T>()

    companion object {
        operator fun <T : Comparable<T>> invoke(): PQ3BinaryHeapPriorityQueue<T> {
            return max()
        }

        fun <T : Comparable<T>> max(): PQ3BinaryHeapPriorityQueue<T> {
            return PQ3BinaryHeapPriorityQueue(MaxComparator())
        }

        fun <T : Comparable<T>> min(): PQ3BinaryHeapPriorityQueue<T> {
            return PQ3BinaryHeapPriorityQueue(MinComparator())
        }
    }

    override val size: Int
        get() = this.indexable.size

    /*
    Both insert and delete require at most O(log2(N)) operations as tree's height grows as log2(N)
     */
    override fun insert(item: T) {
        this.indexable.addLast(item)
        if (this.size > 1) {
            this.indexable.swim(this.comparator)
        }
    }

    override fun delete(): T {
        this.indexable.swap(0, this.indexable.size - 1)
        val item = this.indexable.removeLast()
        if (this.size > 1) {
            this.indexable.sink(this.comparator)
        }
        return item
    }
}
