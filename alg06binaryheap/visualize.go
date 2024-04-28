package alg06binaryheap

import (
	"github.com/deemson/algorithms/alg00indexed"
	"strings"
)

func findIndentationLevel(index int) int {
	level := -1
	powerOf2 := 1
	for powerOf2 <= index+1 {
		powerOf2 *= 2
		level++
	}
	return level
}

func visualize[T any](indexed alg00indexed.Indexed[T], toString func(item T) string, atIndex int) string {
	indentationLevel := findIndentationLevel(atIndex)
	lines := make([]string, 1, 3)
	lines[0] = strings.Repeat("  ", indentationLevel) + toString(indexed.Get(atIndex))
	childIndex := ChildIndex(atIndex)
	if childIndex < indexed.Size() {
		lines = append(lines, visualize(indexed, toString, childIndex))
		childIndex++
		if childIndex < indexed.Size() {
			lines = append(lines, visualize(indexed, toString, childIndex))
		}
	}
	return strings.Join(lines, "\n")
}

func Visualize[T any](indexed alg00indexed.Indexed[T], toString func(item T) string) string {
	return visualize(indexed, toString, 0)
}
