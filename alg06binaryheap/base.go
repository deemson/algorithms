package alg06binaryheap

func ParentIndex(index int) int {
	return (index - 1) / 2
}

func ChildIndex(index int) int {
	return (index+1)*2 - 1
}
