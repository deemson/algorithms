package alg05symboltables

import alg02deques.LinkedDeque

/**
 * Basic symbol table implementation using linked list
 * Search and insert grows as O(2*N)
 */
class ST1UnorderedLinkedListSymbolTable<K, V> : AbstractSymbolTable<K, V>(), SymbolTable<K, V> {
    private val deque = LinkedDeque<Pair<K, V>>()

    override val size: Int
        get() = this.deque.size

    private fun findIndex(key: K): Int {
        for ((index, pair) in this.withIndex()) {
            if (key == pair.first) {
                return index
            }
        }
        return -1
    }

    override fun get(key: K): V {
        val index = this.findIndex(key)
        if (index == -1) {
            throw IllegalStateException("key '$key' was not found")
        } else {
            return this.deque[index].second
        }
    }

    override fun set(key: K, value: V) {
        val index = this.findIndex(key)
        val pair = Pair(key, value)
        if (index == -1) {
            this.deque.addLast(pair)
        } else {
            this.deque[index] = pair
        }
    }

    override fun contains(key: K): Boolean {
        return this.findIndex(key) != -1
    }

    override fun delete(key: K) {
        val index = this.findIndex(key)
        if (index == -1) {
            throw IllegalStateException("key '$key' was not found")
        } else {
            this.deque.removeAt(index)
        }
    }

    override fun iterator(): Iterator<Pair<K, V>> {
        return this.deque.iterator()
    }
}
