package alg00compare

type LessFunc[T any] func(item1, item2 T) bool

func AsLess[T any](compare Func[T]) LessFunc[T] {
	return func(item1, item2 T) bool {
		return compare(item1, item2) == Less
	}
}

var (
	StringLess = AsLess(String)
	IntLess    = AsLess(Int)
)
