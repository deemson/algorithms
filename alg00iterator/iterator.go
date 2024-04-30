package alg00iterator

type Iterator[T any] struct {
	HasNext func() bool
	Next    func() T
}
