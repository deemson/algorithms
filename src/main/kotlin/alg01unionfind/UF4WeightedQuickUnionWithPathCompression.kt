package alg01unionfind

/**
 * Another improvement for QuickUnion: compress tree paths by resetting node parents to tree roots
 * during root lookups.
 */
class UF4WeightedQuickUnionWithPathCompression(size: Int) : UF3WeightedQuickUnion(size) {
    override fun treeRoot(n: Int): Int {
        val root = super.treeRoot(n)
        var nWithNonRootParent = n
        // While the parent of a node in question is not actually root node it's remapped to root
        // to compress three paths.
        while (nWithNonRootParent != root) {
            val next_nWithNonRootParent = this.parentOf[nWithNonRootParent]
            this.parentOf[nWithNonRootParent] = root
            nWithNonRootParent = next_nWithNonRootParent
        }
        return root
    }
}