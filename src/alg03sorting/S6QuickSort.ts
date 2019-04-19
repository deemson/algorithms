import {exchange, shuffle} from "./helpers";
import {Sort} from "./Sort";

/**
 * Divide and conquer algorithm: put one element in place,
 * then do the same for an element in the part of the array to the left
 * and in the part of the array to the right of this element and so on.
 * The execution time is as fast as O(N*log2(N)).
 */
export class S6QuickSort<T> implements Sort<T> {
    sort(array: T[]): void {
        /**
         * Shuffling statistically guarantees that the element to be put in place
         * by the partitioning method below will be near the middle of the array
         * for the majority of the calls.
         */
        shuffle(array);
        this.partitionSort(array, 0, array.length - 1);
    }

    private partitionSort(array: T[], leftThreshold: number, rightThreshold: number) {
        if (rightThreshold <= leftThreshold) {
            return;
        }
        const elementInPlaceIndex = this.partition(array, leftThreshold, rightThreshold);
        this.partitionSort(array, leftThreshold, elementInPlaceIndex - 1);
        this.partitionSort(array, elementInPlaceIndex + 1, rightThreshold);
    }

    /**
     * This function is the core of quicksort. It ensures that some arbitrary item from the part of an array
     * from leftThreshold to rightThreshold is in place, e.g. only smaller items to the left
     * and only bigger items to the right of this element.
     * Method partition returns an index of the element, that was put in place.
     */
    private partition(array: T[], leftThreshold: number, rightThreshold: number): number {
        let leftPointer = leftThreshold + 1;
        let rightPointer = rightThreshold;
        /**
         * The value at index leftThreshold is going to be the one put in place.
         * We will find the pair of values using leftPointer and rightPointer such that
         * one value is greater of equal than valueToPutInPlace
         * and the other one is less or equal than valueToPutInPlace.
         * We swap them and repeat the procedure until there are no more such pairs (pointers will cross).
         */
        const valueToPutInPlace = array[leftThreshold];
        while (true) {
            // Move the left pointer to the right until we find the value bigger or equal.
            while (array[leftPointer] < valueToPutInPlace) {
                if (leftPointer === rightThreshold) {
                    break;
                }
                leftPointer += 1;
            }
            // Move the right pointer to the left until we find the value less or equal.
            while (array[rightPointer] > valueToPutInPlace) {
                if (rightPointer === leftThreshold) {
                    break;
                }
                rightPointer -= 1;
            }
            // If pointers cross then there are no more pairs to swap
            if (leftPointer >= rightPointer) {
                break;
            }
            exchange(array, leftPointer, rightPointer);
        }
        // we put valueToPutInPlace at rightPointer
        exchange(array, leftThreshold, rightPointer);
        /**
         * Now the following is true
         * array[leftThreshold..rightPointer-1]<=array[rightPointer]<=array[rightPointer+1..rightThreshold]
         */
        return rightPointer;
    }
}
