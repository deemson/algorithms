package alg08symboltable

import "github.com/deemson/algorithms/alg00iterator"

type Algorithm[K, V any] interface {
	Size() int
	Get(key K) (V, bool)
	Set(key K, value V)
	Delete(key K) bool
	Keys() alg00iterator.Iterator[K]
}
