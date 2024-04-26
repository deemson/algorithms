package alg02deque

func ArrayBased[T any](capacity int) Deque[T] {
	return Deque[T]{
		algorithm: &ArrayAlgorithm[T]{
			array:          makeArray(capacity),
			firstItemIndex: 0,
			lastItemIndex:  0,
			size:           0,
		},
	}
}

func makeArray(capacity int) []any {
	array := make([]any, capacity)
	return array
}

type ArrayAlgorithm[T any] struct {
	array          []any
	firstItemIndex int
	lastItemIndex  int
	size           int
}

func (a *ArrayAlgorithm[T]) Size() int {
	return a.size
}

func (a *ArrayAlgorithm[T]) Get(index int) T {
	return a.array[a.normalizeIndex(index)].(T)
}

func (a *ArrayAlgorithm[T]) Set(index int, item T) {
	a.array[a.normalizeIndex(index)] = item
}

func (a *ArrayAlgorithm[T]) AddAtIndex(index int, item T) {
	a.growIfRequired()
	// move items out of the way either at the end or at the beginning -- whichever requires fewer items to move
	if a.size-index <= index {
		// fewer items to move at the end
		a.lastItemIndex++
		if a.lastItemIndex == a.capacity() {
			a.lastItemIndex = 0
		}
		for shiftIndex := a.size; shiftIndex > index; shiftIndex-- {
			a.Set(shiftIndex, a.Get(shiftIndex-1))
		}
	} else {
		// fewer items to move at the start
		a.firstItemIndex--
		if a.firstItemIndex < 0 {
			a.firstItemIndex = a.capacity() - 1
		}
		for shiftIndex := 0; shiftIndex < index; shiftIndex++ {
			a.Set(shiftIndex, a.Get(shiftIndex+1))
		}
	}
	a.Set(index, item)
	a.size++
}

func (a *ArrayAlgorithm[T]) RemoveAtIndex(index int) T {
	var item T
	// move item at index to first or last position -- whichever requires fewer swaps and remove it
	if a.size-1-index <= index {
		// fewer items to move at the end
		for swapIndex := index; swapIndex < a.size-1; swapIndex++ {
			swap[T](a, swapIndex, swapIndex+1)
		}
		item = a.Get(a.size - 1)
		a.array[a.normalizeIndex(a.size-1)] = nil
		a.lastItemIndex--
		if a.lastItemIndex < 0 {
			a.lastItemIndex = a.capacity() - 1
		}
	} else {
		// fewer items to move at the start
		for swapIndex := index; swapIndex > 0; swapIndex-- {
			swap[T](a, swapIndex, swapIndex-1)
		}
		item = a.Get(0)
		a.array[a.normalizeIndex(0)] = nil
		a.firstItemIndex++
		if a.firstItemIndex == a.capacity() {
			a.firstItemIndex = 0
		}
	}
	a.size--
	a.shrinkIfRequired()
	return item
}

// normalizeIndex makes sure that index that comes from the outer code which
// is going to be in range [0..size-1] to be in range [firstItemIndex..lastItemIndex-1]
// that ArrayAlgorithm understands
func (a *ArrayAlgorithm[T]) normalizeIndex(index int) int {
	return (a.firstItemIndex + index) % a.capacity()
}

func (a *ArrayAlgorithm[T]) capacity() int {
	return len(a.array)
}

// growIfRequired resizes the array to twice capacity when it's full.
func (a *ArrayAlgorithm[T]) growIfRequired() {
	if a.size == a.capacity() {
		a.resize(a.capacity() * 2)
	}
}

// shrinkIfRequired resizes the array to half the size when it's quarter full.
// The resize is done at quarter capacity to avoid "thrashing" (constant resizing)
// when working with half-full array and doing add-remove operations.
func (a *ArrayAlgorithm[T]) shrinkIfRequired() {
	if a.size > 0 && a.size == a.capacity()/4 {
		a.resize(a.capacity() / 2)
	}
}

// resize resizes the array to have len == capacity.
func (a *ArrayAlgorithm[T]) resize(capacity int) {
	array := makeArray(capacity)
	for index := 0; index < a.size; index++ {
		array[index] = a.Get(index)
	}
	a.array = array
	a.firstItemIndex = 0
	a.lastItemIndex = a.size
}
