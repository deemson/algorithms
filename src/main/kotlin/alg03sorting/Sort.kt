package alg03sorting

interface Sort {
    fun <T : Comparable<T>> sort(array: Array<T>)
}