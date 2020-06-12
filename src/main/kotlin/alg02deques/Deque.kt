package alg02deques

interface Deque<T> : Iterable<T> {
    val size: Int
    val isEmpty: Boolean
        get() = this.size == 0
    val indices: IntRange
        get() = IntRange(0, this.size - 1)

    operator fun get(index: Int): T
    operator fun set(index: Int, item: T)

    fun addFirst(item: T)
    fun addLast(item: T)
    fun addAt(index: Int, item: T)
    fun removeFirst(): T
    fun removeAt(index: Int): T
    fun removeLast(): T
}
