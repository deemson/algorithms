package alg04queue

import "github.com/deemson/algorithms/alg03deque"

func Linked[T any]() Queue[T] {
	return Queue[T]{
		deque: alg03deque.Linked[T](),
	}
}
