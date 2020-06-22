package alg05redblackbinarysearchtree

interface RedBlackBinarySearchTree<T> {
    val root: RedBlackNode<T>
    val isEmpty: Boolean
    fun add(value: T)
    fun remove(value: T)
}
