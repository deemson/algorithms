# Indexable #

Interface [Indexable](Indexable.kt) extends [HasSize](../alg02stacksqueues/HasSize.kt)
and has methods `get(index: Int): T` and `set(index: Int, item: T)` that enable
generic index-based operations. There are two adapters: one for an
[Array](IndexableArrayAdapter.kt) and one for a [Deque](IndexableDequeAdapter.kt).

[IndexableExtensions](IndexableExtensions.kt) adds a `swap(index1: Int, index2: Int)`
method to an indexable irrespective of the actual implementation.