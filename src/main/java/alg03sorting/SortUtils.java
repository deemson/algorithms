package alg03sorting;


public abstract class SortUtils {

    /**
     * Checks whether c1 is less than c2
     */
    static <T extends Comparable<T>> boolean less(T c1, T c2) {
        return c1.compareTo(c2) < 0;
    }

    /**
     * Exchanges places of two of the array's elements
     */
    static <T> void exchange(Comparable<T>[] array, int el1idx, int el2idx) {
        Comparable<T> el1Value = array[el1idx];
        array[el1idx] = array[el2idx];
        array[el2idx] = el1Value;
    }

    /**
     * Sanity check function
     */
    public static <T extends Comparable<T>> boolean isSorted(T[] array) {
        for (int i = 1; i < array.length; i++) {
            if (less(array[i], array[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
