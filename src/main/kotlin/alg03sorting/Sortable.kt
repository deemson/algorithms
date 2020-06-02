package alg03sorting

interface Sortable<T> : Iterable<T> {
    val size: Int
    val indices: IntRange
        get() = IntRange(0, this.size - 1)

    operator fun get(index: Int): T
    operator fun set(index: Int, item: T)
}
