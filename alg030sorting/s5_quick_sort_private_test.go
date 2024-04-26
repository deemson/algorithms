package alg030sorting

import (
	"github.com/deemson/algorithms/alg020indexed"
	"github.com/stretchr/testify/assert"
	"sort"
	"testing"
)

func TestPartition(t *testing.T) {
	testCases := map[string]struct {
		actual        []int
		expected      []int
		expectedIndex int
	}{
		"3 elements": {
			actual:        []int{2, 3, 1},
			expected:      []int{1, 2, 3},
			expectedIndex: 1,
		},
		"nothing on the left": {
			actual:        []int{3, 1, 1, 1, 1},
			expected:      []int{1, 1, 1, 1, 3},
			expectedIndex: 4,
		},
		"nothing on the right": {
			actual:        []int{3, 5, 5, 5, 5},
			expected:      []int{3, 5, 5, 5, 5},
			expectedIndex: 0,
		},
		"everything equal": {
			actual:        []int{3, 3, 3, 3, 3},
			expected:      []int{3, 3, 3, 3, 3},
			expectedIndex: 0,
		},
	}
	for name, testCase := range testCases {
		t.Run(name, func(t *testing.T) {
			actualIndex := partition(alg020indexed.SliceAdapter[int]{
				Slice: testCase.actual,
			}, func(item1, item2 int) bool {
				return item1 < item2
			}, 0, len(testCase.actual)-1)
			assert.Equal(t, testCase.expected, testCase.actual)
			assert.Equal(t, testCase.expectedIndex, actualIndex)
		})
	}
}

func TestShuffle(t *testing.T) {
	slice := []int{1, 2, 3, 4, 5}
	shuffle[int](alg020indexed.SliceAdapter[int]{
		Slice: slice,
	})
	assert.NotEqual(t, []int{1, 2, 3, 4, 5}, slice)
	sort.Slice(slice, func(i, j int) bool {
		return slice[i] < slice[j]
	})
	assert.Equal(t, []int{1, 2, 3, 4, 5}, slice)
}
