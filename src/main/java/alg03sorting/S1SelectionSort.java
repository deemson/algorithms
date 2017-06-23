package alg03sorting;

import static alg03sorting.SortUtils.exchange;
import static alg03sorting.SortUtils.less;

/**
 * Selection sort uses ~N^2/2 compares and N exchanges
 */
public class S1SelectionSort implements SortingAlgorithm {
    public <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            // We are trying to find the minimum element in the array
            // In the beginning we assume that the minimum element is the
            // current element. To the left of the current element there could
            // be no elements less that the current (see below), but there
            // could be smaller elements to the right of the current element.
            int min = i;
            // In this loop we are trying to find the smaller element
            // to the right of the current element
            for (int k = i + 1; k < array.length; k++) {
                if (less(array[k], array[min])) {
                    min = k;
                }
            }
            // We exchange the found minimum element with the current,
            // (or the current element with itself if it IS the minimum)
            exchange(array, i, min);
            // This is the reason why there are no elements smaller than
            // the current to the left in particular, and why the array
            // is sorted in general.
        }
    }
}
