package alg02stacksqueues

interface Collection<T> : Iterable<T> {
    fun size(): Int
    fun isEmpty(): Boolean
}