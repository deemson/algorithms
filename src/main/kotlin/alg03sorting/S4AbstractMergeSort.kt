package alg03sorting

/**
 * Base class for different merge sort implementations.
 * Contains trademark 'merge' function shared by these implementations.
 */
abstract class S4AbstractMergeSort : SortingAlgorithm {
    /**
     * Mergesort is a divide and conquer algorithm, which means it divides the array
     * into smaller parts and sort them individually.
     * Merge is the function that "glues" the divided parts into the array of the original size
     * and original (but sorted) content.
     * The function merges two small sorted parts the array
     * (that span indices lo-mid and mid-hi respectively) into one big sorted part (that spans indices lo-hi).
     *
     * @param array    - array, parts of which is to be merged
     * @param auxArray - auxArray (auxArray.size == array.size)
     * @param lo       - low threshold
     * @param mid      - middle point
     * @param hi       - high threshold
     */
    protected fun <T : Comparable<T>> merge(array: Array<T>, auxArray: Array<T>, lo: Int, mid: Int, hi: Int) {
        System.arraycopy(array, lo, auxArray, lo, hi + 1 - lo)
        var leftMarker = lo
        var rightMarker = mid + 1
        for (index in lo..hi) {
            when {
                // When the left marker has crossed the middle point, it means there's nothing left
                // in the left part and the remainder of the right part can just be copied.
                leftMarker > mid -> {
                    array[index] = auxArray[rightMarker++]
                }
                // The same goes for the right part when the right marker crosses hi threshold.
                rightMarker > hi -> {
                    array[index] = auxArray[leftMarker++]
                }
                // At this point both parts are not exhausted, thus it's checked which part contains
                // smaller element, then it's copied to 'array' and respective marker is increased.
                auxArray[rightMarker] < auxArray[leftMarker] -> {
                    array[index] = auxArray[rightMarker++]
                }
                else -> { // (auxArray[leftMarker] < auxArray[rightMarker])
                    array[index] = auxArray[leftMarker++]
                }
            }
        }
    }




}