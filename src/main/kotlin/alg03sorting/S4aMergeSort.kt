package alg03sorting

import alg02deques.ArrayDeque
import alg02deques.Deque

/**
 * Divide and conquer (split-sort-merge) algorithm.
 * The execution time is as fast as O(N*log2(N)).
 * Traditional recursion implementation.
 */
object S4aMergeSort : S4AbstractMergeSort() {
    private fun <T> sort(sortable: Sortable<T>, auxDeque: Deque<T>, comparator: Comparator<T>, lo: Int, hi: Int) {
        // If the indexes overlap the sorting is done.
        if (hi <= lo) {
            return
        }
        // Divide and conquer in action: the array is sliced in two parts...
        val mid = lo + (hi - lo) / 2
        // ...and each part is sorted independently...
        sort(sortable, auxDeque, comparator, lo, mid)
        sort(sortable, auxDeque, comparator, mid + 1, hi)
        // ... and two parts are merged back together.
        merge(sortable, auxDeque, comparator, lo, mid, hi)
    }

    override fun <T> sort(sortable: Sortable<T>, comparator: Comparator<T>) {
        val auxDeque = ArrayDeque<T>(sortable.size)
        for (item in sortable) {
            auxDeque.addLast(item)
        }
        this.sort(sortable, auxDeque, comparator, lo = 0, hi = sortable.size - 1)
    }
}
