package alg03sorting;


import static alg03sorting.SortUtils.less;

/**
 * Divide and conquer (split-sort-merge) algorithm.
 * The execution time is as fast as O(N*log2(N)).
 */
public class S4MergeSort implements SortingAlgorithm {
    /**
     * As the mergesort is a divide and conquer algorithm, there must be a function to glue back the
     * divided parts. Merge is that function. It merges two small sorted parts the array
     * (that span indices lo-mid and mid-hi respectively) into one big sorted part (that spans indices lo-hi).
     *
     * @param array    - array, parts of which is to be merged
     * @param auxArray - auxArray, such that auxArray.length == array.length
     * @param lo       - low threshold
     * @param mid      - middle point
     * @param hi       - high threshold
     */
    @SuppressWarnings("unchecked")
    static <T extends Comparable<T>> void merge(T[] array, Object[] auxArray, int lo, int mid, int hi) {
        System.arraycopy(array, lo, auxArray, lo, hi + 1 - lo);
        int loIdx = lo, hiIdx = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (loIdx > mid) {
                // If the low part is exhausted, we copy the remainder from the high part
                array[k] = (T) auxArray[hiIdx++];
            } else if (hiIdx > hi) {
                // If the high part is exhausted, we copy the remainder from the low part
                array[k] = (T) auxArray[loIdx++];
            } else if (less((T) auxArray[hiIdx], (T) auxArray[loIdx])) {
                // Ok, both parts are not exhausted so we check which part's value is lower,
                // copy it's value and increase it's respective index
                array[k] = (T) auxArray[hiIdx++];
            } else {
                array[k] = (T) auxArray[loIdx++];
            }
        }
    }

    private static <T extends Comparable<T>> void sort(T[] array, Object[] auxArray, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        // Divide and conquer in action: we slice the array in two parts
        int mid = lo + (hi - lo) / 2;
        // and sort each part independently
        sort(array, auxArray, lo, mid);
        sort(array, auxArray, mid + 1, hi);
        // then merge two parts together
        merge(array, auxArray, lo, mid, hi);
    }

    public <T extends Comparable<T>> void sort(T[] array) {
        // Cannot create and array of type T without passing class directly because of type erasure
        Object[] auxArray = new Object[array.length];
        sort(array, auxArray, 0, array.length - 1);
    }

}
