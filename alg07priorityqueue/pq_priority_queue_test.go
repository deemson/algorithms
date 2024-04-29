package alg07priorityqueue_test

import (
	"github.com/deemson/algorithms/alg00less"
	"github.com/deemson/algorithms/alg07priorityqueue"
	"github.com/stretchr/testify/assert"
	"testing"
)

func forEachAlgorithm[T any](t *testing.T, less alg00less.Less[T], f func(t *testing.T, priorityQueue alg07priorityqueue.PriorityQueue[T])) {
	priorityQueues := map[string]alg07priorityqueue.PriorityQueue[T]{
		"UnorderedArray": alg07priorityqueue.UnorderedArray(less, 2),
	}
	for name, priorityQueue := range priorityQueues {
		t.Run(name, func(t *testing.T) {
			f(t, priorityQueue)
		})
	}
}

func TestPriorityQueue(t *testing.T) {
	forEachAlgorithm[int](t, alg00less.Reversed(alg00less.Int), func(t *testing.T, priorityQueue alg07priorityqueue.PriorityQueue[int]) {
		priorityQueue.PushMany(42, 100500, 13, 1, 5)
		expected := []int{100500, 42, 13, 5, 1}
		actual := priorityQueue.PopMany(len(expected))
		assert.Equal(t, expected, actual)
	})
}
