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
		input    []string
		expected []string
	}{
		{
			input: []string{"A", "B", "C"},
			expected: []string{
				"A",
				"  B",
				"  C",
			},
		},
		{
			input: []string{"A", "B", "C", "D", "E", "F", "G"},
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
			input: []string{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"},
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
		t.Run(strings.Join(testCase.input, ""), func(t *testing.T) {
			actual := alg06binaryheap.Visualize(
				alg00indexed.Slice(testCase.input),
				func(item string) string { return item },
			)
			assert.Equal(t, strings.Join(testCase.expected, "\n"), actual)
		})
	}
}
