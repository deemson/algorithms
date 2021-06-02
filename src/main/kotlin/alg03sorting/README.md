# Sorting #

[Sort](Sort.kt) is an interface for all sorts. It expects [Indexable](../alg03indexable/Indexable.kt)
to enable sorting of data-structures other than basic array.

[SortExtensions](SortExtensions.kt) contains syntactic sugar for trivial
sorts for all sort implementations.

[S1SelectionSort](S1SelectionSort.kt) loops through all elements in an
indexable, finds a minimum element to the right of the current element and
swaps them.

[S2InsertionSort](S2InsertionSort.kt) uses two loops -- outer and inner.
Outer loops through all elements in an interable and adds them one by one
to the inner loop. Inner loop sorts all elements added by the outer loop.

[S3ShellSort](S3ShellSort.kt) is similar to previous implementation,
but tries to reduce its inefficiency by sorting with bigger steps.

[S4AbstractMergeSort](S4AbstractMergeSort.kt) a base class for all merge
sorts. Contains `merge` method to merge back the results of this divide-and-conquer
algorithm.

[S4aMergeSort](S4aMergeSort.kt) is a divide-and-conquer algorithm that
splits an indexable until the sorting is trivial (2 elements) and uses `merge`
method to merge divided parts back.

[S4bBottomUpMergeSort](S4bBottomUpMergeSort.kt) an alternative merge sort
implementation without the recursion.

[S5QuickSort](S5QuickSort.kt) is another divide-and-conquer algorithm.
Uses `partition` method which is the heart of this algorithm -- refer to its
source code docs.

[S6HeapSort](S6HeapSort.kt) is an interesting efficient sort implementation.
Uses [BinaryHeapIndexableExtensions](../alg04binaryheap/BinaryHeapIndexableExtensions.kt)
to implement binary head data-structure which is used in the implementation.
Refer to source code docs for pros and cons.