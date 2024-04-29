package alg06sorting

import (
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/deemson/algorithms/alg00less"
)

type SortFunc[T any] func(indexed alg00indexed.Indexed[T], less alg00less.Less[T])
