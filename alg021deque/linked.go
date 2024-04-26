package alg021deque

func Linked[T any]() Deque[T] {
	return Deque[T]{
		algorithm: &LinkedAlgorithm[T]{
			size: 0,
			head: nil,
			tail: nil,
		},
	}
}

type LinkedAlgorithm[T any] struct {
	size int
	head *linkedNode[T]
	tail *linkedNode[T]
}

func (a *LinkedAlgorithm[T]) Size() int {
	return a.size
}

func (a *LinkedAlgorithm[T]) Get(index int) T {
	return a.nodeAtIndex(index).item
}

func (a *LinkedAlgorithm[T]) Range(fromIndex, toIndex int) []T {
	slice := make([]T, toIndex-fromIndex)
	node := a.nodeAtIndex(fromIndex)
	slice[0] = node.item
	for index := fromIndex + 1; index < toIndex; index++ {
		node = node.next
		slice[index] = node.item
	}
	return slice
}

func (a *LinkedAlgorithm[T]) Set(index int, item T) {
	a.nodeAtIndex(index).item = item
}

func (a *LinkedAlgorithm[T]) AddAtIndex(index int, item T) {
	if index == a.size {
		prevTail := a.tail
		a.tail = &linkedNode[T]{
			item: item,
			prev: prevTail,
			next: nil,
		}
		a.size++
		if a.size > 1 {
			prevTail.next = a.tail
		} else {
			a.head = a.tail
		}
	} else {
		currentNode := a.nodeAtIndex(index)
		addedNode := &linkedNode[T]{
			item: item,
			prev: currentNode.prev,
			next: currentNode,
		}
		a.size++
		if currentNode.prev != nil {
			currentNode.prev.next = addedNode
		} else {
			a.head = addedNode
		}
		currentNode.prev = addedNode
	}
}

func (a *LinkedAlgorithm[T]) RemoveAtIndex(index int) T {
	var item T
	switch index {
	case 0:
		item = a.head.item
		a.head = a.head.next
		if a.size == 1 {
			a.tail = nil
		} else {
			a.head.prev = nil
		}
	case a.size - 1:
		item = a.tail.item
		a.tail = a.tail.prev
		a.tail.next = nil
	default:
		removedNode := a.nodeAtIndex(index)
		item = removedNode.item
		removedNode.prev.next = removedNode.next
		removedNode.next.prev = removedNode.prev
	}
	a.size--
	return item
}

func (a *LinkedAlgorithm[T]) nodeAtIndex(index int) *linkedNode[T] {
	var node *linkedNode[T]
	// loop through the nodes either from head or tail -- whichever requires fewer iterations
	if 2*index < a.size-1 {
		// looping from head is faster
		node = a.head
		for loopIndex := 0; loopIndex < index; loopIndex++ {
			node = node.next
		}
	} else {
		// looping from tail is faster
		node = a.tail
		for loopIndex := a.size - 1; loopIndex > index; loopIndex-- {
			node = node.prev
		}
	}
	return node
}

type linkedNode[T any] struct {
	item T
	prev *linkedNode[T]
	next *linkedNode[T]
}
