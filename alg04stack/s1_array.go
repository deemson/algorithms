package alg04stack

import "github.com/deemson/algorithms/alg02deque"

func Array[T any](capacity int) Stack[T] {
	return Stack[T]{
		deque: alg02deque.Array[T](capacity),
	}
}
