package alg03sorting

import alg03ordered.Ordered
import alg03ordered.swap
import kotlin.random.Random

object S5QuickSort : Sort {
    /**
     * This function is the core of quicksort. It ensures that an arbitrary item from the part of the array
     * from left to right is in place, i.e. smaller than all of the items to the left
     * and larger than all of the items to the right of this item.
     * Method partition returns an index of the item, that was put in place.
     */
    private fun <T> partition(ordered: Ordered<T>, comparator: Comparator<T>, left: Int, right: Int): Int {
        var leftMarker = left
        var rightMarker = right + 1
        while (true) {
            // find item on the left to swap
            while (comparator.compare(ordered[++leftMarker], ordered[left]) < 0) {
                if (leftMarker == right) {
                    break
                }
            }
            // find item on the right to swap
            while (true) {
                if (comparator.compare(ordered[left], ordered[--rightMarker]) >= 0) {
                    break
                }
            }
            // check if markers cross
            if (leftMarker >= rightMarker) {
                break
            }
            // swap
            ordered.swap(leftMarker, rightMarker)
        }
        ordered.swap(left, rightMarker)
        // return index of item now known to be in place
        return rightMarker
    }

    private fun <T> sort(ordered: Ordered<T>, comparator: Comparator<T>, lo: Int, hi: Int) {
        if (lo >= hi) {
            return
        }
        val itemInPlaceIndex = partition(ordered, comparator, lo, hi)
        sort(ordered, comparator, lo, itemInPlaceIndex - 1)
        sort(ordered, comparator, itemInPlaceIndex + 1, hi)
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

    private fun <T> shuffle(ordered: Ordered<T>) {
        for (index in ordered.indices) {
            val swapIndex = index + uniform(ordered.size - index) // between index and array.size - index
            ordered.swap(index, swapIndex)
        }
    }

    override fun <T> sort(ordered: Ordered<T>, comparator: Comparator<T>) {
        // required for guaranteed performance
        this.shuffle(ordered)
        sort(ordered, comparator, lo = 0, hi = ordered.size - 1)
    }
}
