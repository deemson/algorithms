package alg02stacksqueues

class LinkedQueue<T> : AbstractLinkedCollection<T>(), Queue<T> {
    private var last: Node<T>? = null

    override fun enqueue(item: T) {
        val oldLast = this.last
        this.last = Node(item)
        if (this.isEmpty()) {
            this.first = this.last
        } else {
            oldLast?.next = this.last
        }
        this.size++
    }

    override fun dequeue(): T {
        val first = this.first!!
        this.first = first.next
        this.size--
        if (this.isEmpty()) {
            this.last = null
        }
        return first.item
    }
}
