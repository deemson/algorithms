package alg07priorityqueue

import (
	"github.com/deemson/algorithms/alg00compare"
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/deemson/algorithms/alg02deque"
	"github.com/deemson/algorithms/alg05binaryheap"
)

func BinaryHeap[T any](less alg00compare.LessFunc[T], capacity int) PriorityQueue[T] {
	return PriorityQueue[T]{
		algorithm: BinaryHeapAlgorithm[T]{
			deque: alg02deque.Array[T](capacity),
			less:  less,
		},
	}
}

type BinaryHeapAlgorithm[T any] struct {
	deque alg02deque.Deque[T]
	less  alg00compare.LessFunc[T]
}

func (a BinaryHeapAlgorithm[T]) Push(item T) {
	a.deque.AddLast(item)
	if a.deque.Size() > 1 {
		alg05binaryheap.SwimFromBottomToTop(a.deque, a.less)
	}
}

func (a BinaryHeapAlgorithm[T]) Pop() T {
	alg00indexed.Swap[T](a.deque, 0, a.deque.Size()-1)
	item := a.deque.RemoveLast()
	if a.deque.Size() > 1 {
		alg05binaryheap.SinkFromTopToBottom(a.deque, a.less)
	}
	return item
}
