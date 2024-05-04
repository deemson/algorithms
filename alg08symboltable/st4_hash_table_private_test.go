package alg08symboltable

import (
	"github.com/deemson/algorithms/alg00compare"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestHashTable_GrowShrink(t *testing.T) {
	hashTable := HashTable[int, int](HashInt, alg00compare.IntEqual, 2, 0.5)
	algorithm := hashTable.algorithm.(*HashTableAlgorithm[int, int])
	assert.Equal(t, float32(0), algorithm.load())
	assert.Equal(t, 2, len(algorithm.slice))
	hashTable.Set(1, 1)
	assert.Equal(t, float32(0.5), algorithm.load())
	assert.Equal(t, 2, len(algorithm.slice))
	hashTable.Set(2, 2)
	assert.Equal(t, float32(0.5), algorithm.load())
	assert.Equal(t, 4, len(algorithm.slice))
	hashTable.Set(3, 3)
	assert.Equal(t, float32(0.375), algorithm.load())
	assert.Equal(t, 8, len(algorithm.slice))
	assert.True(t, hashTable.Delete(3))
	assert.Equal(t, float32(0.25), algorithm.load())
	assert.Equal(t, 8, len(algorithm.slice))
	assert.True(t, hashTable.Delete(2))
	assert.Equal(t, float32(0.25), algorithm.load())
	assert.Equal(t, 4, len(algorithm.slice))
	assert.True(t, hashTable.Delete(1))
	assert.Equal(t, float32(0), algorithm.load())
	assert.Equal(t, 4, len(algorithm.slice))
}

func TestHashTable_Collisions(t *testing.T) {
	hashTable := HashTable[int, int](func(item int) uint32 {
		return 0
	}, alg00compare.IntEqual, 2, 0.5)
	algorithm := hashTable.algorithm.(*HashTableAlgorithm[int, int])
	hashTable.Set(1, 1)
	hashTable.Set(2, 2)
	hashTable.Set(3, 3)
	assert.Equal(t, []int{1, 2, 3}, hashTable.MustGetMany(hashTable.SortedKeysSlice(alg00compare.IntLess)...))
	assert.Equal(t, float32(0.5), algorithm.load())
	assert.Equal(t, 2, len(algorithm.slice))
	assert.True(t, hashTable.Delete(2))
	assert.Equal(t, []int{1, 3}, hashTable.MustGetMany(hashTable.SortedKeysSlice(alg00compare.IntLess)...))
}

func TestHashTable_EmptyBucketsInBetween(t *testing.T) {
	hashTable := HashTable[int, int](func(item int) uint32 {
		if item%2 == 0 {
			return 0
		} else {
			return 3
		}
	}, alg00compare.IntEqual, 4, 0.5)
	algorithm := hashTable.algorithm.(*HashTableAlgorithm[int, int])
	hashTable.Set(1, 1)
	hashTable.Set(2, 2)
	hashTable.Set(3, 3)
	hashTable.Set(4, 4)
	assert.Equal(t, []int{1, 2, 3, 4}, hashTable.MustGetMany(hashTable.SortedKeysSlice(alg00compare.IntLess)...))
	assert.Equal(t, float32(0.5), algorithm.load())
	assert.Equal(t, 4, len(algorithm.slice))
	assert.Equal(t, 2, algorithm.slice[0].Size())
	assert.Equal(t, 0, algorithm.slice[1].Size())
	assert.Equal(t, 0, algorithm.slice[2].Size())
	assert.Equal(t, 2, algorithm.slice[3].Size())
	hashTable.MustDeleteMany(2, 3)
	assert.Equal(t, []int{1, 4}, hashTable.MustGetMany(hashTable.SortedKeysSlice(alg00compare.IntLess)...))
	assert.Equal(t, 1, algorithm.slice[0].Size())
	assert.Equal(t, 0, algorithm.slice[1].Size())
	assert.Equal(t, 0, algorithm.slice[2].Size())
	assert.Equal(t, 1, algorithm.slice[3].Size())
}
