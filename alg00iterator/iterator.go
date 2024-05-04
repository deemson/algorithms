package alg00iterator

type Iterator[T any] struct {
	HasNext func() bool
	Next    func() T
}

func Empty[T any]() Iterator[T] {
	return Iterator[T]{
		HasNext: func() bool {
			return false
		},
		Next: func() T {
			panic("called Next() for empty iterator")
		},
	}
}

func Unpack[P, U any](iterator Iterator[P], f func(item P) U) Iterator[U] {
	return Iterator[U]{
		HasNext: iterator.HasNext,
		Next: func() U {
			return f(iterator.Next())
		},
	}
}
