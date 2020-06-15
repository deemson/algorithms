package alg02deques

fun <T> arrayDequeOf(vararg items: T): ArrayDeque<T> {
    val deque = ArrayDeque<T>()
    for (item in items) {
        deque.addLast(item)
    }
    return deque
}

fun <T> linkedDequeOf(vararg items: T): LinkedDeque<T> {
    val deque = LinkedDeque<T>()
    for (item in items) {
        deque.addLast(item)
    }
    return deque
}

fun <T> dequeOf(vararg items: T): Deque<T> {
    return arrayDequeOf(*items)
}
