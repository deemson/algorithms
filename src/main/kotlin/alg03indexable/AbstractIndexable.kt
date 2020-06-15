package alg03indexable

abstract class AbstractIndexable<T> : Indexable<T> {
    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        @Suppress("UNCHECKED_CAST")
        val ordered = other as Indexable<T>
        if (this.size != ordered.size) {
            return false
        }
        for (index in this.indices) {
            if (this[index] != ordered[index]) {
                return false
            }
        }
        return true
    }
}
