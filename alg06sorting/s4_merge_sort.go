package alg06sorting

// MergeSort is a divide and conquer (split-sort-merge) algorithm.
// The execution time is as fast as O(N*log2(N)).
// Traditional implementation via recursion.
func MergeSort[T any](slice []T, less Less[T]) {
	aux := make([]T, len(slice))
	mergeSort(slice, aux, less, 0, len(slice)-1)
}

// BottomUpMergeSort implements the same merge sort only without recursion
func BottomUpMergeSort[T any](slice []T, less Less[T]) {
	aux := make([]T, len(slice))
	// partitionSize size grows as 1 2 4 8 ...
	partitionSize := 1
	for partitionSize < len(slice) {
		for lo := 0; lo < len(slice)-partitionSize; lo += 2 * partitionSize {
			mid := lo + partitionSize - 1
			hi := min(lo+2*partitionSize-1, len(slice)-1)
			merge(slice, aux, less, lo, mid, hi)
		}
		partitionSize += partitionSize
	}
}

func mergeSort[T any](slice, aux []T, less Less[T], lo, hi int) {
	// If the thresholds overlap the sorting is done.
	if lo >= hi {
		return
	}
	// Divide and conquer in action: the array is sliced in two parts...
	mid := lo + (hi-lo)/2
	// ...and each part is sorted independently...
	mergeSort(slice, aux, less, lo, mid)
	mergeSort(slice, aux, less, mid+1, hi)
	// ... and the two parts are merged back together.
	merge(slice, aux, less, lo, mid, hi)
}

// merge is a core function in a divide and conquer algorithms of MergeSort and BottomUpMergeSort.
// merge merges two parts of the indexed array together via temporary aux storage array.
// It assumes that both halves of the array are sorted individually, and they need to be merged
// together so that the result is sorted as well.
func merge[T any](slice, aux []T, less Less[T], lo, mid, hi int) {
	for index := lo; index <= hi; index++ {
		aux[index] = slice[index]
	}
	// leftMarker moves from lo to mid
	leftMarker := lo
	// rightMarker moves from mid+1 to hi
	rightMarker := mid + 1
	for index := lo; index <= hi; index++ {
		switch {
		// When the left marker has crossed the middle point, it means there's nothing left
		// in the left part and the remainder of the right part can just be copied.
		case leftMarker > mid:
			slice[index] = aux[rightMarker]
			rightMarker++
		// The same goes for the right part when the right marker crosses hi threshold.
		case rightMarker > hi:
			slice[index] = aux[leftMarker]
			leftMarker++
		// At this point both parts are not exhausted,
		// so the smaller item from either left or right side
		// is copied to 'indexed' and respective marker is increased.
		case less(aux[rightMarker], aux[leftMarker]):
			slice[index] = aux[rightMarker]
			rightMarker++
		default: // aux[leftMarker] <= aux[rightMarker]
			slice[index] = aux[leftMarker]
			leftMarker++
		}
	}
}
