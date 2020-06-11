package comparators

class MinComparator<T : Comparable<T>> : Comparator<T> {
    override fun compare(o1: T, o2: T): Int {
        return o1.compareTo(o2)
    }
}
