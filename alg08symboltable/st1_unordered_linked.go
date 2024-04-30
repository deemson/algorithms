package alg08symboltable

import "github.com/deemson/algorithms/alg02deque"

func UnorderedLinked[K, V any]() SymbolTable[K, V] {
	return SymbolTable[K, V]{
		algorithm: UnorderedLinkedAlgorithm[K, V]{
			deque: alg02deque.Linked[keyValuePair[K, V]](),
		},
	}
}

type UnorderedLinkedAlgorithm[K, V any] struct {
	deque alg02deque.Deque[keyValuePair[K, V]]
}

func (a UnorderedLinkedAlgorithm[K, V]) Get(key K) (V, bool) {
	//TODO implement me
	panic("implement me")
}

func (a UnorderedLinkedAlgorithm[K, V]) Set(key K, value V) {
	//TODO implement me
	panic("implement me")
}

func (a UnorderedLinkedAlgorithm[K, V]) Delete(key K) bool {
	//TODO implement me
	panic("implement me")
}
