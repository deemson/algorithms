package alg03ordered

abstract class AbstractOrdered<T> : Ordered<T> {
    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        @Suppress("UNCHECKED_CAST")
        val ordered = other as Ordered<T>
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
