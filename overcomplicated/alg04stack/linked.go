package alg04stack

import "github.com/deemson/algorithms/alg02deque"

func Linked[T any]() Stack[T] {
	return Stack[T]{
		deque: alg02deque.Linked[T](),
	}
}
