package alg03sorting;

import static alg03sorting.SortUtils.exchange;
import static alg03sorting.SortUtils.less;

/**
 * On average, insertion sort makes ~1/4N^2 compares and ~1/4N^2 exchanges
 * With partially sorted arrays, though, it's performance may be close to linear
 */
public class S2InsertionSort implements SortingAlgorithm {
    public <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            // The enclosing loop adds an additional element to the
            // loop below with each iteration.
            for (int k = i; k > 0; k--) {
                // Each added element tries to find place in the left part (sorted part)
                // of the whole array.
                if (less(array[k], array[k - 1])) {
                    exchange(array, k, k - 1);
                }
            }
            // So we sort the array by gradually "inserting" additional elements
            // to the sorted set.
        }
    }
}
