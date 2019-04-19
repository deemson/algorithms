import {Sort} from "./Sort";

/**
 * Divide and conquer (split-sort-merge) algorithm.
 * The execution time is as fast as O(N*log2(N)).
 */
export class S4MergeSort<T> implements Sort<T> {
    sort(array: T[]): void {
        const auxArray = Array<T>(array.length);
        this.splitSortAndMergeBack(array, auxArray, 0, array.length - 1);
    }

    /**
     * As the mergesort is a divide and conquer algorithm, there must be a function to glue back the
     * divided parts. Merge is that function. It merges two small sorted parts the array
     * (that span indices left-mid and mid-right respectively)
     * into one big sorted part (that spans indices left-right).
     * @param array          - array, parts of which is to be merged
     * @param auxArray       - auxArray, such that auxArray.length == array.length
     * @param leftThreshold  - left threshold
     * @param midThreshold   - middle point
     * @param rightThreshold - right threshold
     */
    protected merge(array: T[], auxArray: T[], leftThreshold: number, midThreshold: number, rightThreshold: number) {
        for (let copyIndex = leftThreshold; copyIndex <= rightThreshold; copyIndex++) {
            auxArray[copyIndex] = array[copyIndex];
        }
        let leftPointer = leftThreshold;
        let midPointer = midThreshold + 1;
        for (let index = leftThreshold; index <= rightThreshold; index++) {
            if (leftPointer > midThreshold) {
                // The lower part is exhausted and we copy the remainder from the higher part.
                array[index] = auxArray[midPointer];
                midPointer += 1;
            } else if (midPointer > rightThreshold) {
                // The higher part is exhausted and we copy the remainder from the lower part.
                array[index] = auxArray[leftPointer];
                leftPointer += 1;
            } else if (auxArray[midPointer] < auxArray[leftPointer]) {
                /**
                 * Both parts are not exhausted:
                 * we check whether the value in leftThreshold..leftPointer or
                 * the value in midPointer..rightThreshold is lower.
                 * Then we pick the value from that range and increment
                 * leftPointer or midPointer correspondingly.
                 */
                array[index] = auxArray[midPointer];
                midPointer += 1;
            } else {
                array[index] = auxArray[leftPointer];
                leftPointer += 1;
            }
        }
    }

    private splitSortAndMergeBack(array: T[], auxArray: T[], leftTheshold: number, rightThreshold: number) {
        // If indexes cross, sorting is done.
        if (rightThreshold <= leftTheshold) {
            return;
        }
        // Divide and conquer in action: we slice the array in two parts.
        const midThreshold = leftTheshold + Math.floor((rightThreshold - leftTheshold) / 2);
        this.splitSortAndMergeBack(array, auxArray, leftTheshold, midThreshold);
        this.splitSortAndMergeBack(array, auxArray, midThreshold + 1, rightThreshold);
        // then merge two parts together
        this.merge(array, auxArray, leftTheshold, midThreshold, rightThreshold);
    }
}
