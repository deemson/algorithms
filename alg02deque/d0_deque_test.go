package alg02deque_test

import (
	"github.com/deemson/algorithms/alg02deque"
	"github.com/stretchr/testify/assert"
	"reflect"
	"strings"
	"testing"
)

func forEachAlgorithm[T any](t *testing.T, f func(t *testing.T, deque alg02deque.Deque[T])) {
	for _, deque := range []alg02deque.Deque[T]{
		alg02deque.Array[T](2),
		alg02deque.Linked[T](),
	} {
		name := reflect.ValueOf(deque).FieldByName("algorithm").Elem().Type().Elem().Name()
		name = name[:strings.Index(name, "[")]
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

func TestDeque_AddAllLast(t *testing.T) {
	forEachAlgorithm[int](t, func(t *testing.T, deque alg02deque.Deque[int]) {
		deque.AddAllLast(1, 2, 3)
		assert.Equal(t, []int{1, 2, 3}, deque.ToSlice())
	})
}

func TestDeque_GetNegativeIndex(t *testing.T) {
	forEachAlgorithm[int](t, func(t *testing.T, deque alg02deque.Deque[int]) {
		defer func(t *testing.T) {
			r := recover()
			assert.Equal(t, "Deque index must not be negative (-1)", r)
		}(t)
		deque.Get(-1)
	})
}

func TestDeque_RemoveFromEmpty(t *testing.T) {
	forEachAlgorithm[int](t, func(t *testing.T, deque alg02deque.Deque[int]) {
		defer func(t *testing.T) {
			r := recover()
			assert.Equal(t, "Deque must not be empty", r)
		}(t)
		deque.RemoveLast()
	})
}
