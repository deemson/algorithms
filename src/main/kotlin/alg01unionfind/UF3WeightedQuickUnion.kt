package alg01unionfind

open class UF3WeightedQuickUnion(size: Int) : UF2QuickUnion(size) {

    // Initially all trees contain only one node
    protected val sizeOfTree: Array<Int> = Array(size) { 1 }

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