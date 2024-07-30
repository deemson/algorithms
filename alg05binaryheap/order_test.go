package alg05binaryheap_test

import (
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
		slice := sliceStringIntoCharacters(testCase.input)
		less := func(item1, item2 string) bool {
			return item1 < item2
		}
		alg05binaryheap.BinaryHeapOrder(slice, less)
		actual := alg05binaryheap.Visualize(slice, stringAsIs)
		assert.Equal(t, strings.Join(testCase.expected, "\n"), actual)
		assert.NoError(t, alg05binaryheap.ValidateBinaryHeapOrder(slice, less))
	}
}
