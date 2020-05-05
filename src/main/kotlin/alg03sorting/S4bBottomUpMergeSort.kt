package alg03sorting

import kotlin.math.min

/**
 * Divide and conquer (split-sort-merge) algorithm.
 * The execution time is as fast as O(N*log2(N)).
 * <p>
 * It is the same mergesort, only without the recursion.
 */
object S4bBottomUpMergeSort : S4AbstractMergeSort() {
    override fun <T : Comparable<T>> sort(array: Array<T>) {
        val auxArray = array.copyOf()
        // Part size grows as 1 2 4 8 ...
        var partSize = 1
        while (partSize < array.size) {
            // Parts of size 'partSize' is merged into parts of size '2 * partSize'
            for (lo in 0 until array.size - partSize step 2 * partSize) {
                val mid = lo + partSize - 1
                val hi = min(lo + 2 * partSize - 1, array.size - 1)
                this.merge(array, auxArray, lo, mid, hi)
            }
            partSize += partSize
        }
    }
}