package alg02deque_test

import (
	"github.com/deemson/algorithms/alg02deque"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestArrayDeque_IndexShifts(t *testing.T) {
	deque := alg02deque.ArrayBased[int](2)
	deque.AddFirst(42)
	assert.Equal(t, 42, deque.Get(0))
	deque.AddLast(123)
	assert.Equal(t, 123, deque.Get(1))
}

func TestArrayDeque_GrowByAddingLast(t *testing.T) {
	deque := alg02deque.ArrayBased[int](2)
	deque.AddLast(1)
	deque.AddLast(2)
	deque.AddLast(3)
	assert.Equal(t, 3, deque.Size())
	assert.Equal(t, []int{1, 2, 3}, deque.ToSlice())
}

func TestArrayDeque_GrowByAddingFirst(t *testing.T) {
	deque := alg02deque.ArrayBased[int](2)
	deque.AddFirst(1)
	deque.AddFirst(2)
	deque.AddFirst(3)
	assert.Equal(t, 3, deque.Size())
	assert.Equal(t, []int{3, 2, 1}, deque.ToSlice())
}

//func TestArrayDeque_ShrinkByAddingLastAndRemovingFirst(t *testing.T) {
//	deque := alg02deque.ArrayBased[int](2)
//	deque.AddLast(1)
//	deque.AddLast(2)
//	deque.AddLast(3)
//	assert.Equal(t, 3, deque.Size())
//	assert.Equal(t, 1, deque.RemoveFirst())
//	assert.Equal(t, 2, deque.RemoveFirst())
//	assert.Equal(t, 1, deque.Size())
//	assert.Equal(t, 3, deque.RemoveFirst())
//	assert.True(t, deque.IsEmpty())
//}
//
//func TestArrayDeque_ShrinkByAddingFirstAndRemovingLast(t *testing.T) {
//	deque := alg02deque.ArrayBased[int](2)
//	deque.AddFirst(1)
//	deque.AddFirst(2)
//	deque.AddFirst(3)
//	assert.Equal(t, 3, deque.Size())
//	assert.Equal(t, 1, deque.RemoveLast())
//	assert.Equal(t, []int{3, 2}, deque.ToSlice())
//	assert.Equal(t, 2, deque.RemoveLast())
//	assert.Equal(t, 1, deque.Size())
//	assert.Equal(t, 3, deque.RemoveLast())
//	assert.True(t, deque.IsEmpty())
//}
