package alg03sorting

import alg02stacksqueues.HasSize

interface Sortable<T> : HasSize, Iterable<T> {
    val indices: IntRange
        get() = IntRange(0, this.size - 1)

    operator fun get(index: Int): T
    operator fun set(index: Int, item: T)
}
