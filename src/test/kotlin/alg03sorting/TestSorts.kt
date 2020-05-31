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
    fun `test it sorts integers`(sort: Sort) {
        val integers = arrayOf(
            42,
            17,
            100500,
            123,
            3,
            13,
            256,
            4242,
            127
        )
        sort.sort(integers)
        assertArrayEquals(
            arrayOf(
                3,
                13,
                17,
                42,
                123,
                127,
                256,
                4242,
                100500
            ), integers
        )
        sort.sortDescending(integers)
        assertArrayEquals(
            arrayOf(
                100500,
                4242,
                256,
                127,
                123,
                42,
                17,
                13,
                3
            ),
            integers
        )
    }

    @ParameterizedTest()
    @MethodSource("sorts")
    fun `test it sorts strings`(sort: Sort) {
        val strings = arrayOf("super", "algorithm", "main")
        sort.sort(strings)
        assertArrayEquals(arrayOf("algorithm", "main", "super"), strings)
        sort.sortDescending(strings)
        assertArrayEquals(arrayOf("super", "main", "algorithm"), strings)
    }
}
