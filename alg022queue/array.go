package alg022queue

import (
	"github.com/deemson/algorithms/alg021deque"
)

func Array[T any](capacity int) Queue[T] {
	return Queue[T]{
		deque: alg021deque.Array[T](capacity),
	}
}
