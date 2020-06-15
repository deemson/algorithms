package alg03indexable

import alg02deques.arrayDequeOf
import alg02deques.dequeOf
import alg02deques.linkedDequeOf

fun <T> orderedArrayDequeOf(vararg items: T): IndexableDequeAdapter<T> {
    return IndexableDequeAdapter(arrayDequeOf(*items))
}

fun <T> orderedLinkedDequeOf(vararg items: T): IndexableDequeAdapter<T> {
    return IndexableDequeAdapter(linkedDequeOf(*items))
}

fun <T> orderedDequeOf(vararg items: T): IndexableDequeAdapter<T> {
    return IndexableDequeAdapter(dequeOf(*items))
}

inline fun <reified T> orderedArrayOf(vararg items: T): IndexableArrayAdapter<T> {
    return IndexableArrayAdapter(arrayOf(*items))
}

inline fun <reified T> orderedOf(vararg items: T): IndexableArrayAdapter<T> {
    return orderedArrayOf(*items)
}
