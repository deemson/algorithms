package alg01unionfind

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class TestUnionFinds {
    private companion object {
        const val size = 10

        @JvmStatic
        fun unionFinds() = listOf(
            UF1QuickFind(size),
            UF2QuickUnion(size),
            UF3WeightedQuickUnion(size),
            UF4WeightedQuickUnionWithPathCompression(size)
        )
    }

    @ParameterizedTest()
    @MethodSource("unionFinds")
    fun `test it works`(unionFind: UnionFind) {
        for (index in 1 until size) {
            assertFalse(unionFind.connected(index - 1, index))
        }
        unionFind.union(3, 5)
        assertTrue(unionFind.connected(3, 5))
        unionFind.union(3, 6)
        assertTrue(unionFind.connected(3, 6))
        assertTrue(unionFind.connected(5, 6))
        unionFind.union(7, 8)
        assertTrue(unionFind.connected(7, 8))
        assertFalse(unionFind.connected(3, 8))
    }
}
