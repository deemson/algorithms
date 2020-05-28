package alg02stacksqueues

class TypedArray<T>(size: Int = 2) {
    private var array = arrayOfNulls<Any?>(size)

    val size: Int
        get() = this.array.size

    operator fun get(index: Int): T {
        @Suppress("UNCHECKED_CAST")
        return this.array[index] as T
    }

    operator fun set(index: Int, value: T) {
        this.array[index] = value
    }

    fun releaseReferenceAt(index: Int) {
        this.array[index] = null
    }
}
