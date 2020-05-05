package alg03sorting

interface SortingAlgorithm {
    fun <T : Comparable<T>> sort(array: Array<T>)
}