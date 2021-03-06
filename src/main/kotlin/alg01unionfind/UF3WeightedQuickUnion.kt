package alg01unionfind

/**
 * Addresses the main drawback of UF2QuickUnion: trees growing too big.
 * Avoid tree growth by storing tree sizes and checking it before attaching trees to each other.
 */
open class UF3WeightedQuickUnion(size: Int) : UF2QuickUnion(size) {

    // Initially all trees contain only one node
    private val sizeOfTree: Array<Int> = Array(size) { 1 }

    /**
     * Union and find will take at most O(log(N)) operations to complete,
     * as the size of the trees is reduced.
     */
    override fun union(n1: Int, n2: Int) {
        val n1TreeRoot = this.treeRoot(n1)
        val n2TreeRoot = this.treeRoot(n2)
        // A smaller tree is set as a child to a taller tree to avoid trees growth.
        if (this.sizeOfTree[n1TreeRoot] < this.sizeOfTree[n2TreeRoot]) {
            this.parentOf[n1TreeRoot] = n2TreeRoot
            this.sizeOfTree[n2TreeRoot] += this.sizeOfTree[n1TreeRoot]
        } else {
            this.parentOf[n2TreeRoot] = n1TreeRoot
            this.sizeOfTree[n1TreeRoot] += this.sizeOfTree[n2TreeRoot]
        }
    }
}
