package alg05symboltables

import alg02deques.arrayDequeOf
import alg03comparators.MinComparator

class ST2RankedBinarySearchSymbolTable<K, V>(private val comparator: Comparator<K>) :
    AbstractSymbolTable<K, V>(),
    SymbolTable<K, V> {

    private val deque = arrayDequeOf<Pair<K, V>>()

    companion object {
        operator fun <K : Comparable<K>, V> invoke(): ST2RankedBinarySearchSymbolTable<K, V> {
            return ST2RankedBinarySearchSymbolTable(MinComparator())
        }
    }

    override val size: Int
        get() = this.deque.size

    /*
        returns the number of keys in this symbol table strictly less than key
         */
    private fun rank(key: K): Int {
        var lo = 0
        var hi = this.deque.size - 1
        while (lo <= hi) {
            val mid = lo + (hi - lo) / 2
            val cmp = this.comparator.compare(key, this.deque[mid].first)
            when {
                cmp < 0 -> hi = mid - 1
                cmp > 0 -> lo = mid + 1
                cmp == 0 -> return mid
            }
        }
        return lo
    }

    override fun contains(key: K): Boolean {
        if (this.isEmpty) {
            return false
        }
        val rank = this.rank(key)
        if (rank == this.size) {
            return false
        }
        return this.deque[rank].first == key
    }

    override fun get(key: K): V {
        if (this.isEmpty) {
            throw this.illegalKeyException(key)
        }
        val rank = this.rank(key)
        if (rank == this.size) {
            throw this.illegalKeyException(key)
        }
        val pair = this.deque[rank]
        if (pair.first == key) {
            return pair.second
        } else {
            throw this.illegalKeyException(key)
        }
    }

    override fun set(key: K, value: V) {
        val rank = this.rank(key)
        val pair = Pair(key, value)
        when {
            rank == this.size -> this.deque.addLast(pair)
            this.deque[rank].first == key -> deque[rank] = pair
            else -> this.deque.addAt(rank + 1, pair)
        }
    }

    override fun delete(key: K) {
        if (this.isEmpty) {
            throw this.illegalKeyException(key)
        }
        val rank = this.rank(key)
        if (rank == this.size) {
            throw this.illegalKeyException(key)
        }
        val pair = this.deque[rank]
        if (pair.first == key) {
            this.deque.removeAt(rank)
        } else {
            throw this.illegalKeyException(key)
        }
    }

    override fun iterator(): Iterator<Pair<K, V>> {
        return this.deque.iterator()
    }
}
