package alg06sorting

import "cmp"

type Sort[T any] func(slice []T, less Less[T])

type Less[T any] func(item1, item2 T) bool

func OrderedLess[T cmp.Ordered](item1, item2 T) bool {
	return item1 < item2
}

func swap[T any](slice []T, index1, index2 int) {
	tmp := slice[index1]
	slice[index1] = slice[index2]
	slice[index2] = tmp
}
