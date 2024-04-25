package alg02deques

import "fmt"

func makeArray(capacity int) []any {
	array := make([]any, capacity)
	return array
}

func ArrayBased[T any](capacity int) *ArrayDeque[T] {
	return &ArrayDeque[T]{
		array:          makeArray(capacity),
		firstItemIndex: 0,
		lastItemIndex:  0,
		size:           0,
	}
}

// check interface compliance
var _ Deque[any] = &ArrayDeque[any]{}

type ArrayDeque[T any] struct {
	array          []any
	firstItemIndex int
	lastItemIndex  int
	size           int
}

func (d *ArrayDeque[T]) ToSlice() []T {
	return toSlice[T](d)
}

func (d *ArrayDeque[T]) Size() int {
	return d.size
}

func (d *ArrayDeque[T]) IsEmpty() bool {
	return isEmpty[T](d)
}

func (d *ArrayDeque[T]) Get(index int) T {
	return d.array[d.normalizeIndex(index)].(T)
}

// normalizeIndex makes sure that index that comes from the outer code which
// is going to be in range [0..size-1] to be in range [firstItemIndex..lastItemIndex-1]
// that ArrayDeque understands
func (d *ArrayDeque[T]) normalizeIndex(index int) int {
	d.ensureIndexInBounds(index)
	return (d.firstItemIndex + index) % d.capacity()
}

// ensureIndexInBounds method panics if index is out of bounds for ArrayDeque size
func (d *ArrayDeque[T]) ensureIndexInBounds(index int) {
	if index < 0 || index >= d.size {
		panic(fmt.Sprintf(
			`want index in range [0..%d]; have index %d`,
			d.size-1,
			index,
		))
	}
}

func (d *ArrayDeque[T]) AddFirst(item T) {
	d.growIfRequired()
	d.firstItemIndex--
	if d.firstItemIndex < 0 {
		d.firstItemIndex = d.capacity() - 1
	}
	d.array[d.firstItemIndex] = item
	d.size++
}

func (d *ArrayDeque[T]) AddLast(item T) {
	d.growIfRequired()
	d.array[d.lastItemIndex] = item
	d.lastItemIndex++
	if d.lastItemIndex == d.capacity() {
		d.lastItemIndex = 0
	}
	d.size++
}

func (d *ArrayDeque[T]) RemoveFirst() T {
	item := d.Get(0)
	d.firstItemIndex++
	if d.firstItemIndex == d.capacity() {
		d.firstItemIndex = 0
	}
	d.size--
	d.shrinkIfRequired()
	return item
}

func (d *ArrayDeque[T]) RemoveLast() T {
	d.ensureNotEmpty()
	item := d.Get(d.size - 1)
	d.lastItemIndex--
	if d.lastItemIndex < 0 {
		d.lastItemIndex = d.capacity() - 1
	}
	d.size--
	d.shrinkIfRequired()
	return item
}

func (d *ArrayDeque[T]) growIfRequired() {
	if d.size == d.capacity() {
		d.resize(d.capacity() * 2)
	}
}

func (d *ArrayDeque[T]) ensureNotEmpty() {
	if d.size == 0 {
		panic("ArrayDeque is empty")
	}
}

// shrinkIfRequired resizes the array to half the size when it's quarter full.
// The resize is done at quarter capacity to avoid "thrashing" (constant resizing)
// when working with half-full array and doing add-remove operations.
func (d *ArrayDeque[T]) shrinkIfRequired() {
	if d.size > 0 && d.size == d.capacity()/4 {
		d.resize(d.capacity() / 2)
	}
}

func (d *ArrayDeque[T]) resize(capacity int) {
	array := makeArray(capacity)
	for index := 0; index < d.size; index++ {
		array[index] = d.Get(index)
	}
	d.array = array
	d.firstItemIndex = 0
	d.lastItemIndex = d.size
}

func (d *ArrayDeque[T]) capacity() int {
	return len(d.array)
}
