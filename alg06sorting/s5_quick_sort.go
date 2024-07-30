package alg06sorting

import (
	"math/rand"
)

func QuickSort[T any](slice []T, less Less[T]) {
	shuffle(slice)
	quickSort(slice, less, 0, len(slice)-1)
}

func shuffle[T any](slice []T) {
	for index := 0; index < len(slice); index++ {
		shuffledIndex := rand.Intn(index + 1)
		swap(slice, index, shuffledIndex)
	}
}

func quickSort[T any](slice []T, less Less[T], left, right int) {
	if left >= right {
		return
	}
	itemInPlaceIndex := partition(slice, less, left, right)
	quickSort(slice, less, left, itemInPlaceIndex-1)
	quickSort(slice, less, itemInPlaceIndex+1, right)
}

// partition is the core of QuickSort. It ensures that randomly picked item from the array
// is put in such a position, so that all the items to the left are smaller and all items to the right
// are bigger. Function partition returns the index of the element after this is done.
func partition[T any](slice []T, less Less[T], left, right int) int {
	partitioningItem := slice[left]
	leftMarker := left + 1
	rightMarker := right
	for {
		// move left marker as long as items are less than partitioning item
		// stop at first item that is not
		for less(slice[leftMarker], partitioningItem) {
			if leftMarker == right {
				break
			}
			leftMarker++
		}
		// move right marker as long as items are not less than partitioning item
		// stop at first item that is
		for !less(slice[rightMarker], partitioningItem) {
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
		swap(slice, leftMarker, rightMarker)
	}
	// put partitioned element in place
	swap(slice, left, rightMarker)
	// return it's current index
	return rightMarker
}
