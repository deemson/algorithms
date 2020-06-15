package alg04binaryheap

import alg03comparators.MaxComparator
import alg03indexable.Indexable
import alg03indexable.swap

private fun <T> Indexable<T>.assertNotEmpty() {
    if (this.isEmpty) {
        throw IllegalStateException("indexable is empty")
    }
}

private fun assertFromIndex(fromIndex: Int) {
    if (fromIndex < 0) {
        throw IllegalStateException("from index must be positive (got $fromIndex)")
    }
}

private fun <T> Indexable<T>.assertToIndex(toIndex: Int) {
    if (toIndex >= this.size) {
        throw IllegalStateException("to index must be smaller than indexable size (got $toIndex < ${this.size})")
    }
}

private fun assertIndexesRange(fromIndex: Int, downToIndex: Int) {
    if (fromIndex >= downToIndex) {
        throw IllegalStateException("fromIndex must be smaller than downToIndex (got $fromIndex < $downToIndex)")
    }
}

private fun parentIndex(index: Int): Int {
    return (index + 1) / 2 - 1
}

private fun childIndex(index: Int): Int {
    return (index + 1) * 2 - 1
}

private fun <T> Indexable<T>.assertBinaryHeapOrder(comparator: Comparator<T>, parentIndex: Int, childIndex: Int) {
    if (comparator.compare(this[parentIndex], this[childIndex]) > 0) {
        throw IllegalStateException(
            "value ${this[parentIndex]} at index $parentIndex violates binary " +
                "heap order with a child ${this[childIndex]} at index $childIndex"
        )
    }
}

fun <T> Indexable<T>.assertBinaryHeap(comparator: Comparator<T>, atIndex: Int = 0, downToIndex: Int = this.size - 1) {
    this.assertNotEmpty()
    assertFromIndex(atIndex)
    this.assertToIndex(downToIndex)
    assertIndexesRange(atIndex, downToIndex)
    var index = atIndex
    var childIndex = childIndex(index)
    while (childIndex <= downToIndex) {
        this.assertBinaryHeapOrder(comparator, index, childIndex)
        if (childIndex < downToIndex) {
            this.assertBinaryHeapOrder(comparator, index, childIndex + 1)
        }
        index = childIndex
        childIndex = childIndex(index)
    }
}

fun <T : Comparable<T>> Indexable<T>.assertBinaryHeap(atIndex: Int = 0, downToIndex: Int = this.size - 1) {
    return this.assertBinaryHeap(MaxComparator(), atIndex, downToIndex)
}

fun <T> Indexable<T>.sink(comparator: Comparator<T>, atIndex: Int = 0, downToIndex: Int = this.size - 1) {
    this.assertNotEmpty()
    assertFromIndex(atIndex)
    this.assertToIndex(downToIndex)
    if (this.size == 1) {
        return
    }
    assertIndexesRange(atIndex, downToIndex)
    var index = atIndex
    var childIndex = childIndex(index)
    while (childIndex <= downToIndex) {
        if (childIndex < downToIndex && comparator.compare(this[childIndex + 1], this[childIndex]) <= 0) {
            childIndex++
        }
        if (comparator.compare(this[index], this[childIndex]) <= 0) {
            break
        }
        this.swap(index, childIndex)
        index = childIndex
        childIndex = childIndex(index)
    }
}

fun <T : Comparable<T>> Indexable<T>.sink(atIndex: Int = 0, downToIndex: Int = this.size - 1) {
    return this.sink(MaxComparator(), atIndex, downToIndex)
}

fun <T> Indexable<T>.swim(comparator: Comparator<T>, atIndex: Int = this.size - 1, upToIndex: Int = 0) {
    this.assertNotEmpty()
    assertFromIndex(upToIndex)
    this.assertToIndex(atIndex)
    if (this.size == 1) {
        return
    }
    assertIndexesRange(upToIndex, atIndex)
    var index = atIndex
    var parentIndex = parentIndex(index)
    while (parentIndex >= upToIndex && comparator.compare(this[index], this[parentIndex]) < 0) {
        this.swap(index, parentIndex)
        index = parentIndex
        parentIndex = parentIndex(index)
    }
}

fun <T : Comparable<T>> Indexable<T>.swim(atIndex: Int = this.size - 1, upToIndex: Int = 0) {
    return this.swim(MaxComparator(), atIndex, upToIndex)
}
