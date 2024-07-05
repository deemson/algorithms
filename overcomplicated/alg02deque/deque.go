package alg02deque

import (
	"fmt"
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/deemson/algorithms/alg00iterator"
)

type Deque[T any] struct {
	algorithm Algorithm[T]
}

func (d Deque[T]) ToSlice() []T {
	return alg00indexed.ToSlice[T](d.algorithm)
}

func (d Deque[T]) Size() int {
	return d.algorithm.Size()
}

func (d Deque[T]) IsEmpty() bool {
	return d.Size() == 0
}

func (d Deque[T]) Get(index int) T {
	ensureIndexLessThanSize(index, d.Size())
	return d.algorithm.Get(index)
}

func (d Deque[T]) Set(index int, item T) {
	ensureIndexLessThanSize(index, d.Size())
	d.algorithm.Set(index, item)
}

func (d Deque[T]) Iterator() alg00iterator.Iterator[T] {
	return d.algorithm.Iterator()
}

func (d Deque[T]) AddAtIndex(index int, item T) {
	ensureIndexLessOrEqualSize(index, d.Size())
	d.algorithm.AddAtIndex(index, item)
}

func (d Deque[T]) AddFirst(item T) {
	d.AddAtIndex(0, item)
}

func (d Deque[T]) AddLast(item T) {
	d.AddAtIndex(d.Size(), item)
}

func (d Deque[T]) AddAllLast(items ...T) {
	for _, item := range items {
		d.AddLast(item)
	}
}

func (d Deque[T]) RemoveAtIndex(index int) T {
	ensureNotEmpty(d.Size())
	ensureIndexLessThanSize(index, d.Size())
	return d.algorithm.RemoveAtIndex(index)
}

func (d Deque[T]) RemoveFirst() T {
	return d.RemoveAtIndex(0)
}

func (d Deque[T]) RemoveLast() T {
	return d.RemoveAtIndex(d.Size() - 1)
}

func ensureIndexNotNegative(index int) {
	if index < 0 {
		panic(fmt.Sprintf("Deque index must not be negative (%d)", index))
	}
}

func ensureIndexLessThanSize(index int, size int) {
	ensureIndexNotNegative(index)
	if index >= size {
		panic(fmt.Sprintf("Deque index (%d) must be less than size (%d)", index, size))
	}
}

func ensureIndexLessOrEqualSize(index int, size int) {
	ensureIndexNotNegative(index)
	if index > size {
		panic(fmt.Sprintf("Deque index (%d) must be less or equal size (%d)", index, size))
	}
}

func ensureNotEmpty(size int) {
	if size == 0 {
		panic("Deque must not be empty")
	}
}
