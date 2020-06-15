package alg03ordered

import alg02stacksqueues.HasSize

interface Ordered<T> : HasSize, Iterable<T> {
    val indices: IntRange
        get() = IntRange(0, this.size - 1)

    operator fun get(index: Int): T
    operator fun set(index: Int, item: T)
}
