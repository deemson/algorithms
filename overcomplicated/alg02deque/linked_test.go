package alg02deque_test

import (
	"github.com/deemson/algorithms/alg02deque"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestLinkedDeque_AddingLast(t *testing.T) {
	deque := alg02deque.Linked[string]()
	deque.AddLast("one")
	assert.Equal(t, []string{"one"}, deque.ToSlice())
	deque.AddLast("two")
	assert.Equal(t, []string{"one", "two"}, deque.ToSlice())
}

func TestLinkedDeque_GettingAndSettingFromHead(t *testing.T) {
	deque := alg02deque.Linked[int]()
	deque.AddAllLast(1, 2, 3, 4, 5)
	assert.Equal(t, []int{1, 2, 3, 4, 5}, deque.ToSlice())
	assert.Equal(t, 2, deque.Get(1))
	deque.Set(1, 42)
	assert.Equal(t, []int{1, 42, 3, 4, 5}, deque.ToSlice())
}

func TestLinkedDeque_GettingAndSettingFromTail(t *testing.T) {
	deque := alg02deque.Linked[int]()
	deque.AddAllLast(1, 2, 3, 4, 5)
	assert.Equal(t, []int{1, 2, 3, 4, 5}, deque.ToSlice())
	assert.Equal(t, 4, deque.Get(3))
	deque.Set(3, 42)
	assert.Equal(t, []int{1, 2, 3, 42, 5}, deque.ToSlice())
}

func TestLinkedDeque_AddingHead(t *testing.T) {
	deque := alg02deque.Linked[int]()
	deque.AddLast(2)
	deque.AddFirst(1)
	assert.Equal(t, []int{1, 2}, deque.ToSlice())
}

func TestLinkedDeque_AddingMiddle(t *testing.T) {
	deque := alg02deque.Linked[int]()
	deque.AddLast(3)
	deque.AddFirst(1)
	deque.AddAtIndex(1, 2)
	assert.Equal(t, []int{1, 2, 3}, deque.ToSlice())
}

func TestLinkedDeque_RemovingHead(t *testing.T) {
	deque := alg02deque.Linked[int]()
	deque.AddAllLast(1, 2)
	assert.Equal(t, 1, deque.RemoveFirst())
	assert.Equal(t, []int{2}, deque.ToSlice())
	assert.Equal(t, 2, deque.RemoveFirst())
	assert.True(t, deque.IsEmpty())
}

func TestLinkedDeque_RemovingTail(t *testing.T) {
	deque := alg02deque.Linked[int]()
	deque.AddAllLast(1, 2)
	assert.Equal(t, 2, deque.RemoveLast())
	assert.Equal(t, []int{1}, deque.ToSlice())
	assert.Equal(t, 1, deque.RemoveLast())
	assert.True(t, deque.IsEmpty())
}

func TestLinkedDeque_RemovingMiddle(t *testing.T) {
	deque := alg02deque.Linked[int]()
	deque.AddAllLast(1, 2, 3)
	assert.Equal(t, 2, deque.RemoveAtIndex(1))
	assert.Equal(t, []int{1, 3}, deque.ToSlice())
}
