package alg05stack

import "github.com/deemson/algorithms/alg03deque"

func Linked[T any]() Stack[T] {
	return Stack[T]{
		deque: alg03deque.Linked[T](),
	}
}
