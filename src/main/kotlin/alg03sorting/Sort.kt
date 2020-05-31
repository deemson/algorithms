package alg03sorting

interface Sort {
    fun <T : Comparable<T>> sort(array: Array<T>)
    fun <T : Comparable<T>> sortAscending(array: Array<T>)
    fun <T : Comparable<T>> sortDescending(array: Array<T>)
    fun <T> sort(array: Array<T>, comparator: Comparator<T>)
}
