package alg05binaryheap

import (
	"fmt"
)

func ParentIndex(index int) int {
	return (index - 1) / 2
}

func ChildIndex(index int) int {
	return (index+1)*2 - 1
}

type Less[T any] func(item1, item2 T) bool

func BinaryHeapOrder[T any](slice []T, less Less[T]) {
	// Starting at the middle as half of the array are leaf nodes and there's no point in sinking them
	for index := len(slice)/2 - 1; index >= 0; index-- {
		SinkToBottom(slice, less, index)
	}
}

func ValidateBinaryHeapOrder[T any](slice []T, less Less[T]) error {
	parentIndex := ParentIndex(len(slice) - 1)
	for parentIndex <= 0 {
		childIndex := ChildIndex(parentIndex)
		if less(slice[childIndex], slice[parentIndex]) {
			return fmt.Errorf(
				`binary heap order violated parent %#v at index %d; left child %#v at index %d`,
				slice[parentIndex],
				parentIndex,
				slice[childIndex],
				childIndex,
			)
		}
		childIndex++
		if less(slice[childIndex], slice[parentIndex]) {
			return fmt.Errorf(
				`binary heap order violated parent %#v at index %d; right child %#v at index %d`,
				slice[parentIndex],
				parentIndex,
				slice[childIndex],
				childIndex,
			)
		}
	}
	return nil
}
