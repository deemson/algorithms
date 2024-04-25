package alg02deque

import "fmt"

func ensureIndexInBounds(index int, size int) {
	if index < 0 {
		panic(fmt.Sprintf("index cannot be negative (%d)", index))
	}
	if index >= size {
		panic(fmt.Sprintf("index (%d) must be less than size (%d)", index, size))
	}
}
