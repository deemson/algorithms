package alg05binaryheap_test

import (
	"github.com/deemson/algorithms/alg00compare"
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/deemson/algorithms/alg05binaryheap"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestSink_Simple_TopToBottom(t *testing.T) {
	slice := sliceStringIntoCharacters("CBA")
	alg05binaryheap.SinkFromTopToBottom(alg00indexed.Slice(slice), alg00compare.StringLess)
	assert.Equal(t, sliceStringIntoCharacters("ABC"), slice)
}

func TestSink_To(t *testing.T) {
	slice := sliceStringIntoCharacters("GFEDCBA")
	alg05binaryheap.SinkFromTop(alg00indexed.Slice(slice), alg00compare.StringLess, 4)
	assert.Equal(t, sliceStringIntoCharacters("EFGDCBA"), slice)
}
