import {S4MergeSort} from "./S4MergeSort";
import {Sort} from "./Sort";

/**
 * It is the same merge sort, only without the recursion.
 */
export class S5BottomUpMergeSort<T> extends S4MergeSort<T> implements Sort<T> {
    sort(array: T[]): void {
        const auxArray = Array<T>(array.length);
        for (let size = 1; size < array.length; size = 2 * size) {
            for (let lowIndex = 0; lowIndex < array.length - size; lowIndex += 2 * size) {
                const midIndex = lowIndex + size - 1;
                const highIndex = Math.min(lowIndex + 2 * size - 1, array.length - 1);
                this.merge(array, auxArray, lowIndex, midIndex, highIndex);
            }
        }
    }
}
