package alg02stacksqueues

interface Queue<T> : Collection<T> {
    fun enqueue(item: T)
    fun dequeue(): T
}
