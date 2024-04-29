package alg06binaryheap_test

import (
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/deemson/algorithms/alg06binaryheap"
	"github.com/stretchr/testify/assert"
	"strings"
	"testing"
)

func TestVisualize(t *testing.T) {
	testCases := []struct {
		input    string
		expected []string
	}{
		{
			input: "ABC",
			expected: []string{
				"A",
				"  B",
				"  C",
			},
		},
		{
			input: "ABCDEFG",
			expected: []string{
				"A",
				"  B",
				"    D",
				"    E",
				"  C",
				"    F",
				"    G",
			},
		},
		{
			input: "ABCDEFGHIJKLMNO",
			expected: []string{
				"A",
				"  B",
				"    D",
				"      H",
				"      I",
				"    E",
				"      J",
				"      K",
				"  C",
				"    F",
				"      L",
				"      M",
				"    G",
				"      N",
				"      O",
			},
		},
	}
	for _, testCase := range testCases {
		t.Run(testCase.input, func(t *testing.T) {
			sliceOfCharStrings := sliceStringIntoCharacters(testCase.input)
			actual := alg06binaryheap.Visualize(
				alg00indexed.Slice(sliceOfCharStrings),
				stringAsIs,
			)
			assert.Equal(t, strings.Join(testCase.expected, "\n"), actual)
		})
	}
}
