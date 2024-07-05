package alg06sorting_test

import (
	"github.com/deemson/algorithms/alg00compare"
	"github.com/deemson/algorithms/alg00indexed"
	"github.com/deemson/algorithms/alg02deque"
	"github.com/deemson/algorithms/alg06sorting"
	"github.com/stretchr/testify/assert"
	"testing"
)

func forEachAlgorithm[T any](t *testing.T, compare alg00compare.Func[T], f func(t *testing.T, sort func(indexed alg00indexed.Indexed[T]))) {
	sorts := map[string]alg06sorting.SortFunc[T]{
		"SelectionSort":     alg06sorting.SelectionSort[T],
		"InsertionSort":     alg06sorting.InsertionSort[T],
		"ShellSort":         alg06sorting.ShellSort[T],
		"MergeSort":         alg06sorting.MergeSort[T],
		"BottomUpMergeSort": alg06sorting.BottomUpMergeSort[T],
		"QuickSort":         alg06sorting.QuickSort[T],
		"HeapSort":          alg06sorting.HeapSort[T],
	}
	for name, sort := range sorts {
		t.Run(name, func(t *testing.T) {
			f(t, func(indexed alg00indexed.Indexed[T]) {
				sort(indexed, alg00compare.AsLess(compare))
			})
		})
	}
}

func TestSort_BunchOfInts(t *testing.T) {
	forEachAlgorithm(t, alg00compare.Int, func(t *testing.T, sort func(indexed alg00indexed.Indexed[int])) {
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
	forEachAlgorithm(t, alg00compare.String, func(t *testing.T, sort func(indexed alg00indexed.Indexed[string])) {
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
