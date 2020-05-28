package alg03sorting

/**
 * Divide and conquer (split-sort-merge) algorithm.
 * The execution time is as fast as O(N*log2(N)).
 * Traditional recursion implementation.
 */
object S4aMergeSort : S4AbstractMergeSort() {
    private fun <T : Comparable<T>> sort(array: Array<T>, auxArray: Array<T>, lo: Int, hi: Int) {
        // If the indexes overlap the sorting is done.
        if (hi <= lo) {
            return
        }
        // Divide and conquer in action: the array is sliced in two parts...
        val mid = lo + (hi - lo) / 2
        // ...and each part is sorted independently...
        sort(array, auxArray, lo, mid)
        sort(array, auxArray, mid + 1, hi)
        // ... and two parts are merged back together.
        merge(array, auxArray, lo, mid, hi)
    }

    override fun <T : Comparable<T>> sort(array: Array<T>) {
        val auxArray = array.copyOf()
        this.sort(array, auxArray, lo = 0, hi = array.size - 1)
    }
}
