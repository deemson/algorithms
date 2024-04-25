package alg02deque_test

import (
	"github.com/deemson/algorithms/alg02deque"
	"github.com/stretchr/testify/assert"
	"testing"
)

func forEachAlgorithm[T any](t *testing.T, f func(t *testing.T, deque alg02deque.Deque[T])) {
	algorithms := map[string]alg02deque.Algorithm[T]{
		"array": alg02deque.ArrayBased[T](2),
	}
	for name, algorithm := range algorithms {
		t.Run(name, func(t *testing.T) {
			f(t, alg02deque.Deque[T]{Algorithm: algorithm})
		})
	}
}

func TestDeque_GetOutOfBounds(t *testing.T) {
	forEachAlgorithm[int](t, func(t *testing.T, deque alg02deque.Deque[int]) {
		defer func(t *testing.T) {
			r := recover()
			assert.Equal(t, "index (0) must be less than size (0)", r)
		}(t)
		deque.Get(0)
	})
}

func TestDeque_SetOutOfBounds(t *testing.T) {
	forEachAlgorithm[int](t, func(t *testing.T, deque alg02deque.Deque[int]) {
		defer func(t *testing.T) {
			r := recover()
			assert.Equal(t, "index (0) must be less than size (0)", r)
		}(t)
		deque.Set(0, 42)
	})
}

func TestDeque_AddAtIndexOutOfBounds(t *testing.T) {
	forEachAlgorithm[int](t, func(t *testing.T, deque alg02deque.Deque[int]) {
		//defer func(t *testing.T) {
		//	r := recover()
		//	assert.Equal(t, "index (0) must be less than size (0)", r)
		//}(t)
		deque.AddAtIndex(1, 42)
	})
}
