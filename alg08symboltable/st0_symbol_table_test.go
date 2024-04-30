package alg08symboltable_test

import (
	"github.com/deemson/algorithms/alg00equal"
	"github.com/deemson/algorithms/alg00less"
	"github.com/deemson/algorithms/alg08symboltable"
	"github.com/stretchr/testify/assert"
	"testing"
)

type keyCompares[T any] struct {
	equal alg00equal.Equal[T]
	less  alg00less.Less[T]
}

func forEachAlgorithm[K, V any](t *testing.T, cmp keyCompares[K], f func(t *testing.T, symbolTable alg08symboltable.SymbolTable[K, V])) {
	symbolTables := map[string]alg08symboltable.SymbolTable[K, V]{
		"UnorderedLinked": alg08symboltable.UnorderedLinked[K, V](cmp.equal),
		"RankedArray":     alg08symboltable.RankedArray[K, V](cmp.equal, cmp.less, 2),
	}
	for name, symbolTable := range symbolTables {
		t.Run(name, func(t *testing.T) {
			f(t, symbolTable)
		})
	}
}

func TestSymbolTable(t *testing.T) {
	forEachAlgorithm[string, int](t, keyCompares[string]{
		equal: alg00equal.String,
		less:  alg00less.String,
	}, func(t *testing.T, symbolTable alg08symboltable.SymbolTable[string, int]) {
		symbolTable.Set("one", 1)
		symbolTable.Set("two", 2)
		symbolTable.Set("three", 3)
		assert.Equal(t, []string{"one", "three", "two"}, symbolTable.SortedKeysSlice(alg00less.String))
		assert.Equal(t, []int{1, 3, 2}, symbolTable.MustGetMany(symbolTable.SortedKeysSlice(alg00less.String)...))
		symbolTable.Set("two", 4)
		assert.Equal(t, []int{1, 3, 4}, symbolTable.MustGetMany(symbolTable.SortedKeysSlice(alg00less.String)...))
		symbolTable.Delete("two")
		assert.Equal(t, []int{1, 3}, symbolTable.MustGetMany(symbolTable.SortedKeysSlice(alg00less.String)...))
		symbolTable.MustDeleteMany("one", "three")
		assert.True(t, symbolTable.IsEmpty())
	})
}

func TestSymbolTable_GetNonExistent(t *testing.T) {
	forEachAlgorithm[string, int](t, keyCompares[string]{
		equal: alg00equal.String,
		less:  alg00less.String,
	}, func(t *testing.T, symbolTable alg08symboltable.SymbolTable[string, int]) {
		defer func(t *testing.T) {
			r := recover()
			assert.Equal(t, `symbol table does not contain key '"non-existent"'`, r)
		}(t)
		symbolTable.MustGet("non-existent")
	})
}

func TestSymbolTable_DeleteNonExistent(t *testing.T) {
	forEachAlgorithm[string, int](t, keyCompares[string]{
		equal: alg00equal.String,
		less:  alg00less.String,
	}, func(t *testing.T, symbolTable alg08symboltable.SymbolTable[string, int]) {
		defer func(t *testing.T) {
			r := recover()
			assert.Equal(t, `symbol table does not contain key '"non-existent"'`, r)
		}(t)
		symbolTable.MustDelete("non-existent")
	})
}
