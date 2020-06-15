package alg03sorting

import alg02deques.ArrayDeque
import alg03indexable.IndexableArrayAdapter
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
            S5QuickSort,
            S6HeapSort
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

    @ParameterizedTest()
    @MethodSource("sorts")
    fun `test it sorts deque of strings`(sort: Sort) {
        val strings = ArrayDeque<String>()
        for (item in arrayOf("super", "algorithm", "main")) {
            strings.addLast(item)
        }
        sort.sort(strings)
        assertArrayEquals(arrayOf("algorithm", "main", "super"), strings.toList().toTypedArray())
        sort.sortDescending(strings)
        assertArrayEquals(arrayOf("super", "main", "algorithm"), strings.toList().toTypedArray())
    }

    @ParameterizedTest()
    @MethodSource("sorts")
    fun `test it sorts strings through sortable adapter`(sort: Sort) {
        val strings = IndexableArrayAdapter(arrayOf("super", "algorithm", "main"))
        sort.sort(strings)
        assertArrayEquals(arrayOf("algorithm", "main", "super"), strings.toList().toTypedArray())
        sort.sortDescending(strings)
        assertArrayEquals(arrayOf("super", "main", "algorithm"), strings.toList().toTypedArray())
    }
}
