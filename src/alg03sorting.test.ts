import {S1SelectionSort, S2InsertionSort, S3ShellSort, S4MergeSort} from "./alg03sorting";

describe.each([
    S1SelectionSort,
    S2InsertionSort,
    S3ShellSort,
    S4MergeSort,
])("Sorting Algorithms", (SortingAlgorithm) => {
    const s = new SortingAlgorithm();
    describe(`${SortingAlgorithm.name}`, () => {
        test("123 array", () => {
            const array = [3, 2, 1];
            s.sort(array);
            expect(array).toEqual([1, 2, 3]);
        });
        test("greeting strings", () => {
            const array = ["sup", "hey", "yo", "howdy"];
            s.sort(array);
            expect(array).toEqual(["hey", "howdy", "sup", "yo"]);
        });
        test("longer number array", () => {
            const array = [5, 19, 7, 4, 10, 1];
            s.sort(array);
            expect(array).toEqual([1, 4, 5, 7, 10, 19]);
        });
        test("12345 array", () => {
            const array = [5, 4, 3, 2, 1];
            s.sort(array);
            expect(array).toEqual([1, 2, 3, 4, 5]);
        });
    });
});
