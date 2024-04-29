package alg06binaryheap_test

import (
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/deemson/algorithms/alg00less"
	"github.com/deemson/algorithms/alg06binaryheap"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestSink_Simple_TopToBottom(t *testing.T) {
	slice := sliceStringIntoCharacters("CBA")
	alg06binaryheap.SinkFromTopToBottom(alg00indexed.Slice(slice), alg00less.String)
	assert.Equal(t, sliceStringIntoCharacters("ABC"), slice)
}
