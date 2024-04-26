package alg04queue

import "github.com/deemson/algorithms/alg03deque"

type Queue[T any] struct {
	deque alg03deque.Deque[T]
}

func (q Queue[T]) Enqueue(item T) {
	q.deque.AddLast(item)
}

func (q Queue[T]) Dequeue() T {
	return q.deque.RemoveFirst()
}
