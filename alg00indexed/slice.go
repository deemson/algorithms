package alg00indexed

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

func (a SliceAdapter[T]) Range(fromIndex, toIndex int) []T {
	return a.Slice[fromIndex:toIndex]
}

func (a SliceAdapter[T]) Set(index int, item T) {
	a.Slice[index] = item
}
