package alg05binaryheap

import (
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/deemson/algorithms/alg00less"
)

func Sink[T any](indexed alg00indexed.Indexed[T], less alg00less.Less[T], fromIndex, toIndex int) {
	parentIndex := fromIndex
	childIndex := ChildIndex(parentIndex)
	for childIndex <= toIndex {
		if childIndex < toIndex && less(indexed.Get(childIndex+1), indexed.Get(childIndex)) {
			childIndex++
		}
		if less(indexed.Get(parentIndex), indexed.Get(childIndex)) {
			break
		}
		alg00indexed.Swap(indexed, parentIndex, childIndex)
		parentIndex = childIndex
		childIndex = ChildIndex(parentIndex)
	}
}

func SinkFromTop[T any](indexed alg00indexed.Indexed[T], less alg00less.Less[T], to int) {
	Sink(indexed, less, 0, to)
}

func SinkToBottom[T any](indexed alg00indexed.Indexed[T], less alg00less.Less[T], fromIndex int) {
	Sink(indexed, less, fromIndex, indexed.Size()-1)
}

func SinkFromTopToBottom[T any](indexed alg00indexed.Indexed[T], less alg00less.Less[T]) {
	SinkToBottom(indexed, less, 0)
}
