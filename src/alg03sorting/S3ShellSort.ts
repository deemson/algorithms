import {exchange} from "./helpers";
import {S0Sort} from "./S0Sort";

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
export class S3ShellSort<T> implements S0Sort<T> {
    sort(array: T[]): void {
        /**
         * Choose the biggest step from Knuth's shellsort step sequence
         * the array length allows.
         */
        let shellSortStep = 1;
        while (shellSortStep < array.length / 3) {
            // 1, 4, 13, 40, 121, 364, ...
            shellSortStep = 3 * shellSortStep + 1;
        }
        while (shellSortStep >= 1) {
            /**
             * We do an insertion sort using step=shellSortStep instead of step=1.
             */
            for (let outerIndex = shellSortStep; outerIndex < array.length; outerIndex++) {
                for (let innerIndex = outerIndex; innerIndex >= shellSortStep; innerIndex -= shellSortStep) {
                    if (array[innerIndex] > array[innerIndex - shellSortStep]) {
                        break;
                    }
                    exchange(array, innerIndex, innerIndex - shellSortStep);
                }
            }
            // Choosing closest smaller step from Knuth's sequence
            shellSortStep = Math.floor(shellSortStep / 3);
        }
    }
}
