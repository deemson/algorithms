package alg05stack

import (
	"github.com/deemson/algorithms/alg03deque"
)

func Array[T any](capacity int) Stack[T] {
	return Stack[T]{
		deque: alg03deque.Array[T](capacity),
	}
}
