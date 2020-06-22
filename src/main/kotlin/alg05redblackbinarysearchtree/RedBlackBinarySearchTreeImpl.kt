package alg05redblackbinarysearchtree

import alg03comparators.MinComparator

class RedBlackBinarySearchTreeImpl<T>(private val comparator: Comparator<T>) : RedBlackBinarySearchTree<T> {
    companion object {
        operator fun <T : Comparable<T>> invoke(): RedBlackBinarySearchTreeImpl<T> {
            return RedBlackBinarySearchTreeImpl(MinComparator())
        }
    }

    private inner class Node(value: T) : RedBlackNode<T> {
        var _value = value
        var _left: Node? = null
        var _right: Node? = null
        var _isRed = true
        var _size = 1

        private fun childIsRed(node: Node?): Boolean {
            return node?.isRed ?: false
        }

        val leftIsRed: Boolean
            get() = this.childIsRed(this._left)
        val leftIsBlack: Boolean
            get() = !this.leftIsRed
        val rightIsRed: Boolean
            get() = this.childIsRed(this._right)
        val rightIsBlack: Boolean
            get() = !this.rightIsRed

        val leftSize: Int
            get() = this._left?._size ?: 0
        val rightSize: Int
            get() = this._right?._size ?: 0

        private fun recalculateSize() {
            this._size = this.leftSize + 1 + this.rightSize
        }

        override val value: T
            get() = this._value
        override val hasLeft: Boolean
            get() = this._left != null
        override val left: RedBlackNode<T>
            get() = this._left ?: throw IllegalStateException("node $_value has no left child")
        override val hasRight: Boolean
            get() = this._right != null
        override val right: RedBlackNode<T>
            get() = this._right ?: throw IllegalStateException("node $_value has no right child")
        override val isRed: Boolean
            get() = this._isRed
        override val size: Int
            get() = this._size

        fun markRed() {
            this._isRed = true
        }

        fun markBlack() {
            this._isRed = false
        }

        override fun compare(value: T): Int {
            return this@RedBlackBinarySearchTreeImpl.comparator.compare(this._value, value)
        }

        fun contains(value: T): Boolean {
            val cmp = this.compare(value)
            when {
                cmp > 0 -> return this._left?.contains(value) ?: false
                cmp < 0 -> return this._right?.contains(value) ?: false
                else /* cmp == 0 */ -> {
                    return true
                }
            }
        }

        /*
        orient a (temporarily) right-leaning red link to lean left
        to maintain perfect balance
        */
        private fun rotateLeft(): Node {
            // replacement node must be red
            // it is the red node that we move counterclockwise to replace the current node
            val replacementNode = this._right!!
            // keys in replacementNode.left are in range (this.key..replacementNode.key)
            // so this what must become this node's right
            this._right = replacementNode._left
            // replacementNode's left now points to this node
            replacementNode._left = this
            // replacementNode's color is changed to what was the color of this node
            replacementNode._isRed = this._isRed
            // this node color becomes the color of replacementNode (red)
            this.markRed()
            // replacementNode's size is changed to what was the size of this node
            replacementNode._size = this._size
            // finally we recalculate the size of the current node
            this.recalculateSize()
            return replacementNode
        }

        /*
        orient a left leaning node to (temporarily) lean right
         */
        private fun rotateRight(): Node {
            // replacement node must be red
            val replacementNode = this._left!!
            // keys in replacementNode.right are in range (replacementNode.key..this.key)
            // so this what must become this node's left
            this._left = replacementNode._right
            // replacementNode's right now points to this node
            replacementNode._right = this
            // replacementNode's color is changed to what was the color of this node
            replacementNode._isRed = this._isRed
            // this node color becomes the color of replacementNode (red)
            this.markRed()
            // replacementNode's size is changed to what was the size of this node
            replacementNode._size = this._size
            // finally we recalculate the size of the current node
            this.recalculateSize()
            return replacementNode
        }

        private fun flipColors() {
            this._isRed = !this._isRed
            this._left!!._isRed = !this._left!!._isRed
            this._right!!._isRed = !this._right!!._isRed
        }

        private fun maybeFixRightRedChildAfterAdd(): Node {
            if (this.rightIsRed && this.leftIsBlack) {
                return this.rotateLeft()
            }
            return this
        }

        private fun maybeFixRightRedChildAfterRemove(): Node {
            if (this.rightIsRed) {
                return this.rotateLeft()
            }
            return this
        }

        private fun maybeFixTwoSubsequentLeftRedChildren(): Node {
            if (this.leftIsRed && this._left!!.leftIsRed) {
                return this.rotateRight()
            }
            return this
        }

        private fun maybeFixTwoRedChildren(): Node {
            if (this.leftIsRed && this.rightIsRed) {
                this.flipColors()
            }
            return this
        }

        fun balanceAfterAdd(): Node {
            val node = this
                .maybeFixRightRedChildAfterAdd()
                .maybeFixTwoSubsequentLeftRedChildren()
                .maybeFixTwoRedChildren()
            node.recalculateSize()
            return node
        }

        fun balanceAfterRemove(): Node {
            val node = this
                .maybeFixRightRedChildAfterRemove()
                .maybeFixTwoSubsequentLeftRedChildren()
                .maybeFixTwoRedChildren()
            node.recalculateSize()
            return node
        }

        /*
        Assumes that this is red and its two left children are both black.
        Used to move red to the left when removal from the left tree is anticipated.
        This is to maintain balance.
         */
        private fun moveRedLeft(): Node {
            this.flipColors()
            if (this._right!!.leftIsRed) {
                this._right = this._right!!.rotateRight()
                val node = this.rotateLeft()
                node.flipColors()
                return node
            }
            return this
        }

        fun maybeMoveRedLeftForRemoval(): Node {
            if (this.leftIsBlack && this._left!!.leftIsBlack) {
                return this.moveRedLeft()
            }
            return this
        }

        private fun moveRedRight(): Node {
            this.flipColors()
            if (this._left!!.leftIsRed) {
                val node = this.rotateRight()
                node.flipColors()
                return node
            }
            return this
        }

        fun maybeMoveRedRightForRemoval(): Node {
            if (this.rightIsBlack && this._right!!.leftIsBlack) {
                return this.moveRedRight()
            }
            return this
        }

        fun findMin(): Node {
            if (this._left == null) {
                return this
            }
            return this._left!!.findMin()
        }

        fun removeMin(): Node? {
            if (this._left == null) {
                return null
            }
            val node = maybeMoveRedLeftForRemoval()
            node._left = node._left!!.removeMin()
            return node.balanceAfterRemove()
        }

        fun remove(value: T): Node? {
            var node = this
            if (node.compare(value) > 0) {
                node.maybeMoveRedLeftForRemoval()
                node._left = this._left!!.remove(value)
            } else {
                if (node.leftIsRed) {
                    node = node.rotateRight()
                }
                if (node.compare(value) == 0 && node._right == null) {
                    return null
                }
                node = node.maybeMoveRedRightForRemoval()
                if (node.compare(value) == 0) {
                    val minNode = node._right!!.findMin()
                    node._value = minNode._value
                    node._right = node._right!!.removeMin()
                } else {
                    node._right = node._right!!.remove(value)
                }
            }
            return node.balanceAfterRemove()
        }
    }

