package alg03sorting

import alg03indexable.Indexable

interface Sort {
    fun <T> sort(indexable: Indexable<T>, comparator: Comparator<T>)
}
