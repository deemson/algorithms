package alg06binaryheap

import (
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/deemson/algorithms/alg00less"
)

func Swim[T any](indexed alg00indexed.Indexed[T], less alg00less.Less[T], fromIndex, toIndex int) {
	childIndex := fromIndex
	parentIndex := ParentIndex(childIndex)
	for parentIndex >= toIndex && less(indexed.Get(childIndex), indexed.Get(parentIndex)) {
		alg00indexed.Swap(indexed, childIndex, parentIndex)
		childIndex = parentIndex
		parentIndex = ParentIndex(childIndex)
	}
}

func SwimToTop[T any](indexed alg00indexed.Indexed[T], less alg00less.Less[T], fromIndex int) {
	Swim(indexed, less, fromIndex, 0)
}

func SwimFromBottom[T any](indexed alg00indexed.Indexed[T], less alg00less.Less[T], toIndex int) {
	Swim(indexed, less, indexed.Size()-1, toIndex)
}

func SwimFromBottomToTop[T any](indexed alg00indexed.Indexed[T], less alg00less.Less[T]) {
	SwimFromBottom(indexed, less, 0)
}
