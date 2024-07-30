package alg05binaryheap

func Sink[T any](slice []T, less Less[T], fromIndex, toIndex int) {
	parentIndex := fromIndex
	childIndex := ChildIndex(parentIndex)
	for childIndex <= toIndex {
		if childIndex < toIndex && less(slice[childIndex+1], slice[childIndex]) {
			childIndex++
		}
		if less(slice[parentIndex], slice[childIndex]) {
			break
		}
		swap(slice, parentIndex, childIndex)
		parentIndex = childIndex
		childIndex = ChildIndex(parentIndex)
	}
}

func SinkFromTop[T any](slice []T, less Less[T], toIndex int) {
	Sink(slice, less, 0, toIndex)
}

func SinkToBottom[T any](slice []T, less Less[T], fromIndex int) {
	Sink(slice, less, fromIndex, len(slice)-1)
}

func SinkFromTopToBottom[T any](slice []T, less Less[T]) {
	SinkToBottom(slice, less, 0)
}
