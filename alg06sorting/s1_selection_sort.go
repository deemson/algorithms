package alg06sorting

// SelectionSort does ~N^2/2 compares and N swaps
func SelectionSort[T any](slice []T, less Less[T]) {
	// Outer loop that iterates through all the items with the intention to swap at the end.
	for outerLoopIndex := 0; outerLoopIndex < len(slice); outerLoopIndex++ {
		// Each iteration of the outer loop it tries to find the minimum item index to the right
		// of the current index and swap this item with the current item.
		minimumItemIndex := outerLoopIndex
		// Inner loop tries to find the minimum item to the right of the current one.
		// This is why it starts at the current index of the outer loop.
		for innerLoopIndex := outerLoopIndex; innerLoopIndex < len(slice); innerLoopIndex++ {
			if less(slice[innerLoopIndex], slice[minimumItemIndex]) {
				minimumItemIndex = innerLoopIndex
			}
		}
		swap(slice, outerLoopIndex, minimumItemIndex)
	}
}
