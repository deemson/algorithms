package alg05sorting_test

import (
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/deemson/algorithms/alg00less"
	"github.com/deemson/algorithms/alg02deque"
	"github.com/deemson/algorithms/alg05sorting"
	"github.com/stretchr/testify/assert"
	"testing"
)

func forEachAlgorithm[T any](t *testing.T, less alg00less.Less[T], f func(t *testing.T, sort func(indexed alg00indexed.Indexed[T]))) {
	sorts := map[string]alg05sorting.SortFunc[T]{
		"SelectionSort":     alg05sorting.SelectionSort[T],
		"InsertionSort":     alg05sorting.InsertionSort[T],
		"ShellSort":         alg05sorting.ShellSort[T],
		"MergeSort":         alg05sorting.MergeSort[T],
		"BottomUpMergeSort": alg05sorting.BottomUpMergeSort[T],
		"QuickSort":         alg05sorting.QuickSort[T],
		"HeapSort":          alg05sorting.HeapSort[T],
	}
	for name, sort := range sorts {
		t.Run(name, func(t *testing.T) {
			f(t, func(indexed alg00indexed.Indexed[T]) {
				sort(indexed, less)
			})
		})
	}
}

func TestSort_BunchOfInts(t *testing.T) {
	forEachAlgorithm(t, alg00less.Int, func(t *testing.T, sort func(indexed alg00indexed.Indexed[int])) {
		actual := []int{
			42,
			17,
			100500,
			123,
			3,
			13,
			256,
			4242,
			127,
		}
		expected := []int{
			3,
			13,
			17,
			42,
			123,
			127,
			256,
			4242,
			100500,
		}
		t.Run("Slice", func(t *testing.T) {
			sort(alg00indexed.Slice(actual))
			assert.Equal(t, expected, actual)
		})
		t.Run("Deque", func(t *testing.T) {
			deque := alg02deque.Array[int](2)
			deque.AddAllLast(actual...)
			sort(deque)
			assert.Equal(t, expected, deque.ToSlice())
		})
	})
}

func TestSort_BunchOfStrings(t *testing.T) {
	forEachAlgorithm(t, alg00less.String, func(t *testing.T, sort func(indexed alg00indexed.Indexed[string])) {
		actual := []string{"super", "algorithm", "main"}
		expected := []string{"algorithm", "main", "super"}
		sort(alg00indexed.Slice(actual))
		assert.Equal(t, expected, actual)
		t.Run("Slice", func(t *testing.T) {
			sort(alg00indexed.Slice(actual))
			assert.Equal(t, expected, actual)
		})
		t.Run("Deque", func(t *testing.T) {
			deque := alg02deque.Array[string](2)
			deque.AddAllLast(actual...)
			sort(deque)
			assert.Equal(t, expected, deque.ToSlice())
		})
	})
}
