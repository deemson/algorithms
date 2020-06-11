package alg04priorityqueues

import alg02stacksqueues.HasSize

interface PriorityQueue<T> : HasSize {
    fun insert(item: T)
    fun delete(): T
}