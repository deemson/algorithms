package alg05binaryheap_test

import (
	"github.com/deemson/algorithms/alg05binaryheap"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestSwim_Simple_BottomToTop(t *testing.T) {
	slice := []string{"C", "B", "A"}
	alg05binaryheap.SwimFromBottomToTop(slice, func(item1, item2 string) bool {
		return item1 < item2
	})
	assert.Equal(t, []string{"A", "B", "C"}, slice)
}
