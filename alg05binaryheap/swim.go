package alg05binaryheap

func Swim[T any](slice []T, less Less[T], fromIndex, toIndex int) {
	childIndex := fromIndex
	parentIndex := ParentIndex(childIndex)
	for parentIndex >= toIndex && less(slice[childIndex], slice[parentIndex]) {
		swap(slice, childIndex, parentIndex)
		childIndex = parentIndex
		parentIndex = ParentIndex(childIndex)
	}
}

func SwimToTop[T any](slice []T, less Less[T], fromIndex int) {
	Swim(slice, less, fromIndex, 0)
}

func SwimFromBottom[T any](slice []T, less Less[T], toIndex int) {
	Swim(slice, less, len(slice)-1, toIndex)
}

func SwimFromBottomToTop[T any](slice []T, less Less[T]) {
	SwimFromBottom(slice, less, 0)
}
