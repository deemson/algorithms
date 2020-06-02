package alg03sorting

import alg02deques.Deque

class SortableDequeAdapter<T>(private val deque: Deque<T>) : Sortable<T> {
    override val size: Int
        get() = this.deque.size

    override fun get(index: Int): T {
        return deque[index]
    }

    override fun set(index: Int, item: T) {
        deque[index] = item
    }

    override fun iterator(): Iterator<T> {
        return deque.iterator()
    }
}
