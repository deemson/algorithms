package alg05sorting

import "github.com/deemson/algorithms/alg00indexed"

// SelectionSort does ~N^2/2 compares and N swaps
func SelectionSort[T any](indexed alg00indexed.Indexed[T], less LessFunc[T]) {
	// Outer loop that iterates through all the items with the intention to swap at the end.
	for outerLoopIndex := 0; outerLoopIndex < indexed.Size(); outerLoopIndex++ {
		// Each iteration of the outer loop it tries to find the minimum item index to the right
		// of the current index and swap this item with the current item.
		mininumItemIndex := outerLoopIndex
		// Inner loop tries to find the minimum item to the right of the current one.
		// This is why it starts at the current index of the outer loop.
		for innerLoopIndex := outerLoopIndex; innerLoopIndex < indexed.Size(); innerLoopIndex++ {
			if less(indexed.Get(innerLoopIndex), indexed.Get(mininumItemIndex)) {
				mininumItemIndex = innerLoopIndex
			}
		}
		alg00indexed.Swap(indexed, outerLoopIndex, mininumItemIndex)
	}
}
