package alg08symboltable

type HashFunc[T any] func(item T) uint32

func HashString(value string) uint32 {
	hash := int32(0)
	for _, character := range value {
		hash = 31*hash + character
	}
	return uint32(hash)
}

func HashInt(value int) uint32 {
	return uint32(value)
}

type HashTable[K, V any] struct {
}
