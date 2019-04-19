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
     * (that span indices from-mid and mid-to respectively) into one big sorted part (that spans indices lo-hi).
     *
     * @param array     - array, parts of which is to be merged
     * @param auxArray  - auxArray, such that auxArray.length == array.length
     * @param lowIndex  - low threshold
     * @param midIndex  - middle point
     * @param highIndex - high threshold
     */
    protected merge(array: T[], auxArray: T[], lowIndex: number, midIndex: number, highIndex: number) {
        for (let copyIndex = lowIndex; copyIndex <= highIndex; copyIndex++) {
            auxArray[copyIndex] = array[copyIndex];
        }
        let lowToMidTrackerIndex = lowIndex;
        let midToHighTrackerIndex = midIndex + 1;
        for (let index = lowIndex; index <= highIndex; index++) {
            if (lowToMidTrackerIndex > midIndex) {
                // The lower part is exhausted and we copy the remainder from the higher part.
                array[index] = auxArray[midToHighTrackerIndex];
                midToHighTrackerIndex += 1;
            } else if (midToHighTrackerIndex > highIndex) {
                // The higher part is exhausted and we copy the remainder from the lower part.
                array[index] = auxArray[lowToMidTrackerIndex];
                lowToMidTrackerIndex += 1;
            } else if (auxArray[midToHighTrackerIndex] < auxArray[lowToMidTrackerIndex]) {
                /**
                 * Both parts are not exhausted:
                 * we check whether the value in lowIndex..lowToMidTrackerIndex or
                 * the value in midToHighTrackerIndex..highIndex is lower.
                 * Then we pick the value from that range and increment
                 * lowToMidTrackerIndex or midToHighTrackerIndex correspondingly.
                 */
                array[index] = auxArray[midToHighTrackerIndex];
                midToHighTrackerIndex += 1;
            } else {
                array[index] = auxArray[lowToMidTrackerIndex];
                lowToMidTrackerIndex += 1;
            }
        }
    }

    private splitSortAndMergeBack(array: T[], auxArray: T[], lowIndex: number, highIndex: number) {
        // If indexes cross, sorting is done.
        if (highIndex <= lowIndex) {
            return;
        }
        // Divide and conquer in action: we slice the array in two parts.
        const midIndex = lowIndex + Math.floor((highIndex - lowIndex) / 2);
        this.splitSortAndMergeBack(array, auxArray, lowIndex, midIndex);
        this.splitSortAndMergeBack(array, auxArray, midIndex + 1, highIndex);
        // then merge two parts together
        this.merge(array, auxArray, lowIndex, midIndex, highIndex);
    }
}
