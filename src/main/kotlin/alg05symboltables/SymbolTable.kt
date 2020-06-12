package alg05symboltables

import alg02stacksqueues.HasSize

interface SymbolTable<K, V> : HasSize, Iterable<Pair<K, V>> {
    operator fun get(key: K): V
    operator fun set(key: K, value: V)
    operator fun contains(key: K): Boolean
    fun delete(key: K)
    fun keys(): Iterator<K>
    fun values(): Iterator<V>
}
