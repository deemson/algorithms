package alg00compare

type GreaterFunc[T any] func(item1, item2 T) bool

func AsGreater[T any](compare Func[T]) LessFunc[T] {
	return func(item1, item2 T) bool {
		return compare(item1, item2) == Greater
	}
}

var (
	StringGreater = AsGreater(String)
	IntGreater    = AsGreater(Int)
)
