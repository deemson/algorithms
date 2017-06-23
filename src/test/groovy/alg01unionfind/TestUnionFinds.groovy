package alg01unionfind

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameter
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized)
class TestUnionFinds {
    @Parameters(name = '{index}: {0}')
    static List<Class<UnionFindAlgorithm>> sortingAlgorithmClasses() {
        return [UF1QuickFind, UF2QuickUnion, UF3WeightedQuickUnion, UF4WeightedQuickUnionWithPathCompression]
    }

    static SIZE = 10
    @Parameter
    public Class<UnionFindAlgorithm> UnionFindAlgorithmClass;
    UnionFindAlgorithm unionFindAlgorithm;

    @Before
    void setUp() {
        unionFindAlgorithm = UnionFindAlgorithmClass.newInstance(SIZE)
    }

    @Test
    void test() {
        for (i in 1..SIZE - 1) {
            assert !unionFindAlgorithm.connected(i - 1, i)
        }
        unionFindAlgorithm.union(3, 5)
        assert unionFindAlgorithm.connected(3, 5)
        unionFindAlgorithm.union(3, 6)
        assert unionFindAlgorithm.connected(3, 6)
        assert unionFindAlgorithm.connected(5, 6)
        unionFindAlgorithm.union(7, 8)
        assert unionFindAlgorithm.connected(7, 8)
        assert !unionFindAlgorithm.connected(3, 8)
    }
}