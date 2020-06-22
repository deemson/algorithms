package alg05redblackbinarysearchtree

class RedBlackIllegalValueException(val value: Any?) : Exception() {
    override val message: String?
        get() = "value $value is not present in the tree"
}
