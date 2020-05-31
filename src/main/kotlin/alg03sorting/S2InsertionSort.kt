package alg03sorting

/**
 * On average, insertion sort does ~1/4N^2 compares and ~1/4N^2 exchanges
 * With partially sorted arrays, though, it's performance may be close to linear
 */
object S2InsertionSort : AbstractBaseSort() {
    override fun <T> sort(array: Array<T>, comparator: Comparator<T>) {
        // The outer loop adds an additional element to the inner loop below with each iteration.
        for (outerIndex in array.indices) {
            // Every added element tries to find its place in the left part (sorted part) of the entire array.
            for (innerIndex in outerIndex downTo 1) {
                // downTo 1 because first inserted element needs something to compare to
                // (element at index 0 in this case)
                if (comparator.compare(array[innerIndex], array[innerIndex - 1]) < 0) {
                    exchange(array, innerIndex, innerIndex - 1)
                }
            }
        }
    }
}
