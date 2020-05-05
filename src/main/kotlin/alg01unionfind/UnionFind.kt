package alg01unionfind

interface UnionFind {
    fun union(n1: Int, n2: Int)
    fun connected(n1: Int, n2: Int): Boolean
}