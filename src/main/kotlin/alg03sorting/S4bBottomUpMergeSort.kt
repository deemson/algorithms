package alg03sorting

import alg02deques.ArrayDeque
import kotlin.math.min

/**
 * Divide and conquer (split-sort-merge) algorithm.
 * The execution time is as fast as O(N*log2(N)).
 * <p>
 * It is the same mergesort, only without the recursion.
 */
object S4bBottomUpMergeSort : S4AbstractMergeSort() {
    override fun <T> sort(sortable: Sortable<T>, comparator: Comparator<T>) {
        val auxDeque = ArrayDeque<T>(sortable.size)
        for (item in sortable) {
            auxDeque.addLast(item)
        }
        // Part size grows as 1 2 4 8 ...
        var partSize = 1
        while (partSize < sortable.size) {
            // Parts of size 'partSize' is merged into parts of size '2 * partSize'
            for (lo in 0 until sortable.size - partSize step 2 * partSize) {
                val mid = lo + partSize - 1
                val hi = min(lo + 2 * partSize - 1, sortable.size - 1)
                this.merge(sortable, auxDeque, comparator, lo, mid, hi)
            }
            partSize += partSize
        }
    }
}
