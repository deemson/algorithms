package alg03indexable

import alg02deques.Deque

class IndexableDequeAdapter<T>(private val deque: Deque<T>) : AbstractIndexable<T>(), Indexable<T> {
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
