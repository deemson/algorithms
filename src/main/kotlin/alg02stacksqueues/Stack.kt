package alg02stacksqueues

interface Stack<T> : HasSize, Iterable<T> {
    fun push(item: T)
    fun pop(): T
}
