package alg06sorting

// InsertionSort does on average ~1/4N^2 compares and ~1/4N^2 swaps.
// With partially sorted arrays, though, it's performance can be close to linear.
func InsertionSort[T any](slice []T, less Less[T]) {
	for outerLoopIndex := 1; outerLoopIndex < len(slice); outerLoopIndex++ {
		// Every added item tries to find its place in the left part (sorted part) of the entire array.
		for innerLoopIndex := outerLoopIndex; innerLoopIndex > 0; innerLoopIndex-- {
			if less(slice[innerLoopIndex], slice[innerLoopIndex-1]) {
				swap(slice, innerLoopIndex, innerLoopIndex-1)
			}
		}
	}
}
