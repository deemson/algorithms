package alg03queue

import "github.com/deemson/algorithms/alg02deque"

func Linked[T any]() Queue[T] {
	return Queue[T]{
		deque: alg02deque.Linked[T](),
	}
}
