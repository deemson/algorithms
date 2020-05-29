package alg02stacksqueues

class LinkedStack<T> : AbstractLinkedCollection<T>(), Stack<T> {
    override fun push(item: T) {
        val oldFirst = this.first
        this.first = Node(item)
        this.first!!.next = oldFirst
        this.size++
    }

    override fun pop(): T {
        val item = this.first!!.item
        this.first = this.first!!.next
        this.size--
        return item
    }
}
