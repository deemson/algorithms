package alg03sorting

import alg03ordered.Ordered
import alg03ordered.swap

/**
 * Shell sort is similar to previous algorithm (S2InsertionSort).
 * Insertion sort (S2InsertionSort) moves a single item at a time, thus being slow
 * when sorting an array that requires a lot of swaps.
 * Shell sort tries to compensate this by gradually sorting the array
 * in a similar fashion, but with bigger steps (>1). Step is equal to 1
 * at the very end of a Shell sorting algorithm, efficiently becoming
 * an insertion sort when the array is almost sorted.
 * <p>
 * The worst-case number of compares used by shell sort with 3x+1 sequence is O(N^1.5).
 */
object S3ShellSort : Sort {
    override fun <T> sort(ordered: Ordered<T>, comparator: Comparator<T>) {
        var step = 1
        // Choosing the biggest step < a.length from Knuth's shellsort step sequence (3*x + 1).
        while (step < ordered.size / 3) {
            // 1, 4, 13, 40, 121, 364, ...
            step = 3 * step + 1
        }
        while (step >= 1) {
            // Make an insertion sort for all of the steps in the sequence.
            // The outer loop is still incremented by 1 each iteration.
            for (outerIndex in step until ordered.size) {
                // However, the inner loop is decremented by 'step'.
                for (innerIndex in outerIndex downTo step step step) {
                    // We swap (i.e. move items closer to the start of the array)
                    // until the item finds it's place (not less than the item to the left - step).
                    // When step == 1 and all items have found their place, the array is sorted.
                    if (comparator.compare(ordered[innerIndex], ordered[innerIndex - step]) >= 0) {
                        break
                    }
                    ordered.swap(innerIndex, innerIndex - step)
                }
            }
            step /= 3
        }
    }
}
