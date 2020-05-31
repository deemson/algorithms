package alg03sorting

abstract class AbstractBaseSort : Sort {
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

    override fun <T : Comparable<T>> sortAscending(array: Array<T>) {
        sort(array, AscendingOrderComparator())
    }

    override fun <T : Comparable<T>> sortDescending(array: Array<T>) {
        sort(array, DescendingOrderComparator())
    }

    override fun <T : Comparable<T>> sort(array: Array<T>) {
        sortAscending(array)
    }
}
