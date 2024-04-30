package alg00indexed_test

import (
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestSliceAdapter_Swap(t *testing.T) {
	slice := []int{1, 2}
	adapter := alg00indexed.Slice(slice)
	alg00indexed.Swap[int](adapter, 0, 1)
	assert.Equal(t, []int{2, 1}, slice)
}

func TestSliceAdapter_Iterator(t *testing.T) {
	expected := []int{1, 2, 3, 4, 5}
	actual := alg00indexed.ToSlice[int](alg00indexed.Slice(expected))
	assert.Equal(t, expected, actual)
}
