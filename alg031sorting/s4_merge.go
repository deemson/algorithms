package alg031sorting

import "github.com/deemson/algorithms/alg020indexed"

// merge is a core function in a divide and conquer algorithms of MergeSort and BottomUpMergeSort.
// merge merges two parts of the indexed array together via temporary aux storage array.
// It assumes that both halves of the array are sorted individually and they need to be merged
// together so that the result is sorted as well.
func merge[T any](indexed, aux alg020indexed.Indexed[T], less LessFunc[T], lo, mid, hi int) {
	for index := lo; index <= hi; index++ {
		aux.Set(index, indexed.Get(index))
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
			indexed.Set(index, aux.Get(rightMarker))
			rightMarker++
		// The same goes for the right part when the right marker crosses hi threshold.
		case rightMarker > hi:
			indexed.Set(index, aux.Get(leftMarker))
			leftMarker++
		// At this point both parts are not exhausted,
		// so the smaller item from either left or right side
		// is copied to 'indexed' and respective marker is increased.
		case less(aux.Get(rightMarker), aux.Get(leftMarker)):
			indexed.Set(index, aux.Get(rightMarker))
			rightMarker++
		default: // lessOrEqual(aux.Get(leftMarker), aux.Get(rightMarker))
			indexed.Set(index, aux.Get(leftMarker))
			leftMarker++
		}
	}
}
