package alg00indexed

import "github.com/deemson/algorithms/alg00iterator"

type Indexed[T any] interface {
	Size() int
	Get(index int) T
	Set(index int, item T)
	Iterator() alg00iterator.Iterator[T]
}

func Swap[T any](indexed Indexed[T], index1, index2 int) {
	tmp := indexed.Get(index1)
	indexed.Set(index1, indexed.Get(index2))
	indexed.Set(index2, tmp)
}

func ToSlice[T any](indexed Indexed[T]) []T {
	slice := make([]T, indexed.Size())
	index := 0
	iterator := indexed.Iterator()
	for iterator.HasNext() {
		slice[index] = iterator.Next()
		index++
	}
	return slice
}
