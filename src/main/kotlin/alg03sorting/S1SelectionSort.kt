package alg03sorting

/**
 * Selection sort does ~N^2/2 compares and N exchanges.
 */
object S1SelectionSort : AbstractBaseSort() {
    override fun <T> sort(array: Array<T>, comparator: Comparator<T>) {
        for (outerIndex in array.indices) {
            /*
            Each iteration of the outer loop tries to find the minimum element index to the right
            of the current index and swap this element with the current element.

            Outer loop the the loop that iterates through all of the elements with the intention to swap
            at the end.
             */
            var minimumIndex = outerIndex
            /*
            Inner loop is the loop that tries to find the minimum element to the right of the current one.
            That's why it starts at the current index of the outer loop.
             */
            for (innerIndex in (outerIndex + 1 until array.size)) {
                if (comparator.compare(array[innerIndex], array[minimumIndex]) < 0) {
                    minimumIndex = innerIndex
                }
            }
            /*
            This line exchanges the found minimum element with the current,
            (or the current element with itself if it itself is the minimum).
             */
            exchange(array, outerIndex, minimumIndex)
        }
    }
}
