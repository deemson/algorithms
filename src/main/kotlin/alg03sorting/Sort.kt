package alg03sorting

import alg03ordered.Ordered

interface Sort {
    fun <T> sort(ordered: Ordered<T>, comparator: Comparator<T>)
}
