package alg03sorting

import kotlin.random.Random

object S5QuickSort : AbstractBaseSort() {
    /**
     * This function is the core of quicksort. It ensures that an arbitrary item from the part of the array
     * from leftThreshold to rightThreshold is in place, i.e. smaller than all of the items to the left
     * and larger than all of the items to the right of this element.
     * Method partition returns an index of the element, that was put in place.
     */
    private fun <T> partition(
        array: Array<T>,
        comparator: Comparator<T>,
        leftThreshold: Int,
        rightThreshold: Int
    ): Int {
        var leftMarker = leftThreshold
        var rightMarker = rightThreshold + 1
        while (true) {
            // find item on the left to swap
            while (comparator.compare(array[++leftMarker], array[leftThreshold]) < 0) {
                if (leftMarker == rightThreshold) {
                    break
                }
            }
            // find item on the right to swap
            while (true) {
                if (comparator.compare(array[leftThreshold], array[--rightMarker]) >= 0) {
                    break
                }
            }
            // check if markers cross
            if (leftMarker >= rightMarker) {
                break
            }
            // swap
            exchange(array, leftMarker, rightMarker)
        }
        exchange(array, leftThreshold, rightMarker)
        // return index of item now known to be in place
        return rightMarker
    }

    private fun <T> sort(array: Array<T>, comparator: Comparator<T>, lo: Int, hi: Int) {
        if (lo >= hi) {
            return
        }
        val elementInPlaceIndex = partition(array, comparator, lo, hi)
        sort(array, comparator, lo, elementInPlaceIndex - 1)
        sort(array, comparator, elementInPlaceIndex + 1, hi)
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

    private fun <T> shuffle(array: Array<T>) {
        for (index in array.indices) {
            val swapIndex = index + uniform(array.size - index) // between index and array.size - index
            val temp = array[index]
            array[index] = array[swapIndex]
            array[swapIndex] = temp
        }
    }

    override fun <T> sort(array: Array<T>, comparator: Comparator<T>) {
        // required for guaranteed performance
        this.shuffle(array)
        sort(array, comparator, lo = 0, hi = array.size - 1)
    }
}
