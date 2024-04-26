package alg04queue

import (
	"github.com/deemson/algorithms/alg03deque"
)

func Array[T any](capacity int) Queue[T] {
	return Queue[T]{
		deque: alg03deque.Array[T](capacity),
	}
}
