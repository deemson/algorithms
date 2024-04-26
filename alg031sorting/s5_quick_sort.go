package alg031sorting

import (
	"github.com/deemson/algorithms/alg020indexed"
	"math/rand"
)

func QuickSort[T any](indexed alg020indexed.Indexed[T], less LessFunc[T]) {
	shuffle(indexed)
	quickSort(indexed, less, 0, indexed.Size()-1)
}

func shuffle[T any](indexed alg020indexed.Indexed[T]) {
	for index := 0; index < indexed.Size(); index++ {
		shuffledIndex := rand.Intn(index + 1)
		alg020indexed.Swap(indexed, index, shuffledIndex)
	}
}

func quickSort[T any](indexed alg020indexed.Indexed[T], less LessFunc[T], left, right int) {
	if left >= right {
		return
	}
	itemInPlaceIndex := partition(indexed, less, left, right)
	quickSort(indexed, less, left, itemInPlaceIndex-1)
	quickSort(indexed, less, itemInPlaceIndex+1, right)
}

// partition is the core of QuickSort. It ensures that randomly picked item from the array
// is put in such a position, so that all the items to the left are smaller and all items to the right
// are bigger. Function partition returns the index of the element after this is done.
func partition[T any](indexed alg020indexed.Indexed[T], less LessFunc[T], left, right int) int {
	partitioningItem := indexed.Get(left)
	leftMarker := left + 1
	rightMarker := right
	for {
		// move left marker as long as items are less than partitioning item
		// stop at first item that is not
		for less(indexed.Get(leftMarker), partitioningItem) {
			if leftMarker == right {
				break
			}
			leftMarker++
		}
		// move right marker as long as items are not less than partitioning item
		// stop at first item that is
		for !less(indexed.Get(rightMarker), partitioningItem) {
			if rightMarker == left {
				break
			}
			rightMarker--
		}
		// check if markers overlap which means the pair to swap was not found
		if leftMarker >= rightMarker {
			break
		}
		// swap the found pair, repeat until condition above is met
		alg020indexed.Swap(indexed, leftMarker, rightMarker)
	}
	// put partitioned element in place
	alg020indexed.Swap(indexed, left, rightMarker)
	// return it's current index
	return rightMarker
}
