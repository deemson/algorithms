package alg03sorting

import alg02deques.Deque
import alg03indexable.Indexable

/**
 * Base class for different merge sort implementations.
 * Contains trademark 'merge' function shared by these implementations.
 */
abstract class S4AbstractMergeSort : Sort {
    /**
     * Mergesort is a divide and conquer algorithm, which means it divides the array
     * into smaller parts and sort them individually.
     * Merge is the function that "glues" the divided parts into the array of the original size
     * and original (but sorted) content.
     * The function merges two small sorted parts the array
     * (that span indices lo-mid and mid-hi respectively) into one big sorted part (that spans indices lo-hi).
     * @param indexable - sortable, parts of which is to be merged
     * @param auxDequeue - auxDequeue (auxDequeue.size == sortable.size)
     * @param comparator - class used to determine order
     * @param lo - low threshold
     * @param mid - middle point
     * @param hi - high threshold
     */
    protected fun <T> merge(
        indexable: Indexable<T>,
        auxDeque: Deque<T>,
        comparator: Comparator<T>,
        lo: Int,
        mid: Int,
        hi: Int
    ) {
        for (index in (lo..hi)) {
            auxDeque[index] = indexable[index]
        }
        var leftMarker = lo
        var rightMarker = mid + 1
        for (index in lo..hi) {
            when {
                // When the left marker has crossed the middle point, it means there's nothing left
                // in the left part and the remainder of the right part can just be copied.
                leftMarker > mid -> {
                    indexable[index] = auxDeque[rightMarker++]
                }
                // The same goes for the right part when the right marker crosses hi threshold.
                rightMarker > hi -> {
                    indexable[index] = auxDeque[leftMarker++]
                }
                // At this point both parts are not exhausted, thus it's checked which part contains
                // smaller item, then it's copied to 'array' and respective marker is increased.
                comparator.compare(auxDeque[rightMarker], auxDeque[leftMarker]) < 0 -> {
                    indexable[index] = auxDeque[rightMarker++]
                }
                else -> { // comparator.compare(auxDeque[leftMarker], auxDeque[rightMarker]) < 0
                    indexable[index] = auxDeque[leftMarker++]
                }
            }
        }
    }
}
