package alg06binaryheap_test

import (
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/deemson/algorithms/alg00less"
	"github.com/deemson/algorithms/alg06binaryheap"
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
		alg06binaryheap.BinaryHeapOrder(indexed, alg00less.String)
		actual := alg06binaryheap.Visualize(indexed, stringAsIs)
		assert.Equal(t, strings.Join(testCase.expected, "\n"), actual)
		assert.NoError(t, alg06binaryheap.ValidateBinaryHeapOrder(indexed, alg00less.String))
	}
}
