package alg03queue_test

import (
	"github.com/deemson/algorithms/alg03queue"
	"github.com/stretchr/testify/assert"
	"reflect"
	"strings"
	"testing"
)

func forEachAlgorithm[T any](t *testing.T, f func(t *testing.T, queue alg03queue.Queue[T])) {
	for _, queue := range []alg03queue.Queue[T]{
		alg03queue.Array[T](2),
		alg03queue.Linked[T](),
	} {
		name := reflect.ValueOf(queue).FieldByName("deque").FieldByName("algorithm").Elem().Type().Elem().Name()
		name = name[:strings.Index(name, "[")]
		t.Run(name, func(t *testing.T) {
			f(t, queue)
		})
	}
}

func TestQueues(t *testing.T) {
	forEachAlgorithm(t, func(t *testing.T, queue alg03queue.Queue[int]) {
		queue.Enqueue(1)
		queue.Enqueue(2)
		queue.Enqueue(3)
		assert.Equal(t, 1, queue.Dequeue())
		assert.Equal(t, 2, queue.Dequeue())
		assert.Equal(t, 3, queue.Dequeue())
	})
}
