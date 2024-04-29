package alg00less

type Less[T any] func(item1, item2 T) bool

func Reversed[T any](less Less[T]) Less[T] {
	return func(item1, item2 T) bool {
		return less(item2, item1)
	}
}

func Int(item1, item2 int) bool {
	return item1 < item2
}

func String(item1, item2 string) bool {
	return item1 < item2
}
