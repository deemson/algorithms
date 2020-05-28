package alg02stacksqueues

interface Stack<T> : Collection<T> {
    fun push(item: T)
    fun pop(): T
}
