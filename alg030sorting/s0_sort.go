package alg030sorting

import "github.com/deemson/algorithms/alg020indexed"

type LessFunc[T any] func(item1, item2 T) bool

type SortFunc[T any] func(indexed alg020indexed.Indexed[T], less LessFunc[T])
