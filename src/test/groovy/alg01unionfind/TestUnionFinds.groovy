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

    @Parameter
    public Class<UnionFindAlgorithm> UnionFindAlgorithmClass;
    UnionFindAlgorithm unionFindAlgorithm;

    @Before
    void setUp() {
        unionFindAlgorithm = UnionFindAlgorithm.newInstance()
    }

    @Test
    void test() {
        unionFindAlgorithm.union()
    }
}