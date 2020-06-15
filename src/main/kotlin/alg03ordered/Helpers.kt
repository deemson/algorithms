package alg03ordered

import alg02deques.arrayDequeOf
import alg02deques.dequeOf
import alg02deques.linkedDequeOf

fun <T> orderedArrayDequeOf(vararg items: T): OrderedDequeAdapter<T> {
    return OrderedDequeAdapter(arrayDequeOf(*items))
}

fun <T> orderedLinkedDequeOf(vararg items: T): OrderedDequeAdapter<T> {
    return OrderedDequeAdapter(linkedDequeOf(*items))
}

fun <T> orderedDequeOf(vararg items: T): OrderedDequeAdapter<T> {
    return OrderedDequeAdapter(dequeOf(*items))
}

inline fun <reified T> orderedArrayOf(vararg items: T): OrderedArrayAdapter<T> {
    return OrderedArrayAdapter(arrayOf(*items))
}

inline fun <reified T> orderedOf(vararg items: T): OrderedArrayAdapter<T> {
    return orderedArrayOf(*items)
}
