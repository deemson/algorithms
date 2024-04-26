package alg030sorting

import (
	"github.com/deemson/algorithms/alg020indexed"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestMerge(t *testing.T) {
	actual := []int{1, 3, 5, 2, 4, 6}
	expected := []int{1, 2, 3, 4, 5, 6}
	merge(
		alg020indexed.SliceAdapter[int]{
			Slice: actual,
		},
		alg020indexed.SliceAdapter[int]{
			Slice: make([]int, len(actual)),
		},
		func(item1, item2 int) bool {
			return item1 < item2
		},
		0, 2, 5,
	)
	assert.Equal(t, expected, actual)
}
