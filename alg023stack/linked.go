package alg023stack

import "github.com/deemson/algorithms/alg021deque"

func Linked[T any]() Stack[T] {
	return Stack[T]{
		deque: alg021deque.Linked[T](),
	}
}
