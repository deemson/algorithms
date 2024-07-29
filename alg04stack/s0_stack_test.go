package alg04stack_test

import (
	"github.com/deemson/algorithms/alg04stack"
	"github.com/stretchr/testify/assert"
	"reflect"
	"strings"
	"testing"
)

func forEachAlgorithm[T any](t *testing.T, f func(t *testing.T, queue alg04stack.Stack[T])) {
	for _, queue := range []alg04stack.Stack[T]{
		alg04stack.Array[T](2),
		alg04stack.Linked[T](),
	} {
		name := reflect.ValueOf(queue).FieldByName("deque").FieldByName("algorithm").Elem().Type().Elem().Name()
		name = name[:strings.Index(name, "[")]
		t.Run(name, func(t *testing.T) {
			f(t, queue)
		})
	}
}

func TestStacks(t *testing.T) {
	forEachAlgorithm(t, func(t *testing.T, stack alg04stack.Stack[int]) {
		stack.Push(1)
		stack.Push(2)
		stack.Push(3)
		assert.Equal(t, 3, stack.Pop())
		assert.Equal(t, 2, stack.Pop())
		assert.Equal(t, 1, stack.Pop())
	})
}
