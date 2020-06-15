package alg03sorting

import alg02deques.ArrayDeque
import alg02deques.Deque
import alg03indexable.Indexable

/**
 * Divide and conquer (split-sort-merge) algorithm.
 * The execution time is as fast as O(N*log2(N)).
 * Traditional recursion implementation.
 */
object S4aMergeSort : S4AbstractMergeSort() {
    private fun <T> sort(indexable: Indexable<T>, auxDeque: Deque<T>, comparator: Comparator<T>, lo: Int, hi: Int) {
        // If the indexes overlap the sorting is done.
        if (hi <= lo) {
            return
        }
        // Divide and conquer in action: the array is sliced in two parts...
        val mid = lo + (hi - lo) / 2
        // ...and each part is sorted independently...
        sort(indexable, auxDeque, comparator, lo, mid)
        sort(indexable, auxDeque, comparator, mid + 1, hi)
        // ... and two parts are merged back together.
        merge(indexable, auxDeque, comparator, lo, mid, hi)
    }

    override fun <T> sort(indexable: Indexable<T>, comparator: Comparator<T>) {
        val auxDeque = ArrayDeque<T>(indexable.size)
        for (item in indexable) {
            auxDeque.addLast(item)
        }
        this.sort(indexable, auxDeque, comparator, lo = 0, hi = indexable.size - 1)
    }
}
