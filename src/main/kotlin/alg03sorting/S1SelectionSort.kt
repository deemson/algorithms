package alg03sorting

import alg03indexable.Indexable
import alg03indexable.swap

/**
 * Selection sort does ~N^2/2 compares and N swaps.
 */
object S1SelectionSort : Sort {
    override fun <T> sort(indexable: Indexable<T>, comparator: Comparator<T>) {
        for (outerIndex in indexable.indices) {
            /*
            Each iteration of the outer loop tries to find the minimum item index to the right
            of the current index and swap this item with the current item.

            Outer loop the the loop that iterates through all of the items with the intention to swap
            at the end.
             */
            var minimumIndex = outerIndex
            /*
            Inner loop is the loop that tries to find the minimum item to the right of the current one.
            That's why it starts at the current index of the outer loop.
             */
            for (innerIndex in (outerIndex + 1 until indexable.size)) {
                if (comparator.compare(indexable[innerIndex], indexable[minimumIndex]) < 0) {
                    minimumIndex = innerIndex
                }
            }
            /*
            This line swaps the found minimum item with the current,
            (or the current item with itself if it itself is the minimum).
             */
            indexable.swap(outerIndex, minimumIndex)
        }
    }
}
