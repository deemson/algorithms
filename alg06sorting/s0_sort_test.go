package alg06sorting_test

import (
	"github.com/deemson/algorithms/alg02indexed"
	"github.com/deemson/algorithms/alg06sorting"
	"github.com/stretchr/testify/assert"
	"testing"
)

func forEachAlgorithm[T any](t *testing.T, less alg06sorting.LessFunc[T], f func(t *testing.T, sort func(indexed alg02indexed.Indexed[T]))) {
	sorts := map[string]alg06sorting.SortFunc[T]{
		"SelectionSort":     alg06sorting.SelectionSort[T],
		"InsertionSort":     alg06sorting.InsertionSort[T],
		"ShellSort":         alg06sorting.ShellSort[T],
		"MergeSort":         alg06sorting.MergeSort[T],
		"BottomUpMergeSort": alg06sorting.BottomUpMergeSort[T],
		"QuickSort":         alg06sorting.QuickSort[T],
	}
	for name, sort := range sorts {
		t.Run(name, func(t *testing.T) {
			f(t, func(indexed alg02indexed.Indexed[T]) {
				sort(indexed, less)
			})
		})
	}
}

func TestSort_BunchOfInts(t *testing.T) {
	forEachAlgorithm(t, func(item1, item2 int) bool {
		return item1 < item2
	}, func(t *testing.T, sort func(indexed alg02indexed.Indexed[int])) {
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
		sort(alg02indexed.SliceAdapter[int]{
			Slice: actual,
		})
		assert.Equal(t, expected, actual)
	})
}

func TestSort_BunchOfStrings(t *testing.T) {
	forEachAlgorithm(t, func(item1, item2 string) bool {
		return item1 < item2
	}, func(t *testing.T, sort func(indexed alg02indexed.Indexed[string])) {
		actual := []string{"super", "algorithm", "main"}
		expected := []string{"algorithm", "main", "super"}
		sort(alg02indexed.SliceAdapter[string]{
			Slice: actual,
		})
		assert.Equal(t, expected, actual)
	})
}
