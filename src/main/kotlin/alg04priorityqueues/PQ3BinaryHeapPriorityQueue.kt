package alg04priorityqueues

import alg02deques.ArrayDeque
import alg02deques.swap
import alg03comparators.MaxComparator
import alg03comparators.MinComparator

class PQ3BinaryHeapPriorityQueue<T>(private val comparator: Comparator<T>) : PriorityQueue<T> {
    private val deque = ArrayDeque<T>()

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
        get() = this.deque.size

    /*
    Convenience utility: it's easier to work with binary heap when indexes start at 1
     */
    private fun normalizeIndex(index: Int): Int {
        return index - 1
    }

    private operator fun get(index: Int): T {
        return this.deque[this.normalizeIndex(index)]
    }

    private fun compare(index1: Int, index2: Int): Int {
        return this.comparator.compare(this[index1], this[index2])
    }

    private fun swap(index1: Int, index2: Int) {
        this.deque.swap(this.normalizeIndex(index1), this.normalizeIndex(index2))
    }

    private fun swim() {
        if (this.size == 1) {
            return
        }
        var index = this.size
        while (index > 1 && this.compare(index / 2, index) > 0) {
            this.swap(index, index / 2)
            index /= 2
        }
    }

    private fun sink() {
        if (this.isEmpty) {
            return
        }
        var index = 1
        while (index * 2 <= this.size) {
            var childIndex = index * 2
            if (childIndex < this.size && this.compare(childIndex + 1, childIndex) <= 0) {
                childIndex++
            }
            if (this.compare(index, childIndex) < 0) {
                break
            }
            this.swap(index, childIndex)
            index = childIndex
        }
    }

    /*
    Both insert and delete require at most O(log2(N)) operations as tree's height grows as log2(N)
     */
    override fun insert(item: T) {
        this.deque.addLast(item)
        this.swim()
    }

    override fun delete(): T {
        this.deque.swap(0, this.deque.size - 1)
        val item = this.deque.removeLast()
        this.sink()
        return item
    }
}
