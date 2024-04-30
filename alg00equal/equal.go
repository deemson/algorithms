package alg00equal

type Equal[T any] func(item1, item2 T) bool

func Int(item1, item2 int) bool {
	return item1 == item2
}

func String(item1, item2 string) bool {
	return item1 == item2
}
