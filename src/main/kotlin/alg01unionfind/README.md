# Union Find #

First data-structure explained in the course.

[UnionFind](UnionFind.kt) is an interface for the data-structure.

[UF1QuickFind](UF1QuickFind.kt) is a simple straightforward implementation
with a quick find but slow union.

[UF2QuickUnion](UF2QuickUnion.kt) is an implementation that tries to 
fix the drawbacks of the first implementation. Tries to make union
faster by grouping unions into subtrees. A major drawback: if all nodes
are connected into a single union performance degrades into `O(n)`
because tree grows too big.

[UF3WeightedQuickUnion](UF3WeightedQuickUnion.kt) extends the previous
implementation to address trees grows. When does a connect operation, a shorter
tree is set as a child to a taller tree to reduce growth.

[UF4WeightedQuickUnionWithPathCompression](UF4WeightedQuickUnionWithPathCompression.kt)
adds path compression to previous implementation to further increase the
speed of union operation.