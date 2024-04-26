package alg022queue_test

import (
	"github.com/deemson/algorithms/alg022queue"
	"github.com/stretchr/testify/assert"
	"testing"
)

func forEachAlgorithm[T any](t *testing.T, f func(t *testing.T, queue alg022queue.Queue[T])) {
	queues := map[string]alg022queue.Queue[T]{
		"ArrayQueue":  alg022queue.Array[T](2),
		"LinkedQueue": alg022queue.Linked[T](),
	}
	for name, queue := range queues {
		t.Run(name, func(t *testing.T) {
			f(t, queue)
		})
	}
}

func TestQueues(t *testing.T) {
	forEachAlgorithm(t, func(t *testing.T, queue alg022queue.Queue[int]) {
		queue.Enqueue(1)
		queue.Enqueue(2)
		queue.Enqueue(3)
		assert.Equal(t, 1, queue.Dequeue())
		assert.Equal(t, 2, queue.Dequeue())
		assert.Equal(t, 3, queue.Dequeue())
	})
}
