package alg03deque_test

import (
	"github.com/deemson/algorithms/alg03deque"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestArrayDeque_GrowByAddingLast(t *testing.T) {
	deque := alg03deque.Array[int](2)
	deque.AddLast(1)
	deque.AddLast(2)
	deque.AddLast(3)
	assert.Equal(t, 3, deque.Size())
	assert.Equal(t, []int{1, 2, 3}, deque.ToSlice())
}

func TestArrayDeque_GrowByAddingFirst(t *testing.T) {
	deque := alg03deque.Array[int](2)
	deque.AddFirst(1)
	deque.AddFirst(2)
	deque.AddFirst(3)
	assert.Equal(t, 3, deque.Size())
	assert.Equal(t, []int{3, 2, 1}, deque.ToSlice())
}

func TestArrayDeque_ShrinkByAddingLastAndRemovingFirst(t *testing.T) {
	deque := alg03deque.Array[int](4)
	deque.AddLast(1)
	deque.AddLast(2)
	deque.AddLast(3)
	assert.Equal(t, 3, deque.Size())
	assert.Equal(t, 1, deque.RemoveFirst())
	assert.Equal(t, 2, deque.RemoveFirst())
	assert.Equal(t, 1, deque.Size())
	assert.Equal(t, 3, deque.RemoveFirst())
	assert.True(t, deque.IsEmpty())
}

func TestArrayDeque_ShrinkByAddingFirstAndRemovingLast(t *testing.T) {
	deque := alg03deque.Array[int](4)
	deque.AddFirst(1)
	deque.AddFirst(2)
	deque.AddFirst(3)
	assert.Equal(t, 3, deque.Size())
	assert.Equal(t, 1, deque.RemoveLast())
	assert.Equal(t, []int{3, 2}, deque.ToSlice())
	assert.Equal(t, 2, deque.RemoveLast())
	assert.Equal(t, 1, deque.Size())
	assert.Equal(t, 3, deque.RemoveLast())
	assert.True(t, deque.IsEmpty())
}

func TestArrayDeque_AddAtIndex_MovingItemsToBothEnds(t *testing.T) {
	deque := alg03deque.Array[int](2)
	deque.AddLast(1)
	deque.AddLast(3)
	deque.AddLast(5)
	deque.AddAtIndex(1, 2)
	assert.Equal(t, []int{1, 2, 3, 5}, deque.ToSlice())
	deque.AddAtIndex(3, 4)
	assert.Equal(t, []int{1, 2, 3, 4, 5}, deque.ToSlice())
}

func TestArrayDeque_RemoveAtIndex_MovingItemsToBothEnds(t *testing.T) {
	deque := alg03deque.Array[int](2)
	deque.AddLast(1)
	deque.AddLast(2)
	deque.AddLast(3)
	deque.AddLast(4)
	deque.AddLast(5)
	assert.Equal(t, 4, deque.RemoveAtIndex(3))
	assert.Equal(t, []int{1, 2, 3, 5}, deque.ToSlice())
	assert.Equal(t, 2, deque.RemoveAtIndex(1))
	assert.Equal(t, []int{1, 3, 5}, deque.ToSlice())
}

func TestArrayDeque_RemoveAtIndex_FirstItemIndexWrapAround(t *testing.T) {
	deque := alg03deque.Array[int](4)
	deque.AddLast(2)
	deque.AddFirst(1)
	assert.Equal(t, []int{1, 2}, deque.ToSlice())
	assert.Equal(t, 1, deque.RemoveFirst())
	assert.Equal(t, []int{2}, deque.ToSlice())
}
