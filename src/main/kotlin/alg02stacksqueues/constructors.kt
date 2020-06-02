package alg02stacksqueues

import alg02deques.ArrayDeque
import alg02deques.LinkedDeque

fun <T> arrayQueue(): Queue<T> {
    return QueueDequeAdapter(ArrayDeque())
}

fun <T> linkedQueue(): Queue<T> {
    return QueueDequeAdapter(LinkedDeque())
}

fun <T> arrayStack(): Stack<T> {
    return StackDequeAdapter(ArrayDeque())
}

fun <T> linkedStack(): Stack<T> {
    return StackDequeAdapter(LinkedDeque())
}
