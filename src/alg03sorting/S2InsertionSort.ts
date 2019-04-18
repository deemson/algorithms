import {exchange} from "./helpers";
import {Sort} from "./Sort";

/**
 * On average, insertion sort makes ~1/4N^2 compares and ~1/4N^2 exchanges
 * With partially sorted arrays, though, it's performance may be close to linear
 */
export class S2InsertionSort<T> implements Sort<T> {
    sort(array: T[]): void {
        for (let outerIndex = 0; outerIndex < array.length; outerIndex++) {
            /**
             * The enclosing loop adds an additional element to the
             * loop below with each iteration.
             */
            for (let innerIndex = outerIndex; innerIndex > 0; innerIndex--) {
                /**
                 * Each added element tries to find place in the left part (sorted part)
                 * of the whole array.
                 */
                if (array[innerIndex] < array[innerIndex - 1]) {
                    exchange(array, innerIndex, innerIndex - 1);
                }
            }
            /**
             * So we sort the array by gradually "inserting" additional elements to the sorted set.
             */
        }
    }
}
