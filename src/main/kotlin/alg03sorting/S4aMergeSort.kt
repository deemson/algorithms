package alg03sorting

import alg02deques.ArrayDeque
import alg02deques.Deque
import alg03ordered.Ordered

/**
 * Divide and conquer (split-sort-merge) algorithm.
 * The execution time is as fast as O(N*log2(N)).
 * Traditional recursion implementation.
 */
object S4aMergeSort : S4AbstractMergeSort() {
    private fun <T> sort(ordered: Ordered<T>, auxDeque: Deque<T>, comparator: Comparator<T>, lo: Int, hi: Int) {
        // If the indexes overlap the sorting is done.
        if (hi <= lo) {
            return
        }
        // Divide and conquer in action: the array is sliced in two parts...
        val mid = lo + (hi - lo) / 2
        // ...and each part is sorted independently...
        sort(ordered, auxDeque, comparator, lo, mid)
        sort(ordered, auxDeque, comparator, mid + 1, hi)
        // ... and two parts are merged back together.
        merge(ordered, auxDeque, comparator, lo, mid, hi)
    }

    override fun <T> sort(ordered: Ordered<T>, comparator: Comparator<T>) {
        val auxDeque = ArrayDeque<T>(ordered.size)
        for (item in ordered) {
            auxDeque.addLast(item)
        }
        this.sort(ordered, auxDeque, comparator, lo = 0, hi = ordered.size - 1)
    }
}
