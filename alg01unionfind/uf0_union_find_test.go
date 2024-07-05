package alg01unionfind_test

import (
	"github.com/deemson/algorithms/alg01unionfind"
	"github.com/stretchr/testify/assert"
	"github.com/stretchr/testify/require"
	"reflect"
	"testing"
)

const size = 10

func forEachAlgorithm(t *testing.T, f func(t *testing.T, unionFind alg01unionfind.UnionFind)) {
	for _, unionFind := range []alg01unionfind.UnionFind{
		alg01unionfind.NewQuickFind(size),
		alg01unionfind.NewQuickUnion(size),
		alg01unionfind.NewWeightedQuickUnion(size),
		alg01unionfind.NewWeightedCompressedQuickUnion(size),
	} {
		t.Run(reflect.TypeOf(unionFind).Elem().Name(), func(t *testing.T) {
			f(t, unionFind)
		})
	}
}

func TestUnionFind(t *testing.T) {
	forEachAlgorithm(t, func(t *testing.T, unionFind alg01unionfind.UnionFind) {
		for i := 1; i < size; i++ {
			require.False(t, unionFind.Connected(i-1, i))
		}
		unionFind.Union(3, 5)
		assert.True(t, unionFind.Connected(3, 5))
		unionFind.Union(6, 3)
		assert.True(t, unionFind.Connected(3, 6))
		assert.True(t, unionFind.Connected(5, 6))
		unionFind.Union(7, 8)
		assert.True(t, unionFind.Connected(7, 8))
		assert.False(t, unionFind.Connected(3, 8))
	})
}
