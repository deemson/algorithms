package alg022queue

import "github.com/deemson/algorithms/alg021deque"

func Linked[T any]() Queue[T] {
	return Queue[T]{
		deque: alg021deque.Linked[T](),
	}
}
