package alg03indexable

import alg02deques.Deque

class IndexableDequeAdapter<T>(private val deque: Deque<T>) : Deque<T> by deque, Indexable<T> {
    override val isEmpty: Boolean
        get() = super<Deque>.isEmpty
    override val indices: IntRange
        get() = super<Deque>.indices
}
