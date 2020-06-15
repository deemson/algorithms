package alg03sorting

import alg02deques.Deque
import alg03comparators.MaxComparator
import alg03comparators.MinComparator
import alg03ordered.Ordered
import alg03ordered.OrderedArrayAdapter
import alg03ordered.OrderedDequeAdapter

fun <T : Comparable<T>> Sort.sortAscending(ordered: Ordered<T>) {
    this.sort(ordered, MinComparator())
}

fun <T : Comparable<T>> Sort.sortDescending(ordered: Ordered<T>) {
    this.sort(ordered, MaxComparator())
}

fun <T : Comparable<T>> Sort.sort(ordered: Ordered<T>) {
    this.sortAscending(ordered)
}

fun <T> Sort.sort(deque: Deque<T>, comparator: Comparator<T>) {
    this.sort(OrderedDequeAdapter(deque), comparator)
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
    this.sort(OrderedArrayAdapter(array), comparator)
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
