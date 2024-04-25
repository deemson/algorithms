package alg02deque

type Deque[T any] struct {
	Algorithm[T]
}

func (d Deque[T]) ToSlice() []T {
	slice := make([]T, d.Size())
	for index := 0; index < d.Size(); index++ {
		slice[index] = d.Get(index)
	}
	return slice
}

func (d Deque[T]) IsEmpty() bool {
	return d.Size() == 0
}

func (d Deque[T]) AddFirst(item T) {
	d.AddAtIndex(0, item)
}

func (d Deque[T]) AddLast(item T) {
	d.AddAtIndex(d.Size(), item)
}
