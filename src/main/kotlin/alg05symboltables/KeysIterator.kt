package alg05symboltables

internal class KeysIterator<K>(private val iterator: Iterator<Pair<K, Any?>>) : Iterator<K> {
    override fun hasNext(): Boolean {
        return this.iterator.hasNext()
    }

    override fun next(): K {
        return this.iterator.next().first
    }
}
