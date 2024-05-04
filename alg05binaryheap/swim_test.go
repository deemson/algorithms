package alg05binaryheap_test

import (
	"github.com/deemson/algorithms/alg00compare"
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/deemson/algorithms/alg05binaryheap"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestSwim_Simple_BottomToTop(t *testing.T) {
	slice := []string{"C", "B", "A"}
	alg05binaryheap.SwimFromBottomToTop(alg00indexed.Slice(slice), alg00compare.StringLess)
	assert.Equal(t, []string{"A", "B", "C"}, slice)
}
