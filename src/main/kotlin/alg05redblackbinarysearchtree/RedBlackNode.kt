package alg05redblackbinarysearchtree

interface RedBlackNode<T> {
    val value: T
    val hasLeft: Boolean
    val left: RedBlackNode<T>
    val hasRight: Boolean
    val right: RedBlackNode<T>
    val isRed: Boolean
    val isBlack: Boolean
        get() = !this.isRed
    val size: Int

    fun compare(value: T): Int
}
