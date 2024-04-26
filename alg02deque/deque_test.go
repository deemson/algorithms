package alg02deque_test

import (
	"github.com/deemson/algorithms/alg02deque"
	"github.com/stretchr/testify/assert"
	"testing"
)

func forEachAlgorithm[T any](t *testing.T, f func(t *testing.T, deque alg02deque.Deque[T])) {
	deques := map[string]alg02deque.Deque[T]{
		"array": alg02deque.ArrayBased[T](2),
	}
	for name, deque := range deques {
		t.Run(name, func(t *testing.T) {
			f(t, deque)
		})
	}
}

func TestDeque_GetOutOfBounds(t *testing.T) {
	forEachAlgorithm[int](t, func(t *testing.T, deque alg02deque.Deque[int]) {
		defer func(t *testing.T) {
			r := recover()
			assert.Equal(t, "Deque index (0) must be less than size (0)", r)
		}(t)
		deque.Get(0)
	})
}

func TestDeque_SetOutOfBounds(t *testing.T) {
	forEachAlgorithm[int](t, func(t *testing.T, deque alg02deque.Deque[int]) {
		defer func(t *testing.T) {
			r := recover()
			assert.Equal(t, "Deque index (0) must be less than size (0)", r)
		}(t)
		deque.Set(0, 42)
	})
}

func TestDeque_AddAtIndexOutOfBounds(t *testing.T) {
	forEachAlgorithm[int](t, func(t *testing.T, deque alg02deque.Deque[int]) {
		defer func(t *testing.T) {
			r := recover()
			assert.Equal(t, "Deque index (1) must be less or equal size (0)", r)
		}(t)
		deque.AddAtIndex(1, 42)
	})
}

func TestDeque_AddAtIndexInBounds(t *testing.T) {
	forEachAlgorithm[int](t, func(t *testing.T, deque alg02deque.Deque[int]) {
		deque.AddAtIndex(0, 42)
		assert.Equal(t, 42, deque.Get(0))
	})
}
