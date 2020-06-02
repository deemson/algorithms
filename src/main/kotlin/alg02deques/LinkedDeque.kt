package alg02deques

import java.lang.IllegalStateException

class LinkedDeque<T> : Deque<T> {
    private var _size = 0

    private inner class Node(var item: T) {
        var prev: Node? = null
        var next: Node? = null
    }

    private var head: Node? = null
    private var tail: Node? = null

    override val size: Int
        get() = this._size

    private fun nodeAt(index: Int): Node {
        if (index < 0 || index >= this._size) {
            throw IndexOutOfBoundsException("$index is out of bounds")
        }
        var node = this.head
        var currentIndex = 0
        while (currentIndex < index) {
            node = node!!.next
            currentIndex++
        }
        return node!!
    }

    override fun get(index: Int): T {
        return this.nodeAt(index).item
    }

    override fun set(index: Int, item: T) {
        this.nodeAt(index).item = item
    }

    override fun addFirst(item: T) {
        val head = this.head
        this.head = Node(item)
        if (this.isEmpty) {
            this.tail = this.head
        } else {
            head!!.prev = this.head
            this.head!!.next = head
        }
        this._size++
    }

    override fun addLast(item: T) {
        val tail = this.tail
        this.tail = Node(item)
        if (this.isEmpty) {
            this.head = this.tail
        } else {
            tail!!.next = this.tail
            this.tail!!.prev = tail
        }
        this._size++
    }

    private fun assertNotEmpty() {
        if (this.isEmpty) {
            throw IllegalStateException("dequeue is empty")
        }
    }

    override fun removeFirst(): T {
        this.assertNotEmpty()
        val head = this.head!!
        this.head = head.next
        this._size--
        if (this.isEmpty) {
            this.tail = null
        } else {
            this.head!!.prev = null
        }
        return head.item
    }

    override fun removeLast(): T {
        this.assertNotEmpty()
        val tail = this.tail!!
        this.tail = tail.prev
        this._size--
        if (this.isEmpty) {
            this.head = null
        } else {
            this.tail!!.next = null
        }
        return tail.item
    }

    private inner class ArrayDequeueIterator : Iterator<T> {
        private var node = this@LinkedDeque.head

        override fun hasNext(): Boolean {
            return this.node != null
        }

        override fun next(): T {
            val item = this.node!!.item
            this.node = this.node!!.next
            return item
        }
    }

    override fun iterator(): Iterator<T> {
        return ArrayDequeueIterator()
    }
}
