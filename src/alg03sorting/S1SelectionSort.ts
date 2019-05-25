import {exchange} from "./helpers";
import {S0Sort} from "./S0Sort";

/**
 * Selection sort uses ~N^2/2 compares and N exchanges
 */
export class S1SelectionSort<T> implements S0Sort<T> {
    sort(array: T[]): void {
        for (let index = 0; index < array.length; index++) {
            /**
             * We are trying to find the minimum element in the array
             * In the beginning we assume that the minimum element is the
             * current element. To the left of the current element there could
             * be no elements less that the current (see below), but there
             * could be smaller elements to the right of the current element.
             */
            let minValueIndex = index;
            /**
             * In this loop we are trying to find the smallest element
             * to the right of the current element
             */
            for (let minSearchIndex = index + 1; minSearchIndex < array.length; minSearchIndex++) {
                if (array[minSearchIndex] < array[minValueIndex]) {
                    minValueIndex = minSearchIndex;
                }
            }
            /**
             * We exchange the found minimum element with the current,
             * (or the current element with itself if it IS the minimum)
             */
            exchange(array, index, minValueIndex);
            /**
             * This is the reason why there are no elements smaller than
             * the current to the left in particular, and why the array
             * is sorted in general.
             */
        }
    }
}
