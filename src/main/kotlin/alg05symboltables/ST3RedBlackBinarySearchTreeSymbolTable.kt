package alg05symboltables

import alg02deques.LinkedDeque
import alg02stacksqueues.Queue
import alg02stacksqueues.QueueDequeAdapter
import alg03comparators.MinComparator
import alg05redblackbinarysearchtree.RedBlackBinarySearchTree
import alg05redblackbinarysearchtree.RedBlackBinarySearchTreeImpl
import alg05redblackbinarysearchtree.RedBlackIllegalValueException
import alg05redblackbinarysearchtree.RedBlackNode

class ST3RedBlackBinarySearchTreeSymbolTable<K, V>(private val comparator: Comparator<K>) :
    AbstractSymbolTable<K, V>(),
    SymbolTable<K, V> {

    companion object {
        operator fun <K : Comparable<K>, V> invoke(): ST3RedBlackBinarySearchTreeSymbolTable<K, V> {
            return ST3RedBlackBinarySearchTreeSymbolTable(MinComparator())
        }
    }

    private inner class PairKeyComparator : Comparator<Pair<K, V>> {
        override fun compare(pair1: Pair<K, V>?, pair2: Pair<K, V>?): Int {
            val k1 = pair1?.first
            val k2 = pair2?.first
            return this@ST3RedBlackBinarySearchTreeSymbolTable.comparator.compare(k1, k2)
        }
    }

    private val tree: RedBlackBinarySearchTree<Pair<K, V>> = RedBlackBinarySearchTreeImpl(PairKeyComparator())

    override val size: Int
        get() = if (this.tree.isEmpty) 0 else this.tree.root.size

    override fun contains(key: K): Boolean {
        return try {
            this[key]
            true
        } catch (e: IllegalKeyException) {
            false
        }
    }

    private fun get(node: RedBlackNode<Pair<K, V>>, key: K): V {
        val cmp = this.comparator.compare(key, node.value.first)
        when {
            cmp < 0 -> {
                if (node.hasLeft) {
                    return this.get(node.left, key)
                } else {
                    throw IllegalKeyException(key)
                }
            }
            cmp > 0 -> {
                if (node.hasRight) {
                    return this.get(node.right, key)
                } else {
                    throw IllegalKeyException(key)
                }
            }
            else /* cmp == 0 */ -> {
                return node.value.second
            }
        }
    }

    override fun get(key: K): V {
        if (this.tree.isEmpty) {
            throw IllegalKeyException(key)
        }
        return this.get(this.tree.root, key)
    }

    override fun set(key: K, value: V) {
        this.tree.add(Pair(key, value))
    }

    private fun inOrder(node: RedBlackNode<Pair<K, V>>, queue: Queue<Pair<K, V>>) {
        if (node.hasLeft) {
            inOrder(node.left, queue)
        }
        queue.enqueue(node.value)
        if (node.hasRight) {
            inOrder(node.right, queue)
        }
    }

    override fun delete(key: K) {
        if (this.tree.isEmpty) {
            throw IllegalKeyException(key)
        }
        try {
            // value here does not matter as tree nodes are compared by key
            // just a hack in order not to do an unnecessary lookup
            this.tree.remove(Pair(key, this.tree.root.value.second))
        } catch (e: RedBlackIllegalValueException) {
            throw IllegalKeyException(key)
        }
    }

    override fun iterator(): Iterator<Pair<K, V>> {
        val queue = QueueDequeAdapter(LinkedDeque<Pair<K, V>>())
        if (!this.tree.isEmpty) {
            inOrder(this.tree.root, queue)
        }
        return queue.iterator()
    }
}
