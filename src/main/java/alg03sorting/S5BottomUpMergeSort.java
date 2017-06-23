package alg03sorting;


/**
 * Divide and conquer (split-sort-merge) algorithm.
 * The execution time is as fast as O(N*log2(N)).
 * <p>
 * It is the same mergesort, only without the recursion.
 */
public class S5BottomUpMergeSort extends S4MergeSort {
    public <T extends Comparable<T>> void sort(T[] array) {
        int N = array.length;
        Object[] auxArray = new Object[N];
        for (int size = 1; size < N; size = size + size) {
            for (int lo = 0; lo < N - size; lo += size + size) {
                merge(array, auxArray, lo, lo + size - 1, Math.min(lo + size + size - 1, N - 1));
            }
        }
    }
}
