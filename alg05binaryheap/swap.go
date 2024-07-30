package alg05binaryheap

func swap[T any](slice []T, index1, index2 int) {
	tmp := slice[index1]
	slice[index1] = slice[index2]
	slice[index2] = tmp
}
