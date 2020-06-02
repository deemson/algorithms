package alg02stacksqueues

interface Queue<T> : HasSize, Iterable<T> {
    fun enqueue(item: T)
    fun dequeue(): T
}
