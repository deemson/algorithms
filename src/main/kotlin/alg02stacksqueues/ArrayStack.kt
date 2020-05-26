package alg02stacksqueues


class ArrayStack<T> : AbstractArrayBasedCollection<T>(), Stack<T> {

    override fun push(item: T) {
        this.growArrayIfRequired()
        this.array[this.size++] = item
    }

    override fun pop(): T {
        val item = this.array[this.size - 1]
        // Release unused pointer
        this.array.releaseReferenceAt(this.size - 1)
        this.size--
        this.shrinkArrayIfRequired()
        return item
    }

    /*
    This iterator is used when resizing the underlying array to maintain the correct order.
     */
    private inner class ArrayStackIterator : Iterator<T> {
        private var index = 0

        override fun hasNext(): Boolean {
            return this.index < size
        }

        override fun next(): T {
            return array[this.index++]
        }

    }

    override fun resizedArrayContent(): Iterator<T> {
        return ArrayStackIterator()
    }

    /*
    This iterator is used to actually iterate through the stack.
    As the stack is LIFO queue, the iterator returns elements in reversed order.
     */
    private inner class ReversedArrayStackIterator : Iterator<T> {
        private var index = size - 1

        override fun hasNext(): Boolean {
            return this.index >= 0
        }

        override fun next(): T {
            return array[this.index--]
        }

    }

    override fun iterator(): Iterator<T> {
        return ReversedArrayStackIterator()
    }

}