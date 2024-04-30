package alg07priorityqueue

import (
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/deemson/algorithms/alg00less"
	"github.com/deemson/algorithms/alg02deque"
)

func UnorderedArray[T any](less alg00less.Less[T], capacity int) PriorityQueue[T] {
	return PriorityQueue[T]{
		algorithm: UnorderedArrayAlgorithm[T]{
			deque: alg02deque.Array[T](capacity),
			less:  less,
		},
	}
}

// UnorderedArrayAlgorithm scales as O(1) for inserts and as O(N) for deletes
type UnorderedArrayAlgorithm[T any] struct {
	deque alg02deque.Deque[T]
	less  alg00less.Less[T]
}

func (a UnorderedArrayAlgorithm[T]) Push(item T) {
	a.deque.AddLast(item)
}

func (a UnorderedArrayAlgorithm[T]) Pop() T {
	minIndex := 0
	minElement := a.deque.Get(0)
	for index, item := range a.deque.Range(1, a.deque.Size()) {
		if a.less(item, minElement) {
			minIndex = index + 1
			minElement = item
		}
	}
	alg00indexed.Swap[T](a.deque, minIndex, a.deque.Size()-1)
	return a.deque.RemoveLast()
}
