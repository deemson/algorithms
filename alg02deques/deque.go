package alg02deques

type Deque[T any] interface {
	ToSlice() []T
	Size() int
	IsEmpty() bool
	Get(index int) T
	AddFirst(item T)
	AddLast(item T)
	AddAtIndex(index int, item T)
	RemoveFirst() T
	RemoveLast() T
	RemoveAtIndex(index int) T
}

func isEmpty[T any](deque Deque[T]) bool {
	return deque.Size() == 0
}

func toSlice[T any](deque Deque[T]) []T {
	slice := make([]T, deque.Size())
	for index := 0; index < deque.Size(); index++ {
		slice[index] = deque.Get(index)
	}
	return slice
}
