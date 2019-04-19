import {S4MergeSort} from "./S4MergeSort";
import {Sort} from "./Sort";

/**
 * It is the same merge sort, only without the recursion.
 */
export class S5BottomUpMergeSort<T> extends S4MergeSort<T> implements Sort<T> {
    sort(array: T[]): void {
        const auxArray = Array<T>(array.length);
        for (let size = 1; size < array.length; size = 2 * size) {
            for (let leftThreshold = 0; leftThreshold < array.length - size; leftThreshold += 2 * size) {
                const midThreshold = leftThreshold + size - 1;
                const rightThreshold = Math.min(leftThreshold + 2 * size - 1, array.length - 1);
                this.merge(array, auxArray, leftThreshold, midThreshold, rightThreshold);
            }
        }
    }
}
