package alg00indexed_test

import (
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestSliceAdapter_Swap(t *testing.T) {
	slice := []int{1, 2}
	adapter := alg00indexed.SliceAdapter[int]{
		Slice: slice,
	}
	alg00indexed.Swap[int](adapter, 0, 1)
	assert.Equal(t, []int{2, 1}, slice)
}
