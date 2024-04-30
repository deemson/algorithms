package alg00indexed

import "github.com/deemson/algorithms/alg00iterator"

func Slice[T any](slice []T) SliceAdapter[T] {
	return SliceAdapter[T]{
		Slice: slice,
	}
}

type SliceAdapter[T any] struct {
	Slice []T
}

func (a SliceAdapter[T]) Size() int {
	return len(a.Slice)
}

func (a SliceAdapter[T]) Get(index int) T {
	return a.Slice[index]
}

func (a SliceAdapter[T]) Set(index int, item T) {
	a.Slice[index] = item
}

func (a SliceAdapter[T]) Iterator() alg00iterator.Iterator[T] {
	index := 0
	return alg00iterator.Iterator[T]{
		HasNext: func() bool {
			return index < a.Size()
		},
		Next: func() T {
			index++
			return a.Slice[index-1]
		},
	}
}
