import {exchange} from "./helpers";
import {S0Sort} from "./S0Sort";

/**
 * On average, insertion sort makes ~1/4N^2 compares and ~1/4N^2 exchanges
 * With partially sorted arrays, though, it's performance may be close to linear
 */
export class S2InsertionSort<T> implements S0Sort<T> {
    sort(array: T[]): void {
        /**
         * The outer loop adds an additional element to the
         * loop below with each iteration.
         */
        for (let outerIndex = 0; outerIndex < array.length; outerIndex++) {
            /**
             * Each added element tries to find place in the left part (sorted part)
             * using the inner loop below.
             */
            for (let innerIndex = outerIndex; innerIndex > 0; innerIndex--) {
                /**
                 * If the current element is bigger than the previous,
                 * then we found the element's place in the left (sorted) part
                 * of the array and there's no point of running the inner loop anymore.
                 */
                if (array[innerIndex] > array[innerIndex - 1]) {
                    break;
                }
                exchange(array, innerIndex, innerIndex - 1);
            }
            /**
             * So we sort the array by gradually "inserting" additional elements to the sorted set.
             */
        }
    }
}
