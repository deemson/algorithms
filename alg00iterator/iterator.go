package alg00iterator

type Iterator[T any] struct {
	HasNext func() bool
	Next    func() T
}

func Unpack[P, U any](iterator Iterator[P], f func(item P) U) Iterator[U] {
	return Iterator[U]{
		HasNext: iterator.HasNext,
		Next: func() U {
			return f(iterator.Next())
		},
	}
}
