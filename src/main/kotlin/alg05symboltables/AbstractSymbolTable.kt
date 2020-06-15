package alg05symboltables

abstract class AbstractSymbolTable<K, V> : SymbolTable<K, V> {
    private abstract inner class ContentIterator {
        val iterator = this@AbstractSymbolTable.iterator()

        fun hasNext(): Boolean {
            return this.iterator.hasNext()
        }
    }

    private inner class KeysIterator : ContentIterator(), Iterator<K> {
        override fun next(): K {
            return this.iterator.next().first
        }
    }

    override fun keys(): Iterator<K> {
        return KeysIterator()
    }

    private inner class ValuesIterator : ContentIterator(), Iterator<V> {
        override fun next(): V {
            return this.iterator.next().second
        }
    }

    override fun values(): Iterator<V> {
        return ValuesIterator()
    }

    protected fun illegalKeyException(key: K): IllegalArgumentException {
        return IllegalArgumentException("key \"$key\" was not found")
    }
}
