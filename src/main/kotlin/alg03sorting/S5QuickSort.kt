package alg03sorting

import kotlin.random.Random

object S5QuickSort : Sort {
    /**
     * This function is the core of quicksort. It ensures that an arbitrary item from the part of the array
     * from left to right is in place, i.e. smaller than all of the items to the left
     * and larger than all of the items to the right of this item.
     * Method partition returns an index of the item, that was put in place.
     */
    private fun <T> partition(sortable: Sortable<T>, comparator: Comparator<T>, left: Int, right: Int): Int {
        var leftMarker = left
        var rightMarker = right + 1
        while (true) {
            // find item on the left to swap
            while (comparator.compare(sortable[++leftMarker], sortable[left]) < 0) {
                if (leftMarker == right) {
                    break
                }
            }
            // find item on the right to swap
            while (true) {
                if (comparator.compare(sortable[left], sortable[--rightMarker]) >= 0) {
                    break
                }
            }
            // check if markers cross
            if (leftMarker >= rightMarker) {
                break
            }
            // swap
            sortable.swap(leftMarker, rightMarker)
        }
        sortable.swap(left, rightMarker)
        // return index of item now known to be in place
        return rightMarker
    }

    private fun <T> sort(sortable: Sortable<T>, comparator: Comparator<T>, lo: Int, hi: Int) {
        if (lo >= hi) {
            return
        }
        val itemInPlaceIndex = partition(sortable, comparator, lo, hi)
        sort(sortable, comparator, lo, itemInPlaceIndex - 1)
        sort(sortable, comparator, itemInPlaceIndex + 1, hi)
    }

    private var random: Random

    init {
        val seed = System.currentTimeMillis()
        this.random = Random(seed)
    }

    private fun uniform(n: Int): Int {
        if (n <= 0) throw IllegalArgumentException("Parameter n must be positive")
        return random.nextInt(n)
    }

    private fun <T> shuffle(sortable: Sortable<T>) {
        for (index in sortable.indices) {
            val swapIndex = index + uniform(sortable.size - index) // between index and array.size - index
            sortable.swap(index, swapIndex)
        }
    }

    override fun <T> sort(sortable: Sortable<T>, comparator: Comparator<T>) {
        // required for guaranteed performance
        this.shuffle(sortable)
        sort(sortable, comparator, lo = 0, hi = sortable.size - 1)
    }
}