    private var _root: Node? = null

    override val root: RedBlackNode<T>
        get() = this._root ?: throw RedBlackIsEmptyException()
    override val isEmpty: Boolean
        get() = this._root == null

    private fun add(node: Node?, value: T): Node {
        if (node == null) {
            return Node(value)
        }
        val cmp = node.compare(value)
        when {
            cmp > 0 -> node._left = this.add(node._left, value)
            cmp < 0 -> node._right = this.add(node._right, value)
            cmp == 0 -> node._value = value
        }
        return node.balanceAfterAdd()
    }

    override fun add(value: T) {
        this._root = this.add(this._root, value)
        this._root!!.markBlack()
    }

    override fun remove(value: T) {
        if (this.isEmpty) {
            throw RedBlackIsEmptyException()
        }
        val root = this._root!!
        if (!root.contains(value)) {
            throw RedBlackIllegalValueException(value)
        }
        /*
        It does not actually matter which color root is as there are not nodes above root.
        However, when it's required to push red links down to one
        of it's children of the left (in order to maintain balance after removal)
        we must somehow "materialize" red link above root.
        As the color of the root does not matter, it's just marked red.
         */
        if (root.leftIsBlack && root.rightIsBlack) {
            root.markRed()
        }
        this._root = root.remove(value)
        if (!this.isEmpty) {
            // if tree is not empty after deletion, mark root black again
            this._root!!.markBlack()
        }
    }
}
