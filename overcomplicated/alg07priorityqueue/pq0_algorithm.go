package alg07priorityqueue

type Algorithm[T any] interface {
	Push(item T)
	Pop() T
}
