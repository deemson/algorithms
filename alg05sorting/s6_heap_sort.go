package alg05sorting

import (
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/deemson/algorithms/alg00less"
	"github.com/deemson/algorithms/alg06binaryheap"
)

func HeapSort[T any](indexed alg00indexed.Indexed[T], less alg00less.Less[T]) {
	reversedLess := alg00less.Reversed(less)
	// Binary heap order with reversed less so that max (instead of min) element is on top
	alg06binaryheap.BinaryHeapOrder(indexed, reversedLess)
	for index := indexed.Size() - 1; index > 0; index-- {
		// move max element in place (first to the end, second to the end-1, etc)
		alg00indexed.Swap(indexed, 0, index)
		// as binary heap order now might be violated when we moved arbitrary element
		// to the top, we sink it down to the last unsorted index
		alg06binaryheap.SinkFromTop(indexed, reversedLess, index-1)
	}
}
