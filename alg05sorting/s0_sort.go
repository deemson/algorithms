package alg05sorting

import "github.com/deemson/algorithms/alg00indexed"

type LessFunc[T any] func(item1, item2 T) bool

type SortFunc[T any] func(indexed alg00indexed.Indexed[T], less LessFunc[T])
