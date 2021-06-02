package alg01unionfind

/**
 * Fixes the drawbacks of UF1QuickUnion.
 * Union is implemented by setting parent-child nodes creating a tree for complex unions.
 * Drawbacks: trees can get tall making finds expensive (could be up to N in a worst case).
 */
open class UF2QuickUnion(size: Int) : UnionFind {

    protected val parentOf: Array<Int> = Array(size) { index: Int -> index }

    /**
     * This is the method for finding union tree root.
     * The roots are the nodes, that were never been set as an other's node child
     * therefore having the parent it had during the initialization, i.e. i == parentOf[i]
     * meaning it is a parent to itself.
     */
    protected open fun treeRoot(n: Int): Int {
        var root = n
        while (root != this.parentOf[root]) {
            // Cycle thought the node's parents until a node that is parent to itself is found
            root = this.parentOf[root]
        }
        return root
    }

    override fun connected(n1: Int, n2: Int): Boolean {
        return this.treeRoot(n1) == this.treeRoot(n2)
    }

    /**
     * To union two nodes the root of one node is set as a parent to the root of another node
     * (which root is parent to which does not matter) creating a bigger tree in a process.
     * This implementation sets the root of the n1 as a parent to the root of n2.
     */
    override fun union(n1: Int, n2: Int) {
        val n1TreeRoot = this.treeRoot(n1)
        val n2TreeRoot = this.treeRoot(n2)
        parentOf[n2TreeRoot] = n1TreeRoot
    }
}
