package alg02stacksqueues

class ArrayQueue<T> : Queue<T> {
    private var firstIndex = 0
    private var lastIndex = 0
    private var size = 0
    private var array = Array<Any?>(2) { null }


    override fun size(): Int {
        return this.size
    }

    override fun isEmpty(): Boolean {
        return this.size == 0
    }

    private fun resize(capacity: Int) {
        val newArray = Array<Any?>(capacity) { null }
        for (index in (0 until this.size)) {
            newArray[index] = this.array[(this.firstIndex + index).rem(this.array.size)]
        }
        this.array = newArray
        this.firstIndex = 0
        this.lastIndex = this.size
    }

    override fun enqueue(item: T) {
        if (this.size == this.array.size) {
            this.resize(capacity = this.array.size * 2)
        }
        this.array[this.lastIndex++] = item
        // Wrap-around
        if (this.lastIndex == this.array.size) {
            this.lastIndex = 0
        }
        this.size++
    }

    override fun dequeue(): T {
        val untypedItem = this.array[this.firstIndex]
        // Release unused pointer
        this.array[this.firstIndex] = null
        this.size--
        this.firstIndex++
        // Wrap-around
        if (this.firstIndex == this.array.size) {
            this.firstIndex = 0
        }
        if (this.size > 0 && this.size == this.array.size / 4) {
            resize(this.array.size / 2)
        }
        @Suppress("UNCHECKED_CAST")
        return untypedItem as T
    }

    private inner class ArrayQueueIterator<T>() : Iterator<T> {
        private var index = 0

        override fun hasNext(): Boolean {
            return this.index > size
        }

        override fun next(): T {
            @Suppress("UNCHECKED_CAST")
            val item = array[(firstIndex + this.index).rem(array.size)] as T
            this.index++
            return item
        }

    }

    override fun iterator(): Iterator<T> {
        return ArrayQueueIterator()
    }

}