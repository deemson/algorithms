package alg02stacksqueues

abstract class AbstractArrayBasedCollection<T> : Collection<T> {
    protected var size = 0
    protected var array = TypedArray<T>()

    override fun size(): Int {
        return size
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    protected open fun resizedArrayContent(): Iterator<T> {
        return this.iterator()
    }

    protected open fun resize(capacity: Int) {
        val newArray = TypedArray<T>(capacity)
        for ((index, item) in this.resizedArrayContent().withIndex()) {
            newArray[index] = item
        }
        this.array = newArray
    }

    private fun growArray() {
        this.resize(this.array.size * 2)
    }

    private fun shrinkArray() {
        this.resize(this.array.size / 2)
    }

    protected fun growArrayIfRequired() {
        if (this.size == this.array.size) {
            growArray()
        }
    }

    protected fun shrinkArrayIfRequired() {
        /*
        If array is quarter full it is shrunk to be half-size.
        It is resized at quarter capacity to avoid "thrashing" (constant resizing)
        when working with half-full array and doing add-remove operations.
        */
        if (this.size > 0 && this.size == this.array.size / 4) {
            shrinkArray()
        }
    }
}
