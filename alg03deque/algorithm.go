package alg03deque

type Algorithm[T any] interface {
	Size() int
	Get(index int) T
	Range(fromIndex, toIndex int) []T
	Set(index int, item T)
	AddAtIndex(index int, item T)
	RemoveAtIndex(index int) T
}
