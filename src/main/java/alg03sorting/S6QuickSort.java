package alg03sorting;

import java.util.Random;

import static alg03sorting.SortUtils.exchange;
import static alg03sorting.SortUtils.less;

public class S6QuickSort implements SortingAlgorithm {
    /**
     * This function is the core of quicksort. It ensures that some arbitrary item from the part of an array
     * from leftThreshold to rightThreshold is in place, e.g. only smaller items to the left
     * and only bigger items to the right of this element.
     * Method partition returns an index of the element, that was put in place.
     */
    private static <T extends Comparable<T>> int partition(T[] array, int leftThreshold, int rightThreshold) {
        int leftPointer = leftThreshold, rightPointer = rightThreshold + 1;
        while (true) {
            // find item on left to swap
            while (less(array[++leftPointer], array[leftThreshold])) {
                if (leftPointer == rightThreshold) {
                    break;
                }
            }
            // find item on right to swap
            while (less(array[leftThreshold], array[--rightPointer])) {
                if (rightPointer == leftThreshold) {
                    break;
                }
            }
            // check if pointers cross
            if (leftPointer >= rightPointer) {
                break;
            }
            // swap
            exchange(array, leftPointer, rightPointer);
        }
        exchange(array, leftThreshold, rightPointer);
        // return index of item now known to be in place
        return rightPointer;
    }

    private static <T extends Comparable<T>> void sort(T[] array, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int elementInPlaceIdx = partition(array, lo, hi);
        sort(array, lo, elementInPlaceIdx - 1);
        sort(array, elementInPlaceIdx + 1, hi);
    }

    public <T extends Comparable<T>> void sort(T[] array) {
        // Needed for performance guarantee
        shuffle(array);
        sort(array, 0, array.length - 1);
    }

    private static Random random;

    static {
        // this is how the seed was set in Java 1.4
        long seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    private static void shuffle(Object[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = i + uniform(N - i); // between i and N-1
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    private static int uniform(int N) {
        if (N <= 0) throw new IllegalArgumentException("Parameter N must be positive");
        return random.nextInt(N);
    }
}
