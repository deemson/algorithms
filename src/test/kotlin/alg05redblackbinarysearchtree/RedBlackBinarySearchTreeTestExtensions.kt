package alg05redblackbinarysearchtree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue

fun <T> RedBlackNode<T>.assertIsBinarySearchTree(from: T? = null, to: T? = null) {
    assertTrue(
        from == null || this.compare(from) > 0,
        "node ${this.value} must be larger than $from"
    )
    if (this.hasLeft) {
        this.left.assertIsBinarySearchTree(from, this.value)
    }
    assertTrue(
        to == null || this.compare(to) < 0,
        "node ${this.value} must be smaller than $to"
    )
    if (this.hasRight) {
        this.right.assertIsBinarySearchTree(this.value, to)
    }
}

fun <T> RedBlackBinarySearchTree<T>.assertIsBinarySearchTree() {
    if (this.isEmpty) {
        return
    }
    this.root.assertIsBinarySearchTree()
}

fun <T> RedBlackNode<T>.assertSizeIsConsistent() {
    var leftSize = 0
    if (this.hasLeft) {
        leftSize = this.left.size
        this.left.assertSizeIsConsistent()
    }
    var rightSize = 0
    if (this.hasRight) {
        rightSize = this.right.size
        this.right.assertSizeIsConsistent()
    }
    val expectedSize = leftSize + 1 + rightSize
    assertEquals(
        expectedSize,
        this.size,
        "node ${this.value} has inconsistent size: got ${this.size}, expected $expectedSize"
    )
}

fun <T> RedBlackBinarySearchTree<T>.assertSizeIsConsistent() {
    if (this.isEmpty) {
        return
    }
    this.root.assertSizeIsConsistent()
}

fun <T> RedBlackNode<T>.assertIs23Tree() {
    if (this.hasLeft) {
        this.left.assertIs23Tree()
        assertFalse(
            this.isRed && this.left.isRed,
            "node ${this.value} is red and subsequent left ${this.left.value} is also red"
        )
    }
    if (this.hasRight) {
        assertFalse(this.right.isRed, "node ${this.value} has a red right child")
        this.right.assertIs23Tree()
    }
}

fun <T> RedBlackBinarySearchTree<T>.assertIs23Tree() {
    if (this.isEmpty) {
        return
    }
    this.root.assertIs23Tree()
}

fun <T> RedBlackNode<T>.isBalanced(referenceBlackCount: Int) {
    val childReferenceBlackCount = if (this.isBlack) referenceBlackCount - 1 else referenceBlackCount
    val errMess = if (childReferenceBlackCount > 0) {
        "$childReferenceBlackCount less black links leading to it than the reference path"
    } else {
        "${-childReferenceBlackCount} more black links leading to it than the reference path"
    }
    if (this.hasLeft) {
        this.left.isBalanced(childReferenceBlackCount)
    } else {
        if (childReferenceBlackCount != 0) {
            throw IllegalStateException("leaf node ${this.value} has no left and has $errMess")
        }
    }
    if (this.hasRight) {
        this.right.isBalanced(childReferenceBlackCount)
    } else {
        if (childReferenceBlackCount != 0) {
            throw IllegalStateException("leaf node ${this.value} has no right and has $errMess")
        }
    }
}

fun <T> RedBlackBinarySearchTree<T>.isBalanced() {
    if (this.isEmpty) {
        return
    }
    var node = this.root
    var blackNodesCountInFirstPath = if (node.isBlack) 1 else 0
    while (node.hasLeft) {
        node = node.left
        if (node.isBlack) {
            blackNodesCountInFirstPath++
        }
    }
    this.root.isBalanced(blackNodesCountInFirstPath)
}

fun <T> RedBlackBinarySearchTree<T>.doAssertions() {
    this.assertIsBinarySearchTree()
    this.assertSizeIsConsistent()
    this.assertIs23Tree()
    this.isBalanced()
}
