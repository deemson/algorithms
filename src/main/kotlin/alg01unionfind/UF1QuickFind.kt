package alg01unionfind

/**
 * Find is quick, but union is slow: hence the name.
 */
class UF1QuickFind(size: Int) : UnionFind {
    private val ids: Array<Int> = Array(size) { index: Int -> index }

    /**
     * This is slow as O(N).
     */
    override fun union(n1: Int, n2: Int) {
        val n1id = this.ids[n1]
        val n2id = this.ids[n2]
        /*
        Every occurrence of n1id is changed to n2id.
        This is as slow as O(N) and is going to be O(N^2) if N union find is required.
         */
        for (index in this.ids.indices) {
            if (this.ids[index] == n1id) {
                this.ids[index] = n2id
            }
        }
    }

    /**
     * This is fast as O(1).
     */
    override fun connected(n1: Int, n2: Int): Boolean {
        return ids[n1] == ids[n2]
    }
}