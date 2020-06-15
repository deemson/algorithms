package alg03sorting

import alg03indexable.Indexable
import alg03indexable.swap
import kotlin.random.Random

object S5QuickSort : Sort {
    /**
     * This function is the core of quicksort. It ensures that an arbitrary item from the part of the array
     * from left to right is in place, i.e. smaller than all of the items to the left
     * and larger than all of the items to the right of this item.
     * Method partition returns an index of the item, that was put in place.
     */
    private fun <T> partition(indexable: Indexable<T>, comparator: Comparator<T>, left: Int, right: Int): Int {
        var leftMarker = left
        var rightMarker = right + 1
        while (true) {
            // find item on the left to swap
            while (comparator.compare(indexable[++leftMarker], indexable[left]) < 0) {
                if (leftMarker == right) {
                    break
                }
            }
            // find item on the right to swap
            while (true) {
                if (comparator.compare(indexable[left], indexable[--rightMarker]) >= 0) {
                    break
                }
            }
            // check if markers cross
            if (leftMarker >= rightMarker) {
                break
            }
            // swap
            indexable.swap(leftMarker, rightMarker)
        }
        indexable.swap(left, rightMarker)
        // return index of item now known to be in place
        return rightMarker
    }

    private fun <T> sort(indexable: Indexable<T>, comparator: Comparator<T>, lo: Int, hi: Int) {
        if (lo >= hi) {
            return
        }
        val itemInPlaceIndex = partition(indexable, comparator, lo, hi)
        sort(indexable, comparator, lo, itemInPlaceIndex - 1)
        sort(indexable, comparator, itemInPlaceIndex + 1, hi)
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

    private fun <T> shuffle(indexable: Indexable<T>) {
        for (index in indexable.indices) {
            val swapIndex = index + uniform(indexable.size - index) // between index and array.size - index
            indexable.swap(index, swapIndex)
        }
    }

    override fun <T> sort(indexable: Indexable<T>, comparator: Comparator<T>) {
        // required for guaranteed performance
        this.shuffle(indexable)
        sort(indexable, comparator, lo = 0, hi = indexable.size - 1)
    }
}
