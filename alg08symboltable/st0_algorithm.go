package alg08symboltable

type Algorithm[K, V any] interface {
	Get(key K) (V, bool)
	Set(key K, value V)
	Delete(key K) bool
}
