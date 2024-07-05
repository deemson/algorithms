package alg06sorting

import (
	"github.com/deemson/algorithms/alg00compare"
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestMerge(t *testing.T) {
	actual := []int{1, 3, 5, 2, 4, 6}
	expected := []int{1, 2, 3, 4, 5, 6}
	merge(
		alg00indexed.Slice(actual),
		alg00indexed.Slice(make([]int, len(actual))),
		alg00compare.IntLess,
		0, 2, 5,
	)
	assert.Equal(t, expected, actual)
}
