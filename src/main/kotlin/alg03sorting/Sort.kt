package alg03sorting

interface Sort {
    fun <T> sort(sortable: Sortable<T>, comparator: Comparator<T>)
}
