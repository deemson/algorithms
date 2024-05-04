package alg08symboltable

import (
	"github.com/deemson/algorithms/alg00compare"
	"github.com/deemson/algorithms/alg00iterator"
	"github.com/deemson/algorithms/alg02deque"
)

type HashFunc[T any] func(item T) uint32

func HashString(value string) uint32 {
	hash := int32(7)
	for _, character := range value {
		hash = 31*hash + character
	}
	return uint32(hash)
}

func HashInt(value int) uint32 {
	return uint32(value)
}

func makeSliceOfDeques[K, V any](size int) []alg02deque.Deque[keyValuePair[K, V]] {
	slice := make([]alg02deque.Deque[keyValuePair[K, V]], size)
	for index := range slice {
		slice[index] = alg02deque.Linked[keyValuePair[K, V]]()
	}
	return slice
}

func HashTable[K, V any](hash HashFunc[K], equal alg00compare.EqualFunc[K], capacity int, loadFactor float32) SymbolTable[K, V] {
	return SymbolTable[K, V]{
		algorithm: &HashTableAlgorithm[K, V]{
			size:                 0,
			hash:                 hash,
			occupiedSliceIndexes: 0,
			slice:                makeSliceOfDeques[K, V](capacity),
			equal:                equal,
			loadFactor:           loadFactor,
		},
	}
}

type HashTableAlgorithm[K, V any] struct {
	size                 int
	hash                 HashFunc[K]
	occupiedSliceIndexes int
	slice                []alg02deque.Deque[keyValuePair[K, V]]
	equal                alg00compare.EqualFunc[K]
	loadFactor           float32
}

func (a *HashTableAlgorithm[K, V]) Size() int {
	return a.size
}

func (a *HashTableAlgorithm[K, V]) Get(key K) (V, bool) {
	deque := a.slice[a.sliceIndexForKey(key)]
	iterator := deque.Iterator()
	for iterator.HasNext() {
		pair := iterator.Next()
		if a.equal(pair.key, key) {
			return pair.value, true
		}
	}
	var emptyValue V
	return emptyValue, false
}

func (a *HashTableAlgorithm[K, V]) Set(key K, value V) {
	sizeBeforeSet := a.size
	a.set(keyValuePair[K, V]{
		key:   key,
		value: value,
	})
	if a.size > sizeBeforeSet {
		a.growIfRequired()
	}
}

func (a *HashTableAlgorithm[K, V]) Delete(key K) bool {
	deque := a.slice[a.sliceIndexForKey(key)]
	iterator := deque.Iterator()
	index := 0
	for iterator.HasNext() {
		pair := iterator.Next()
		if a.equal(pair.key, key) {
			deque.RemoveAtIndex(index)
			a.size--
			if deque.IsEmpty() {
				a.occupiedSliceIndexes--
			}
			a.shrinkIfRequired()
			return true
		}
		index++
	}
	return false
}

func (a *HashTableAlgorithm[K, V]) Keys() alg00iterator.Iterator[K] {
	count := 0
	currentDequeIterator := alg00iterator.Unpack(a.slice[0].Iterator(), unpackKey[K, V])
	sliceIndex := 0
	return alg00iterator.Iterator[K]{
		HasNext: func() bool {
			return count < a.size
		},
		Next: func() K {
			for !currentDequeIterator.HasNext() {
				sliceIndex++
				currentDequeIterator = alg00iterator.Unpack(a.slice[sliceIndex].Iterator(), unpackKey[K, V])
			}
			count++
			return currentDequeIterator.Next()
		},
	}
}

func (a *HashTableAlgorithm[K, V]) load() float32 {
	return float32(a.occupiedSliceIndexes) / float32(len(a.slice))
}

func (a *HashTableAlgorithm[K, V]) sliceIndexForKey(key K) int {
	return int(a.hash(key) % uint32(len(a.slice)))
}

func (a *HashTableAlgorithm[K, V]) set(pair keyValuePair[K, V]) {
	deque := a.slice[a.sliceIndexForKey(pair.key)]
	if deque.IsEmpty() {
		deque.AddLast(pair)
		a.occupiedSliceIndexes++
		a.size++
		return
	}
	index := 0
	iterator := deque.Iterator()
	for iterator.HasNext() {
		if a.equal(iterator.Next().key, pair.key) {
			deque.Set(index, pair)
			return
		}
		index++
	}
	deque.AddLast(pair)
	a.size++
}

func (a *HashTableAlgorithm[K, V]) resize(capacity int) {
	oldSlice := a.slice
	a.slice = makeSliceOfDeques[K, V](capacity)
	a.occupiedSliceIndexes = 0
	a.size = 0
	for _, deque := range oldSlice {
		iterator := deque.Iterator()
		for iterator.HasNext() {
			a.set(iterator.Next())
		}
	}
}

func (a *HashTableAlgorithm[K, V]) growIfRequired() {
	if a.load() > a.loadFactor {
		a.resize(len(a.slice) * 2)
	}
}

func (a *HashTableAlgorithm[K, V]) shrinkIfRequired() {
	if a.size > 0 && a.load() <= a.loadFactor/4 {
		a.resize(len(a.slice) / 2)
	}
}
