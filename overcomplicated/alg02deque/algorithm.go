package alg02deque

import "github.com/deemson/algorithms/alg00indexed"

type Algorithm[T any] interface {
	alg00indexed.Indexed[T]
	AddAtIndex(index int, item T)
	RemoveAtIndex(index int) T
}
