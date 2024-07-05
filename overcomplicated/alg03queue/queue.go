package alg03queue

import "github.com/deemson/algorithms/alg02deque"

type Queue[T any] struct {
	deque alg02deque.Deque[T]
}

func (q Queue[T]) Enqueue(item T) {
	q.deque.AddLast(item)
}

func (q Queue[T]) Dequeue() T {
	return q.deque.RemoveFirst()
}
