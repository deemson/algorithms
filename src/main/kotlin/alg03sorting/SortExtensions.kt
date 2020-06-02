package alg03sorting

import alg02deques.Deque

private class AscendingOrderComparator<T : Comparable<T>> : Comparator<T> {
    override fun compare(o1: T, o2: T): Int {
        return o1.compareTo(o2)
    }
}

private class DescendingOrderComparator<T : Comparable<T>> : Comparator<T> {
    override fun compare(o1: T, o2: T): Int {
        return o2.compareTo(o1)
    }
}

fun <T : Comparable<T>> Sort.sortAscending(sortable: Sortable<T>) {
    this.sort(sortable, AscendingOrderComparator())
}

fun <T : Comparable<T>> Sort.sortDescending(sortable: Sortable<T>) {
    this.sort(sortable, DescendingOrderComparator())
}

fun <T : Comparable<T>> Sort.sort(sortable: Sortable<T>) {
    this.sortAscending(sortable)
}

fun <T> Sort.sort(deque: Deque<T>, comparator: Comparator<T>) {
    this.sort(SortableDequeAdapter(deque), comparator)
}

fun <T : Comparable<T>> Sort.sortAscending(deque: Deque<T>) {
    this.sort(deque, AscendingOrderComparator())
}

fun <T : Comparable<T>> Sort.sortDescending(deque: Deque<T>) {
    this.sort(deque, DescendingOrderComparator())
}

fun <T : Comparable<T>> Sort.sort(deque: Deque<T>) {
    this.sortAscending(deque)
}

fun <T> Sort.sort(array: Array<T>, comparator: Comparator<T>) {
    this.sort(SortableArrayAdapter(array), comparator)
}

fun <T : Comparable<T>> Sort.sortAscending(array: Array<T>) {
    this.sort(array, AscendingOrderComparator())
}

fun <T : Comparable<T>> Sort.sortDescending(array: Array<T>) {
    this.sort(array, DescendingOrderComparator())
}

fun <T : Comparable<T>> Sort.sort(array: Array<T>) {
    this.sortAscending(array)
}
