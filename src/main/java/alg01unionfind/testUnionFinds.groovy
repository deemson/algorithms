package alg01unionfind

size = 10
def listOfUnionFinds = [
        new UF1QuickFind(size),
        new UF2QuickUnion(size),
        new UF3WeightedQuickUnion(size),
        new UF4WeightedQuickUnionWithPathCompression(size)
]

def testUnionFind(UnionFindAlgorithm uf) {
    print("Testing ${uf.getClass().getSimpleName()} union find implementation... ")
    for (i in 1..size - 1) {
        assert !uf.connected(i - 1, i)
    }
    uf.union(3, 5)
    assert uf.connected(3, 5)
    uf.union(3, 6)
    assert uf.connected(3, 6)
    assert uf.connected(5, 6)
    uf.union(7, 8)
    assert uf.connected(7, 8)
    assert !uf.connected(3, 8)
    print("Pass!\n")
}

for (unionFind in listOfUnionFinds) {
    testUnionFind(unionFind)
}