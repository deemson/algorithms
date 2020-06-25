package alg05symboltables

internal class ValuesIterator<V>(private val iterator: Iterator<Pair<Any?, V>>) : Iterator<V> {
    override fun hasNext(): Boolean {
        return this.iterator.hasNext()
    }

    override fun next(): V {
        return this.iterator.next().second
    }
}