package alg02deques

interface Deque<T> : Iterable<T> {
    val size: Int
    val isEmpty: Boolean
        get() = this.size == 0

    operator fun get(index: Int): T
    operator fun set(index: Int, item: T)

    fun addFirst(item: T)
    fun addLast(item: T)
    fun removeFirst(): T
    fun removeLast(): T
}
