package alg023stack

import "github.com/deemson/algorithms/alg021deque"

type Stack[T any] struct {
	deque alg021deque.Deque[T]
}

func (q Stack[T]) Push(item T) {
	q.deque.AddFirst(item)
}

func (q Stack[T]) Pop() T {
	return q.deque.RemoveFirst()
}
