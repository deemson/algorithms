package alg01unionfind

type UnionFind interface {
	Union(int, int)
	Connected(int, int) bool
}
