package alg023stack_test

import (
	"github.com/deemson/algorithms/alg023stack"
	"github.com/stretchr/testify/assert"
	"testing"
)

func forEachAlgorithm[T any](t *testing.T, f func(t *testing.T, stack alg023stack.Stack[T])) {
	stacks := map[string]alg023stack.Stack[T]{
		"array":  alg023stack.Array[T](2),
		"linked": alg023stack.Linked[T](),
	}
	for name, stack := range stacks {
		t.Run(name, func(t *testing.T) {
			f(t, stack)
		})
	}
}

func TestStacks(t *testing.T) {
	forEachAlgorithm(t, func(t *testing.T, stack alg023stack.Stack[int]) {
		stack.Push(1)
		stack.Push(2)
		stack.Push(3)
		assert.Equal(t, 3, stack.Pop())
		assert.Equal(t, 2, stack.Pop())
		assert.Equal(t, 1, stack.Pop())
	})
}
