package comparators

class MaxComparator<T : Comparable<T>> : Comparator<T> {
    override fun compare(o1: T, o2: T): Int {
        return o2.compareTo(o1)
    }
}
