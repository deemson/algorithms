package alg05stack_test

import (
	"github.com/deemson/algorithms/alg05stack"
	"github.com/stretchr/testify/assert"
	"testing"
)

func forEachAlgorithm[T any](t *testing.T, f func(t *testing.T, stack alg05stack.Stack[T])) {
	stacks := map[string]alg05stack.Stack[T]{
		"ArrayStack":  alg05stack.Array[T](2),
		"LinkedStack": alg05stack.Linked[T](),
	}
	for name, stack := range stacks {
		t.Run(name, func(t *testing.T) {
			f(t, stack)
		})
	}
}

func TestStacks(t *testing.T) {
	forEachAlgorithm(t, func(t *testing.T, stack alg05stack.Stack[int]) {
		stack.Push(1)
		stack.Push(2)
		stack.Push(3)
		assert.Equal(t, 3, stack.Pop())
		assert.Equal(t, 2, stack.Pop())
		assert.Equal(t, 1, stack.Pop())
	})
}
