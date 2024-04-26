package alg031sorting

import "github.com/deemson/algorithms/alg020indexed"

// MergeSort is a divide and conquer (split-sort-merge) algorithm.
// The execution time is as fast as O(N*log2(N)).
// Traditional implementation via recursion.
func MergeSort[T any](indexed alg020indexed.Indexed[T], less LessFunc[T]) {
	aux := alg020indexed.SliceAdapter[T]{
		Slice: make([]T, indexed.Size()),
	}
	mergeSort(indexed, aux, less, 0, indexed.Size()-1)
}

func mergeSort[T any](indexed, aux alg020indexed.Indexed[T], less LessFunc[T], lo, hi int) {
	// If the thresholds overlap the sorting is done.
	if lo >= hi {
		return
	}
	// Divide and conquer in action: the array is sliced in two parts...
	mid := lo + (hi-lo)/2
	// ...and each part is sorted independently...
	mergeSort(indexed, aux, less, lo, mid)
	mergeSort(indexed, aux, less, mid+1, hi)
	// ... and the two parts are merged back together.
	merge(indexed, aux, less, lo, mid, hi)
}
