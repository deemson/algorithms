package alg02stacksqueues;

import static java.lang.System.arraycopy;

final class ArrayUtils {

    private ArrayUtils() {
    }

    @SuppressWarnings("unchecked")
    static <E> E[] resize(E[] array, int capacity, int sourcePosition, int destinationPosition, int length) {
        E[] copy = (E[]) new Object[capacity];
        arraycopy(array, sourcePosition, copy, destinationPosition, length);
        return copy;
    }
}
