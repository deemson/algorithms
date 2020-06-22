package alg05redblackbinarysearchtree

class RedBlackIsEmptyException : Exception() {
    override val message: String?
        get() = "tree is empty"
}
