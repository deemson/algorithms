package alg06sorting

import (
	"github.com/deemson/algorithms/alg00compare"
	"github.com/deemson/algorithms/alg00indexed"
)

type SortFunc[T any] func(indexed alg00indexed.Indexed[T], less alg00compare.LessFunc[T])
