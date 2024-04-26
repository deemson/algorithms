package alg06sorting

import "github.com/deemson/algorithms/alg02indexed"

type LessFunc[T any] func(item1, item2 T) bool

type SortFunc[T any] func(indexed alg02indexed.Indexed[T], less LessFunc[T])
