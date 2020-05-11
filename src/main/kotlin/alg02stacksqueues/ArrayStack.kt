package alg02stacksqueues

class ArrayStack<T> : Stack<T> {
    private var size = 0
    private var array = Array<Any?>(2) { null }

    override fun size(): Int {
        return size
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    private fun resize(capacity: Int) {
        val newArray = Array<Any?>(capacity) { null }
        for (index in (0 until this.size)) {
            newArray[index] = this.array[index]
        }
        this.array = newArray
    }

    override fun push(item: T) {
        if (this.size == this.array.size) {
            this.resize(capacity = this.array.size * 2)
        }
        this.array[this.size++] = item
    }

    override fun pop(): T {
        val untypedItem = this.array[this.size - 1]
        // Release unused pointer
        this.array[this.size - 1] = null
        this.size--
        if (this.size > 0 && this.size == this.array.size / 4) {
            resize(this.array.size / 2)
        }
        @Suppress("UNCHECKED_CAST")
        return untypedItem as T
    }

    private inner class ArrayStackIterator<T>(private var index: Int = 0) : Iterator<T> {
        override fun hasNext(): Boolean {
            return this.index > 0
        }

        override fun next(): T {
            @Suppress("UNCHECKED_CAST")
            return array[this.index--] as T
        }

    }

    override fun iterator(): Iterator<T> {
        return ArrayStackIterator()
    }

}