package alg03sorting

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource


class TestSorts {

    private companion object {
        @JvmStatic
        fun sorts() = listOf(
                S1SelectionSort,
                S2InsertionSort,
                S3ShellSort,
                S4aMergeSort,
                S4bBottomUpMergeSort,
                S5QuickSort
        )
    }

    @ParameterizedTest()
    @MethodSource("sorts")
    fun `test it sorts integers`(sort: SortingAlgorithm) {
        val integers = arrayOf(42, 17, 100500, 123)
        sort.sort(integers)
        assertArrayEquals(arrayOf(17, 42, 123, 100500), integers)
    }

    @ParameterizedTest()
    @MethodSource("sorts")
    fun `test it sorts strings`(sort: SortingAlgorithm) {
        val strings = arrayOf("super", "algorithm", "main")
        sort.sort(strings)
        assertArrayEquals(arrayOf("algorithm", "main", "super"), strings)
    }

}