package alg03sorting;


import static alg03sorting.SortUtils.exchange;
import static alg03sorting.SortUtils.less;

/**
 * Insertion sort moves only one element at a time, thus being slow
 * when sorting an array requires a lot of moves.
 * Shell sort tries to compensate this by gradually sorting the array
 * in a similar fashion, but with bigger steps (>1). Step is equal to 1
 * at the very end of a Shell sorting algorithm, efficiently becoming
 * an insertion sort when the array is almost sorted.
 * <p>
 * The worst-case number of compares used by shellsort with 3x+1 sequence
 * is O(N^1.5)
 */
public class S3ShellSort implements SortingAlgorithm {
    public <T extends Comparable<T>> void sort(T[] array) {
        // Choosing the biggest step h < a.length
        // from Knuth's shellsort step sequence (3*x + 1)
        int h = 1;
        while (h < array.length / 3) {
            // 1, 4, 13, 40, 121, 364, ...
            h = 3 * h + 1;
        }
        while (h >= 1) {
            // We perform an h-sort (an insertion sort with step h)
            // for all steps
            for (int i = h; i < array.length; i++) {
                for (int k = i; k >= h && less(array[k], array[k - h]); k -= h) {
                    exchange(array, k, k - h);
                }
            }
            // Taking closest smaller step from Knuth's sequence
            h = h / 3;
        }
    }
}
