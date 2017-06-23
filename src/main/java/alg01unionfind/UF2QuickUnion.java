package alg01unionfind;


/**
 * <p>
 * Improvement upon quickunion.
 * Union is implemented by setting parent-child nodes
 * thus creating some sort of a tree for complex unions.
 * </p>
 * Defects:
 * <ul>
 * <li>Trees can get tall</li>
 * <li>Finds are too expensive (could be up to N in a worst case)</li>
 * </ul>
 */
public class UF2QuickUnion implements UnionFindAlgorithm {

    protected int[] parentOf;

    public UF2QuickUnion(int size) {
        parentOf = new int[size];
        for (int i = 0; i < size; i++) {
            parentOf[i] = i;
        }
    }

    /**
     * This is the method for finding union tree root.
     * The roots are the nodes, that were never been set as some other's node child
     * thus having the parent it had during the initialization, i.e. i == parentOf[i]
     * being a parent to itself.
     */
    protected int root(int i) {
        while (i != parentOf[i]) {
            // We cycle thought the node's parents until we find a node that is parent
            // to itself
            i = parentOf[i];
        }
        return i;
    }

    /**
     * To union two nodes we set their respective tree roots in as parent-child
     * (who is who does not matter) creating bigger tree in a process
     * This implementation sets N1 as a parent of N2
     */
    @Override
    public void union(int n1, int n2) {
        int rootN1 = root(n1);
        int rootN2 = root(n2);
        parentOf[rootN2] = rootN1;
    }

    /**
     * Here we must find out if the roots of union trees, n1 and n2 are in, are the same
     * If they are -- n1 and n2 are connected and vice versa
     * This could be as slow as O(N) in a worst case
     */
    @Override
    public boolean connected(int n1, int n2) {
        return root(n1) == root(n2);
    }
}
