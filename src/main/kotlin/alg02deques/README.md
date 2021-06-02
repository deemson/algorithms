# Deques #

[Deque](Deque.kt) is a utility data-structure not explained in the course.
Used in later course data-structures to reduce code duplication where a resizable
array is required.

Has two implementations -- [ArrayDeque](ArrayDeque.kt) and [LinkedDeque](LinkedDeque.kt).

[DequeExtensions](DequeExtensions.kt) contains `swap(index1: Int, index2: Int)` method
that is the same for both deques.