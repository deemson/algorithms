package alg03sorting

fun <T> exchange(array: Array<T>, element1Index: Int, element2Index: Int) {
    val element1Value = array[element1Index]
    array[element1Index] = array[element2Index]
    array[element2Index] = element1Value
}
