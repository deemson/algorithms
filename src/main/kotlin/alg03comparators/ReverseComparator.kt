package alg03comparators

class ReverseComparator<T>(private val comparator: Comparator<T>) : Comparator<T> {
    override fun compare(o1: T, o2: T): Int {
        return this.comparator.compare(o2, o1)
    }
}
