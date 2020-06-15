package alg02deques

abstract class AbstractDeque<T> : Deque<T> {
    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        @Suppress("UNCHECKED_CAST")
        val deque = other as Deque<T>
        if (this.size != deque.size) {
            return false
        }
        for (index in this.indices) {
            if (this[index] != deque[index]) {
                return false
            }
        }
        return true
    }
}
