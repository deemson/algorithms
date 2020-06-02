package alg02stacksqueues

import alg02deques.Deque

class StackDequeAdapter<T>(private val deque: Deque<T>) : Stack<T> {
    override val size: Int
        get() = this.deque.size

    override fun push(item: T) {
        this.deque.addFirst(item)
    }

    override fun pop(): T {
        return this.deque.removeFirst()
    }

    override fun iterator(): Iterator<T> {
        return this.deque.iterator()
    }
}
