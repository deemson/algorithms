package alg021deque

type Algorithm[T any] interface {
	Size() int
	Get(index int) T
	Range(fromIndex, toIndex int) []T
	Set(index int, item T)
	AddAtIndex(index int, item T)
	RemoveAtIndex(index int) T
}

func swap[T any](algorithm Algorithm[T], index1, index2 int) {
	tmp := algorithm.Get(index1)
	algorithm.Set(index1, algorithm.Get(index2))
	algorithm.Set(index2, tmp)
}
