package alg05symboltables

import alg02deques.LinkedDeque
import kotlin.math.absoluteValue

class ST4HashSymbolTable<K, V>(capacity: Int = 2, private val loadFactor: Float = 0.75f) : SymbolTable<K, V> {
    private var array = Array<LinkedDeque<Pair<K, V>>>(capacity) { LinkedDeque() }
    private var occupiedIndexes = 0
    private var _size = 0

    override val size: Int
        get() = this._size

    private val load: Float
        get() = this.occupiedIndexes.toFloat() / this.array.size

    private fun arrayIndex(key: K): Int {
        return key.hashCode().absoluteValue.rem(this.array.size)
    }

    override fun contains(key: K): Boolean {
        return try {
            this[key]
            true
        } catch (e: IllegalKeyException) {
            false
        }
    }

    override fun get(key: K): V {
        val deque = this.array[this.arrayIndex(key)]
        for (pair in deque) {
            if (pair.first == key) {
                return pair.second
            }
        }
        throw IllegalKeyException(key)
    }

    private fun set(pair: Pair<K, V>) {
        val deque = this.array[this.arrayIndex(pair.first)]
        if (deque.isEmpty) {
            this.occupiedIndexes++
            deque.addLast(pair)
            this._size++
        } else {
            var isSet = false
            for ((index, existingPair) in deque.withIndex()) {
                if (pair.first == existingPair.first) {
                    deque[index] = pair
                    isSet = true
                }
            }
            if (!isSet) {
                deque.addLast(pair)
                this._size++
            }
        }
    }

    private fun resize(capacity: Int) {
        val oldArray = this.array
        this.array = Array<LinkedDeque<Pair<K, V>>>(capacity) { LinkedDeque() }
        this.occupiedIndexes = 0
        this._size = 0
        for (deque in oldArray) {
            for (pair in deque) {
                this.set(pair)
            }
        }
    }

    private fun growIfRequired() {
        if (this.load >= this.loadFactor) {
            this.resize(this.array.size * 2)
        }
    }

    private fun shrinkIfRequired() {
        /*
        If array is quarter full it is shrunk to be half-size.
        It is resized at quarter capacity to avoid "thrashing" (constant resizing)
        when working with half-full array and doing add-remove operations.
        */
        if (this._size > 0 && this.load <= this.loadFactor / 4) {
            this.resize(this.array.size / 2)
        }
    }

    override fun set(key: K, value: V) {
        val oldSize = this._size
        this.set(Pair(key, value))
        if (this._size - oldSize > 0) {
            this.growIfRequired()
        }
    }

    override fun delete(key: K) {
        val deque = this.array[this.arrayIndex(key)]
        for ((index, pair) in deque.withIndex()) {
            if (pair.first == key) {
                deque.removeAt(index)
                this._size--
                if (deque.isEmpty) {
                    this.occupiedIndexes--
                }
                this.shrinkIfRequired()
                return
            }
        }
        throw IllegalKeyException(key)
    }

    override fun iterator(): Iterator<Pair<K, V>> {
        val totalDeque = LinkedDeque<Pair<K, V>>()
        for (deque in this.array) {
            for (pair in deque) {
                totalDeque.addLast(pair)
            }
        }
        return totalDeque.iterator()
    }
}
