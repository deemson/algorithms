package alg08symboltable

import (
	"github.com/deemson/algorithms/alg00compare"
	"github.com/deemson/algorithms/alg00iterator"
	"github.com/deemson/algorithms/alg02deque"
)

func UnorderedLinked[K, V any](equal alg00compare.EqualFunc[K]) SymbolTable[K, V] {
	return SymbolTable[K, V]{
		algorithm: UnorderedLinkedAlgorithm[K, V]{
			deque: alg02deque.Linked[keyValuePair[K, V]](),
			equal: equal,
		},
	}
}

type UnorderedLinkedAlgorithm[K, V any] struct {
	deque alg02deque.Deque[keyValuePair[K, V]]
	equal alg00compare.EqualFunc[K]
}

func (a UnorderedLinkedAlgorithm[K, V]) Size() int {
	return a.deque.Size()
}

func (a UnorderedLinkedAlgorithm[K, V]) Get(key K) (V, bool) {
	iterator := a.deque.Iterator()
	for iterator.HasNext() {
		pair := iterator.Next()
		if a.equal(pair.key, key) {
			return pair.value, true
		}
	}
	var value V
	return value, false
}

func (a UnorderedLinkedAlgorithm[K, V]) Set(key K, value V) {
	iterator := a.deque.Iterator()
	indexToReplace := -1
	index := 0
	for iterator.HasNext() {
		pair := iterator.Next()
		if a.equal(pair.key, key) {
			indexToReplace = index
			break
		}
		index++
	}
	pair := keyValuePair[K, V]{
		key:   key,
		value: value,
	}
	if indexToReplace > -1 {
		a.deque.Set(indexToReplace, pair)
	} else {
		a.deque.AddLast(pair)
	}
}

func (a UnorderedLinkedAlgorithm[K, V]) Delete(key K) bool {
	iterator := a.deque.Iterator()
	indexToDelete := -1
	index := 0
	for iterator.HasNext() {
		pair := iterator.Next()
		if a.equal(pair.key, key) {
			indexToDelete = index
			break
		}
		index++
	}
	if indexToDelete > -1 {
		a.deque.RemoveAtIndex(indexToDelete)
		return true
	}
	return false
}

func (a UnorderedLinkedAlgorithm[K, V]) Keys() alg00iterator.Iterator[K] {
	return alg00iterator.Unpack(a.deque.Iterator(), func(item keyValuePair[K, V]) K {
		return item.key
	})
}
