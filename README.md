# Algorithms #

## Summary ##

This repo contains code that implements algorithms in Kotlin for both
[Part 1](https://www.coursera.org/learn/algorithms-part1) and
[Part 2](https://www.coursera.org/learn/algorithms-part2) Coursera courses.

There are several abstractions added to reduce code duplication and make it 
a bit more re-usable across different algorithms. There are also tests
added to check the implementations and show how the algorithms are used.

## How to use ##

The code in this repo is meant to be an educational supplement. You can use
`./gradlew test` (or `./gradlew.bat test` if you're on Windows) to run all tests.

## Contents ##

### 01. Union Find ###

[Docs](src/main/kotlin/alg01unionfind/README.md)

[Test](src/test/kotlin/alg01unionfind/TestUnionFinds.kt)

First data-structure explained in the course. The implementation is very 
close to that in the course.

### 02. Deques ###

[Docs](src/main/kotlin/alg02deques/README.md)

[Test](src/test/kotlin/alg02deques/TestDeques.kt)

Not explained in the course: an abstraction created to reduce code duplication
caused by the need of having a resizable array in various algorithms later in the course.

### 02. Stacks & Queues ###

[Docs](src/main/kotlin/alg02stacksqueues/README.md)

[Stacks Test](src/test/kotlin/alg02stacksqueues/TestStacks.kt)

[Queues Test](src/test/kotlin/alg02stacksqueues/TestQueues.kt)

Stacks & Queues data-structures implementations. Stack & Queue
interfaces are implemented by using deques via adapters.

### 03. Comparators ###

[Docs](src/main/kotlin/alg03comparators/README.md)

Not explained in the course: package contains a bunch of utility
comparators for the ordered data-structures explained later in the course.

### 03. Indexable ###

[Docs](src/main/kotlin/alg03indexable/README.md)

Not explained in the course: package contains an interface that enables
the usage of any data-structure that supports index access with other
data-structures. Contains adapters for an array and a deque.

### 03. Sorting ###

[Docs](src/main/kotlin/alg03sorting/README.md)

[Test](src/test/kotlin/alg03sorting/TestSorts.kt)

All sorting algorithms as explained in the course. The only difference:
all implementations expect indexable to enable sorting of any data-structure
that supports index based operations, not only arrays.

## 04. Binary Heap ##

[Code](src/main/kotlin/alg04binaryheap/BinaryHeapIndexableExtensions.kt)

Not explained in the course. A bunch of additional method for indexable
to implement binary heap data-structure. Used in binary heap sort (see sorting).