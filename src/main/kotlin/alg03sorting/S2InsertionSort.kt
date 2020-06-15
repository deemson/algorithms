package alg03sorting

import alg03ordered.Ordered
import alg03ordered.swap

/**
 * On average, insertion sort does ~1/4N^2 compares and ~1/4N^2 swaps
 * With partially sorted arrays, though, it's performance may be close to linear
 */
object S2InsertionSort : Sort {
    override fun <T> sort(ordered: Ordered<T>, comparator: Comparator<T>) {
        // The outer loop adds an additional item to the inner loop below with each iteration.
        for (outerIndex in ordered.indices) {
            // Every added item tries to find its place in the left part (sorted part) of the entire array.
            for (innerIndex in outerIndex downTo 1) {
                // downTo 1 because first inserted item needs something to compare to
                // (item at index 0 in this case)
                if (comparator.compare(ordered[innerIndex], ordered[innerIndex - 1]) < 0) {
                    ordered.swap(innerIndex, innerIndex - 1)
                }
            }
        }
    }
}
