package alg06sorting

import "github.com/deemson/algorithms/alg02indexed"

// InsertionSort does on average ~1/4N^2 compares and ~1/4N^2 swaps.
// With partially sorted arrays, though, it's performance can be close to linear.
func InsertionSort[T any](indexed alg02indexed.Indexed[T], less LessFunc[T]) {
	for outerLoopIndex := 1; outerLoopIndex < indexed.Size(); outerLoopIndex++ {
		// Every added item tries to find its place in the left part (sorted part) of the entire array.
		for innerLoopIndex := outerLoopIndex; innerLoopIndex > 0; innerLoopIndex-- {
			if less(indexed.Get(innerLoopIndex), indexed.Get(innerLoopIndex-1)) {
				alg02indexed.Swap(indexed, innerLoopIndex, innerLoopIndex-1)
			}
		}
	}
}
