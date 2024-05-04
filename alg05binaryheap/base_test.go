package alg05binaryheap_test

import (
	"github.com/deemson/algorithms/alg00compare"
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/deemson/algorithms/alg05binaryheap"
	"github.com/stretchr/testify/assert"
	"strings"
	"testing"
)

func TestBinaryHeapOrder(t *testing.T) {
	testCases := []struct {
		input    string
		expected []string
	}{
		{
			input: "CBAGFED",
			expected: []string{
				`A`,
				`  B`,
				`    G`,
				`    F`,
				`  C`,
				`    E`,
				`    D`,
			},
		},
		{
			input: "CBAHGFED",
			expected: []string{
				`A`,
				`  B`,
				`    D`,
				`      H`,
				`    G`,
				`  C`,
				`    F`,
				`    E`,
			},
		},
	}
	for _, testCase := range testCases {
		indexed := alg00indexed.Slice(sliceStringIntoCharacters(testCase.input))
		alg05binaryheap.BinaryHeapOrder(indexed, alg00compare.StringLess)
		actual := alg05binaryheap.Visualize(indexed, stringAsIs)
		assert.Equal(t, strings.Join(testCase.expected, "\n"), actual)
		assert.NoError(t, alg05binaryheap.ValidateBinaryHeapOrder(indexed, alg00compare.StringLess))
	}
}
