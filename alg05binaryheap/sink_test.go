package alg05binaryheap_test

import (
	"github.com/deemson/algorithms/alg05binaryheap"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestSink_Simple_TopToBottom(t *testing.T) {
	slice := sliceStringIntoCharacters("CBA")
	alg05binaryheap.SinkFromTopToBottom(slice, func(item1, item2 string) bool {
		return item1 < item2
	})
	assert.Equal(t, sliceStringIntoCharacters("ABC"), slice)
}

func TestSink_To(t *testing.T) {
	slice := sliceStringIntoCharacters("GFEDCBA")
	alg05binaryheap.SinkFromTop(slice, func(item1, item2 string) bool {
		return item1 < item2
	}, 4)
	assert.Equal(t, sliceStringIntoCharacters("EFGDCBA"), slice)
}
