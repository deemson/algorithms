package alg03sorting

import alg02deques.Deque
import comparators.MaxComparator
import comparators.MinComparator

fun <T : Comparable<T>> Sort.sortAscending(sortable: Sortable<T>) {
    this.sort(sortable, MinComparator())
}

fun <T : Comparable<T>> Sort.sortDescending(sortable: Sortable<T>) {
    this.sort(sortable, MaxComparator())
}

fun <T : Comparable<T>> Sort.sort(sortable: Sortable<T>) {
    this.sortAscending(sortable)
}

fun <T> Sort.sort(deque: Deque<T>, comparator: Comparator<T>) {
    this.sort(SortableDequeAdapter(deque), comparator)
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
    this.sort(SortableArrayAdapter(array), comparator)
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
