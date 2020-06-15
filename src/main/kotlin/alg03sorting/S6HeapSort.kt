package alg03sorting

import alg03comparators.ReverseComparator
import alg03indexable.Indexable
import alg03indexable.swap
import alg04binaryheap.parentIndex
import alg04binaryheap.sink

/**
 * Pros: guaranteed sorting in-place and in linearithmic time
 * Cons: many comparisons and memory references are all over the place
 *
 * This complicates hardware cache optimizations and therefore heap sort not used in practice that much.
 * There are improved variations of quicksort and merge sort that can sort in-place and have guaranteed
 * linearithmic performance, however the code is much more complicated.
 */
object S6HeapSort : Sort {
    override fun <T> sort(indexable: Indexable<T>, comparator: Comparator<T>) {
        val reverseComparator = ReverseComparator(comparator)
        val firstParent = parentIndex(indexable.size - 1)
        // "Heapify" indexable
        for (index in firstParent downTo 0) {
            indexable.sink(reverseComparator, atIndex = index, downToIndex = indexable.size - 1)
        }
        for (index in indexable.size - 2 downTo 0) {
            indexable.swap(0, index + 1)
            indexable.sink(reverseComparator, atIndex = 0, downToIndex = index)
        }
    }
}
