package alg03sorting

/**
 * Divide and conquer (split-sort-merge) algorithm.
 * The execution time is as fast as O(N*log2(N)).
 * Traditional recursion implementation.
 */
object S4aMergeSort : S4AbstractMergeSort() {
    private fun <T> sort(array: Array<T>, auxArray: Array<T>, comparator: Comparator<T>, lo: Int, hi: Int) {
        // If the indexes overlap the sorting is done.
        if (hi <= lo) {
            return
        }
        // Divide and conquer in action: the array is sliced in two parts...
        val mid = lo + (hi - lo) / 2
        // ...and each part is sorted independently...
        sort(array, auxArray, comparator, lo, mid)
        sort(array, auxArray, comparator, mid + 1, hi)
        // ... and two parts are merged back together.
        merge(array, auxArray, comparator, lo, mid, hi)
    }

    override fun <T> sort(array: Array<T>, comparator: Comparator<T>) {
        val auxArray = array.copyOf()
        this.sort(array, auxArray, comparator, lo = 0, hi = array.size - 1)
    }
}
