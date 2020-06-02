package alg02deques

class ArrayDeque<T>(capacity: Int = 2) : Deque<T> {
    private object Empty

    private var array = Array<Any?>(capacity) { Empty }
    private var firstItemIndex = 0
    private var lastItemIndex = 0

    private var _size: Int = 0

    override val size: Int
        get() = this._size

    private val capacity: Int
        get() = this.array.size

    private fun normalizeIndex(index: Int): Int {
        if (index < 0 || index >= this.capacity) {
            throw IndexOutOfBoundsException("$index is out of bounds")
        }
        /*
        As first and last item indexes are wrapped around at the end of the array
        we need to normalize indexes that come in range 0..size-1
        to be in range firstItemIndex..lastItemIndex-1
         */
        return (this.firstItemIndex + index).rem(this.capacity)
    }

    override operator fun get(index: Int): T {
        @Suppress("UNCHECKED_CAST")
        return this.array[normalizeIndex(index)] as T
    }

    override operator fun set(index: Int, item: T) {
        this.array[normalizeIndex(index)] = item
    }

    private fun resize(capacity: Int) {
        val array = Array<Any?>(capacity) { Empty }
        for (index in 0 until this._size) {
            array[index] = this[index]
        }
        this.array = array
        this.firstItemIndex = 0
        this.lastItemIndex = this._size
    }

    private fun growIfRequired() {
        if (this._size == this.capacity) {
            this.resize(this.capacity * 2)
        }
    }

    private fun shrinkIfRequired() {
        /*
        If array is quarter full it is shrunk to be half-size.
        It is resized at quarter capacity to avoid "thrashing" (constant resizing)
        when working with half-full array and doing add-remove operations.
        */
        if (this._size > 0 && this._size == this.capacity / 4) {
            this.resize(this.capacity / 2)
        }
    }

    override fun addFirst(item: T) {
        this.growIfRequired()
        this.firstItemIndex--
        if (this.firstItemIndex < 0) {
            this.firstItemIndex = this.capacity - 1
        }
        this.array[this.firstItemIndex] = item
        this._size++
    }

    override fun addLast(item: T) {
        this.growIfRequired()
        this.array[this.lastItemIndex] = item
        this.lastItemIndex++
        if (this.lastItemIndex == this.capacity) {
            this.lastItemIndex = 0
        }
        this._size++
    }

    override fun removeFirst(): T {
        val item = this[0]
        this.firstItemIndex++
        if (this.firstItemIndex == this.capacity) {
            this.firstItemIndex = 0
        }
        this._size--
        this.shrinkIfRequired()
        return item
    }

    override fun removeLast(): T {
        val item = this[this._size - 1]
        this.lastItemIndex--
        if (this.lastItemIndex < 0) {
            this.lastItemIndex = this.capacity - 1
        }
        this._size--
        this.shrinkIfRequired()
        return item
    }

    private inner class ArrayDequeueIterator : Iterator<T> {
        private var index = 0

        override fun hasNext(): Boolean {
            return this.index < this@ArrayDeque._size
        }

        override fun next(): T {
            return this@ArrayDeque[this.index++]
        }
    }

    override fun iterator(): Iterator<T> {
        return ArrayDequeueIterator()
    }
}
