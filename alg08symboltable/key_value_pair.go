package alg08symboltable

type keyValuePair[K, V any] struct {
	key   K
	value V
}

func unpackKey[K, V any](pair keyValuePair[K, V]) K {
	return pair.key
}
