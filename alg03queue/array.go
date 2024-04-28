package alg03queue

import (
	"github.com/deemson/algorithms/alg02deque"
)

func Array[T any](capacity int) Queue[T] {
	return Queue[T]{
		deque: alg02deque.Array[T](capacity),
	}
}
