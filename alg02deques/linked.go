package alg02deques

// check interface compliance
var _ Deque[any] = &LinkedDeque[any]{}

type LinkedDeque[T any] struct {
	size int
}

func (d *LinkedDeque[T]) ToSlice() []T {
	return toSlice[T](d)
}

func (d *LinkedDeque[T]) Size() int {
	return d.size
}

func (d *LinkedDeque[T]) IsEmpty() bool {
	return isEmpty[T](d)
}

func (d *LinkedDeque[T]) Get(index int) T {
	//TODO implement me
	panic("implement me")
}

func (d *LinkedDeque[T]) AddFirst(item T) {
	//TODO implement me
	panic("implement me")
}

func (d *LinkedDeque[T]) AddLast(item T) {
	//TODO implement me
	panic("implement me")
}

func (d *LinkedDeque[T]) RemoveFirst() T {
	//TODO implement me
	panic("implement me")
}

func (d *LinkedDeque[T]) RemoveLast() T {
	//TODO implement me
	panic("implement me")
}
