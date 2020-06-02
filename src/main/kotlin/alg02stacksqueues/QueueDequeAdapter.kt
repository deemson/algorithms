package alg02stacksqueues

import alg02deques.Deque

class QueueDequeAdapter<T>(private val deque: Deque<T>) : Queue<T> {
    override val size: Int
        get() = this.deque.size

    override fun enqueue(item: T) {
        this.deque.addLast(item)
    }

    override fun dequeue(): T {
        return this.deque.removeFirst()
    }

    override fun iterator(): Iterator<T> {
        return this.deque.iterator()
    }
}
