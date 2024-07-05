package alg08symboltable

import (
	"fmt"
	"github.com/deemson/algorithms/alg00compare"
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/deemson/algorithms/alg00iterator"
	"github.com/deemson/algorithms/alg06sorting"
)

type SymbolTable[K, V any] struct {
	algorithm Algorithm[K, V]
}

func (d SymbolTable[K, V]) Size() int {
	return d.algorithm.Size()
}

func (d SymbolTable[K, V]) IsEmpty() bool {
	return d.Size() == 0
}

func (d SymbolTable[K, V]) Get(key K) (V, bool) {
	return d.algorithm.Get(key)
}

func (d SymbolTable[K, V]) MustGet(key K) V {
	item, ok := d.Get(key)
	if !ok {
		panic(fmt.Sprintf("symbol table does not contain key '%#v'", key))
	}
	return item
}

func (d SymbolTable[K, V]) MustGetMany(keys ...K) []V {
	values := make([]V, len(keys))
	for index, key := range keys {
		values[index] = d.MustGet(key)
	}
	return values
}

func (d SymbolTable[K, V]) Set(key K, value V) {
	d.algorithm.Set(key, value)
}

func (d SymbolTable[K, V]) Delete(key K) bool {
	return d.algorithm.Delete(key)
}

func (d SymbolTable[K, V]) MustDelete(key K) {
	if !d.Delete(key) {
		panic(fmt.Sprintf("symbol table does not contain key '%#v'", key))
	}
}

func (d SymbolTable[K, V]) MustDeleteMany(keys ...K) {
	for _, key := range keys {
		d.MustDelete(key)
	}
}

func (d SymbolTable[K, V]) Keys() alg00iterator.Iterator[K] {
	return d.algorithm.Keys()
}

func (d SymbolTable[K, V]) SortedKeysSlice(less alg00compare.LessFunc[K]) []K {
	slice := make([]K, d.Size())
	iterator := d.Keys()
	index := 0
	for iterator.HasNext() {
		slice[index] = iterator.Next()
		index++
	}
	alg06sorting.QuickSort(alg00indexed.Slice(slice), less)
	return slice
}
