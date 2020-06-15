package alg03indexable

import alg02deques.arrayDequeOf
import alg02deques.dequeOf
import alg02deques.linkedDequeOf

fun <T> indexableArrayDequeOf(vararg items: T): IndexableDequeAdapter<T> {
    return IndexableDequeAdapter(arrayDequeOf(*items))
}

fun <T> indexableLinkedDequeOf(vararg items: T): IndexableDequeAdapter<T> {
    return IndexableDequeAdapter(linkedDequeOf(*items))
}

fun <T> indexableDequeOf(vararg items: T): IndexableDequeAdapter<T> {
    return IndexableDequeAdapter(dequeOf(*items))
}

inline fun <reified T> indexableArrayOf(vararg items: T): IndexableArrayAdapter<T> {
    return IndexableArrayAdapter(arrayOf(*items))
}

inline fun <reified T> indexableOf(vararg items: T): IndexableArrayAdapter<T> {
    return indexableArrayOf(*items)
}
