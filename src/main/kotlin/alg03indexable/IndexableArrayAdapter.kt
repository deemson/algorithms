package alg03indexable

class IndexableArrayAdapter<T>(private val array: Array<T>) : Indexable<T> {
    override val size: Int
        get() = this.array.size

    override fun get(index: Int): T {
        return array[index]
    }

    override fun set(index: Int, item: T) {
        array[index] = item
    }

    override fun iterator(): Iterator<T> {
        return array.iterator()
    }
}
