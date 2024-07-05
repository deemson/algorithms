package alg00compare

type Result int8

const (
	Less    Result = -1
	Equal   Result = 0
	Greater Result = 1
)

type Func[T any] func(item1, item2 T) Result

func (f Func[T]) Less(item1, item2 T) bool {
	return f(item1, item2) == Less
}

func (f Func[T]) Equal(item1, item2 T) bool {
	return f(item1, item2) == Equal
}

func Int(item1, item2 int) Result {
	switch {
	case item1 < item2:
		return Less
	case item1 > item2:
		return Greater
	default:
		return Equal
	}
}

func String(item1, item2 string) Result {
	switch {
	case item1 < item2:
		return Less
	case item1 > item2:
		return Greater
	default:
		return Equal
	}
}
