package alg00compare

type EqualFunc[T any] func(item1, item2 T) bool

func AsEqual[T any](compare Func[T]) EqualFunc[T] {
	return func(item1, item2 T) bool {
		return compare(item1, item2) == Equal
	}
}

var (
	StringEqual = AsEqual(String)
	IntEqual    = AsEqual(Int)
)
