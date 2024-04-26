package alg04queue_test

import (
	"github.com/deemson/algorithms/alg04queue"
	"github.com/stretchr/testify/assert"
	"testing"
)

func forEachAlgorithm[T any](t *testing.T, f func(t *testing.T, queue alg04queue.Queue[T])) {
	queues := map[string]alg04queue.Queue[T]{
		"ArrayQueue":  alg04queue.Array[T](2),
		"LinkedQueue": alg04queue.Linked[T](),
	}
	for name, queue := range queues {
		t.Run(name, func(t *testing.T) {
			f(t, queue)
		})
	}
}

func TestQueues(t *testing.T) {
	forEachAlgorithm(t, func(t *testing.T, queue alg04queue.Queue[int]) {
		queue.Enqueue(1)
		queue.Enqueue(2)
		queue.Enqueue(3)
		assert.Equal(t, 1, queue.Dequeue())
		assert.Equal(t, 2, queue.Dequeue())
		assert.Equal(t, 3, queue.Dequeue())
	})
}
