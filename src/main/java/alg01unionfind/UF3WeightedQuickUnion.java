package alg01unionfind;

/**
 * <p>
 * To lessen the QuickUnion defect we should shorten the function <b>root</b> calls.
 * To do that we should avoid creating tall union trees, effectively reducing
 * the number of <b>while</b> loops.
 * </p>
 */
public class UF3WeightedQuickUnion extends UF2QuickUnion implements UnionFindAlgorithm {

    /**
     * To avoid creating tall trees we must never set a tall tree as a child of
     * a small tree growing the former even more. So we create treeSize array to keep
     * track of tree sizes
     */
    protected int[] treeSizeOf;

    public UF3WeightedQuickUnion(int size) {
        super(size);
        treeSizeOf = new int[size];
        for (int i = 0; i < size; i++) {
            // Initially all trees contain only one node
            treeSizeOf[i] = 1;
        }
    }

    /**
     * Now union and find will take at most O(log(N)) operations to complete,
     * as we reduced the size of trees.
     */
    @Override
    public void union(int n1, int n2) {
        int rootN1 = root(n1);
        int rootN2 = root(n2);
        // We put a smaller tree as a child to a taller tree
        if (treeSizeOf[rootN1] < treeSizeOf[rootN2]) {
            parentOf[rootN1] = rootN2;
            treeSizeOf[rootN2] += rootN1;
        } else {
            parentOf[rootN2] = rootN1;
            treeSizeOf[rootN1] += rootN2;
        }
    }

}
