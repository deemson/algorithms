package alg02stacksqueues

class ArrayQueue<T> : AbstractArrayBasedCollection<T>(), Queue<T> {
    private var firstIndex = 0
    private var lastIndex = 0

    override fun resize(capacity: Int) {
        super.resize(capacity)
        this.firstIndex = 0
        this.lastIndex = this.size
    }

    override fun enqueue(item: T) {
        this.growArrayIfRequired()
        this.array[this.lastIndex++] = item
        // Wrap-around
        if (this.lastIndex == this.array.size) {
            this.lastIndex = 0
        }
        this.size++
    }

    override fun dequeue(): T {
        val item = this.array[this.firstIndex]
        // Release unused reference
        this.array.releaseReferenceAt(this.firstIndex)
        this.size--
        this.firstIndex++
        // Wrap-around
        if (this.firstIndex == this.array.size) {
            this.firstIndex = 0
        }
        this.shrinkArrayIfRequired()
        return item
    }

    private inner class ArrayQueueIterator : Iterator<T> {
        private var index = 0

        override fun hasNext(): Boolean {
            return this.index < size
        }

        override fun next(): T {
            val item = array[(firstIndex + this.index).rem(array.size)]
            this.index++
            return item
        }
    }

    override fun iterator(): Iterator<T> {
        return ArrayQueueIterator()
    }
}
