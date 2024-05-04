package alg08symboltable

import (
	"github.com/deemson/algorithms/alg00compare"
	"github.com/deemson/algorithms/alg00iterator"
	"github.com/deemson/algorithms/alg02deque"
)

func RankedArray[K, V any](compare alg00compare.Func[K], capacity int) SymbolTable[K, V] {
	return SymbolTable[K, V]{
		algorithm: RankedArrayAlgorithm[K, V]{
			deque:   alg02deque.Array[keyValuePair[K, V]](capacity),
			compare: compare,
		},
	}
}

type RankedArrayAlgorithm[K, V any] struct {
	deque   alg02deque.Deque[keyValuePair[K, V]]
	compare alg00compare.Func[K]
}

func (a RankedArrayAlgorithm[K, V]) Size() int {
	return a.deque.Size()
}

func (a RankedArrayAlgorithm[K, V]) Get(key K) (V, bool) {
	var emptyValue V
	rank := a.rank(key)
	if rank == a.Size() {
		return emptyValue, false
	}
	pairForReturn := a.deque.Get(rank)
	if a.compare.Equal(pairForReturn.key, key) {
		return pairForReturn.value, true
	}
	return emptyValue, false
}

func (a RankedArrayAlgorithm[K, V]) Set(key K, value V) {
	rank := a.rank(key)
	pairToSet := keyValuePair[K, V]{
		key:   key,
		value: value,
	}
	switch {
	case rank == a.Size():
		a.deque.AddLast(pairToSet)
	case a.compare.Equal(a.deque.Get(rank).key, key):
		a.deque.Set(rank, pairToSet)
	default:
		a.deque.AddAtIndex(rank, pairToSet)
	}
}

func (a RankedArrayAlgorithm[K, V]) Delete(key K) bool {
	rank := a.rank(key)
	if rank == a.Size() {
		return false
	}
	pairToDelete := a.deque.Get(rank)
	if a.compare.Equal(pairToDelete.key, key) {
		a.deque.RemoveAtIndex(rank)
		return true
	}
	return false
}

func (a RankedArrayAlgorithm[K, V]) Keys() alg00iterator.Iterator[K] {
	return alg00iterator.Unpack(a.deque.Iterator(), func(item keyValuePair[K, V]) K {
		return item.key
	})
}

// rank returns the number of keys in this symbol table strictly less than key
func (a RankedArrayAlgorithm[K, V]) rank(key K) int {
	lo := 0
	hi := a.deque.Size() - 1
	for lo <= hi {
		mid := lo + (hi-lo)/2
		switch {
		case a.compare.Less(key, a.deque.Get(mid).key):
			hi = mid - 1
		case a.compare.Less(a.deque.Get(mid).key, key):
			lo = mid + 1
		default:
			return mid
		}
	}
	return lo
}
