package alg03sorting


/**
 * Shell sort is similar to previous algorithm (S2InsertionSort).
 * Insertion sort (S2InsertionSort) moves a single element at a time, thus being slow
 * when sorting an array that requires a lot of exchanges.
 * Shell sort tries to compensate this by gradually sorting the array
 * in a similar fashion, but with bigger steps (>1). Step is equal to 1
 * at the very end of a Shell sorting algorithm, efficiently becoming
 * an insertion sort when the array is almost sorted.
 * <p>
 * The worst-case number of compares used by shell sort with 3x+1 sequence is O(N^1.5).
 */
object S3ShellSort : Sort {
    override fun <T : Comparable<T>> sort(array: Array<T>) {
        var step = 1
        // Choosing the biggest step < a.length from Knuth's shellsort step sequence (3*x + 1).
        while (step < array.size / 3) {
            // 1, 4, 13, 40, 121, 364, ...
            step = 3 * step + 1;
        }
        while (step >= 1) {
            // Make an insertion sort for all of the steps in the sequence.
            // The outer loop is still incremented by 1 each iteration.
            for (outerIndex in step until array.size) {
                // However, the inner loop is decremented by 'step'.
                for (innerIndex in outerIndex downTo step step step) {
                    // We exchange (i.e. move elements closer to the start of the array)
                    // until the element finds it's place (not less than the element to the left - step).
                    // When step == 1 and all elements have found their place, the array is sorted.
                    if (array[innerIndex] >= array[innerIndex - step]) {
                        break
                    }
                    exchange(array, innerIndex, innerIndex - step)
                }
            }
            step /= 3;
        }
    }
}