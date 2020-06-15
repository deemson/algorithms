package alg03sorting

import alg02deques.Deque
import alg03comparators.MaxComparator
import alg03comparators.MinComparator
import alg03indexable.Indexable
import alg03indexable.IndexableArrayAdapter
import alg03indexable.IndexableDequeAdapter

fun <T : Comparable<T>> Sort.sortAscending(indexable: Indexable<T>) {
    this.sort(indexable, MinComparator())
}

fun <T : Comparable<T>> Sort.sortDescending(indexable: Indexable<T>) {
    this.sort(indexable, MaxComparator())
}

fun <T : Comparable<T>> Sort.sort(indexable: Indexable<T>) {
    this.sortAscending(indexable)
}

fun <T> Sort.sort(deque: Deque<T>, comparator: Comparator<T>) {
    this.sort(IndexableDequeAdapter(deque), comparator)
}

fun <T : Comparable<T>> Sort.sortAscending(deque: Deque<T>) {
    this.sort(deque, MinComparator())
}

fun <T : Comparable<T>> Sort.sortDescending(deque: Deque<T>) {
    this.sort(deque, MaxComparator())
}

fun <T : Comparable<T>> Sort.sort(deque: Deque<T>) {
    this.sortAscending(deque)
}

fun <T> Sort.sort(array: Array<T>, comparator: Comparator<T>) {
    this.sort(IndexableArrayAdapter(array), comparator)
}

fun <T : Comparable<T>> Sort.sortAscending(array: Array<T>) {
    this.sort(array, MinComparator())
}

fun <T : Comparable<T>> Sort.sortDescending(array: Array<T>) {
    this.sort(array, MaxComparator())
}

fun <T : Comparable<T>> Sort.sort(array: Array<T>) {
    this.sortAscending(array)
}
