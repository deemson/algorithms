package alg03sorting

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameter
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized)
class TestSorts {
    @Parameters(name = '{index}: {0}')
    static List<Class<SortingAlgorithm>> sortingAlgorithmClasses() {
        return [S1SelectionSort, S2InsertionSort, S3ShellSort, S4MergeSort, S5BottomUpMergeSort, S6QuickSort]
    }

    @Parameter
    public Class<SortingAlgorithm> SortingAlgorithmClass;
    SortingAlgorithm sortingAlgorithm;

    @Before
    void setUp() {
        sortingAlgorithm = SortingAlgorithmClass.newInstance()
    }

    @Test
    void test() {
        def a = (1..20).toList()
        while (SortUtils.isSorted(a.toArray() as Integer[])) {
            Collections.shuffle(a)
        }
        a = a.toArray() as Integer[]
        sortingAlgorithm.sort(a)
        assert SortUtils.isSorted(a)
    }
}