package alg07priorityqueue

import (
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/deemson/algorithms/alg00less"
	"github.com/deemson/algorithms/alg02deque"
)

func OrderedArray[T any](less alg00less.Less[T], capacity int) PriorityQueue[T] {
	return PriorityQueue[T]{
		algorithm: OrderedArrayAlgorithm[T]{
			deque: alg02deque.Array[T](capacity),
			less:  less,
		},
	}
}

type OrderedArrayAlgorithm[T any] struct {
	deque alg02deque.Deque[T]
	less  alg00less.Less[T]
}

func (a OrderedArrayAlgorithm[T]) Push(item T) {
	a.deque.AddLast(item)
	otherItems := alg00indexed.ToSlice[T](a.deque)
	for index := a.deque.Size() - 2; index >= 0; index-- {
		if a.less(otherItems[index], item) {
			break
		}
		alg00indexed.Swap[T](a.deque, index, a.deque.Size()-1)
	}
}

func (a OrderedArrayAlgorithm[T]) Pop() T {
	return a.deque.RemoveFirst()
}
