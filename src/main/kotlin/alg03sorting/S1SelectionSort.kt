package alg03sorting

/**
 * Selection sort does ~N^2/2 compares and N swaps.
 */
object S1SelectionSort : Sort {
    override fun <T> sort(sortable: Sortable<T>, comparator: Comparator<T>) {
        for (outerIndex in sortable.indices) {
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
            for (innerIndex in (outerIndex + 1 until sortable.size)) {
                if (comparator.compare(sortable[innerIndex], sortable[minimumIndex]) < 0) {
                    minimumIndex = innerIndex
                }
            }
            /*
            This line swaps the found minimum item with the current,
            (or the current item with itself if it itself is the minimum).
             */
            sortable.swap(outerIndex, minimumIndex)
        }
    }
}
