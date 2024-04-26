package alg030sorting_test

import (
	"github.com/deemson/algorithms/alg020indexed"
	"github.com/deemson/algorithms/alg030sorting"
	"github.com/stretchr/testify/assert"
	"testing"
)

func forEachAlgorithm[T any](t *testing.T, less alg030sorting.LessFunc[T], f func(t *testing.T, sort func(indexed alg020indexed.Indexed[T]))) {
	sorts := map[string]alg030sorting.SortFunc[T]{
		"SelectionSort":     alg030sorting.SelectionSort[T],
		"InsertionSort":     alg030sorting.InsertionSort[T],
		"ShellSort":         alg030sorting.ShellSort[T],
		"MergeSort":         alg030sorting.MergeSort[T],
		"BottomUpMergeSort": alg030sorting.BottomUpMergeSort[T],
		"QuickSort":         alg030sorting.QuickSort[T],
	}
	for name, sort := range sorts {
		t.Run(name, func(t *testing.T) {
			f(t, func(indexed alg020indexed.Indexed[T]) {
				sort(indexed, less)
			})
		})
	}
}

func TestSort_BunchOfInts(t *testing.T) {
	forEachAlgorithm(t, func(item1, item2 int) bool {
		return item1 < item2
	}, func(t *testing.T, sort func(indexed alg020indexed.Indexed[int])) {
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
		sort(alg020indexed.SliceAdapter[int]{
			Slice: actual,
		})
		assert.Equal(t, expected, actual)
	})
}

func TestSort_BunchOfStrings(t *testing.T) {
	forEachAlgorithm(t, func(item1, item2 string) bool {
		return item1 < item2
	}, func(t *testing.T, sort func(indexed alg020indexed.Indexed[string])) {
		actual := []string{"super", "algorithm", "main"}
		expected := []string{"algorithm", "main", "super"}
		sort(alg020indexed.SliceAdapter[string]{
			Slice: actual,
		})
		assert.Equal(t, expected, actual)
	})
}
