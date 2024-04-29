package alg05binaryheap

import (
	"fmt"
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/deemson/algorithms/alg00less"
)

func ParentIndex(index int) int {
	return (index - 1) / 2
}

func ChildIndex(index int) int {
	return (index+1)*2 - 1
}

func BinaryHeapOrder[T any](indexed alg00indexed.Indexed[T], less alg00less.Less[T]) {
	// Starting at the middle as half of the array are leaf nodes and there's no point in sinking them
	for index := indexed.Size()/2 - 1; index >= 0; index-- {
		SinkToBottom(indexed, less, index)
	}
}

func ValidateBinaryHeapOrder[T any](indexed alg00indexed.Indexed[T], less alg00less.Less[T]) error {
	parentIndex := ParentIndex(indexed.Size() - 1)
	for parentIndex <= 0 {
		childIndex := ChildIndex(parentIndex)
		if less(indexed.Get(childIndex), indexed.Get(parentIndex)) {
			return fmt.Errorf(
				`binary heap order violated parent %#v at index %d; left child %#v at index %d`,
				indexed.Get(parentIndex),
				parentIndex,
				indexed.Get(childIndex),
				childIndex,
			)
		}
		childIndex++
		if less(indexed.Get(childIndex), indexed.Get(parentIndex)) {
			return fmt.Errorf(
				`binary heap order violated parent %#v at index %d; right child %#v at index %d`,
				indexed.Get(parentIndex),
				parentIndex,
				indexed.Get(childIndex),
				childIndex,
			)
		}
	}
	return nil
}
