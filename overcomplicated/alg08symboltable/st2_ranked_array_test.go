package alg08symboltable_test

import (
	"github.com/deemson/algorithms/alg00compare"
	"github.com/deemson/algorithms/alg08symboltable"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestRankedArray_GetDelete_NonEmptyTable_NonExistentKey(t *testing.T) {
	symbolTable := alg08symboltable.RankedArray[int, int](alg00compare.Int, 2)
	symbolTable.Set(42, 42)
	_, ok := symbolTable.Get(13)
	assert.False(t, ok)
	ok = symbolTable.Delete(13)
	assert.False(t, ok)
}
