package alg023stack

import (
	"github.com/deemson/algorithms/alg021deque"
)

func Array[T any](capacity int) Stack[T] {
	return Stack[T]{
		deque: alg021deque.Array[T](capacity),
	}
}
