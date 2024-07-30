package alg06sorting

import (
	"github.com/deemson/algorithms/alg05binaryheap"
)

func HeapSort[T any](slice []T, less Less[T]) {
	reversedLess := func(item1, item2 T) bool {
		return less(item2, item1)
	}
	// Binary heap order with reversed less so that max (instead of min) element is on top
	alg05binaryheap.BinaryHeapOrder(slice, reversedLess)
	for index := len(slice) - 1; index > 0; index-- {
		// move max element in place (first to the end, second to the end-1, etc)
		swap(slice, 0, index)
		// as binary heap order now might be violated when we moved arbitrary element
		// to the top, we sink it down to the last unsorted index
		alg05binaryheap.SinkFromTop(slice, reversedLess, index-1)
	}
}
