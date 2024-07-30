package alg06sorting_test

import (
	"github.com/deemson/algorithms/alg06sorting"
	"github.com/stretchr/testify/assert"
	"testing"
)

func forEachAlgorithm[T any](t *testing.T, less alg06sorting.Less[T], f func(t *testing.T, sort func(slice []T))) {
	namedSorts := []struct {
		name string
		sort alg06sorting.Sort[T]
	}{
		{"SelectionSort", alg06sorting.SelectionSort[T]},
		{"InsertionSort", alg06sorting.InsertionSort[T]},
		{"ShellSort", alg06sorting.ShellSort[T]},
		{"MergeSort", alg06sorting.MergeSort[T]},
		{"BottomUpMergeSort", alg06sorting.BottomUpMergeSort[T]},
		{"QuickSort", alg06sorting.QuickSort[T]},
		{"HeapSort", alg06sorting.HeapSort[T]},
	}
	for _, namedSort := range namedSorts {
		t.Run(namedSort.name, func(t *testing.T) {
			f(t, func(slice []T) {
				namedSort.sort(slice, less)
			})
		})
	}
}

func TestSort_BunchOfInts(t *testing.T) {
	forEachAlgorithm(t, alg06sorting.OrderedLess[int], func(t *testing.T, sort func(slice []int)) {
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
		sort(actual)
		assert.Equal(t, expected, actual)
	})
}

func TestSort_BunchOfStrings(t *testing.T) {
	forEachAlgorithm(t, alg06sorting.OrderedLess[string], func(t *testing.T, sort func(slice []string)) {
		actual := []string{"super", "algorithm", "main"}
		expected := []string{"algorithm", "main", "super"}
		sort(actual)
		assert.Equal(t, expected, actual)
	})
}
