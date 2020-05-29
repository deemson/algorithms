package alg02stacksqueues

abstract class AbstractLinkedCollection<T> : Collection<T> {
    protected var size = 0
    protected var first: Node<T>? = null

    protected class Node<T>(val item: T) {
        var next: Node<T>? = null
    }

    override fun isEmpty(): Boolean {
        return this.first == null
    }

    override fun size(): Int {
        return this.size
    }

    private inner class LinkedIterator : Iterator<T> {
        private var current = first

        override fun hasNext(): Boolean {
            return this.current != null
        }

        override fun next(): T {
            val current = this.current!!
            this.current = current.next
            return current.item
        }
    }

    override fun iterator(): Iterator<T> {
        return LinkedIterator()
    }
}
