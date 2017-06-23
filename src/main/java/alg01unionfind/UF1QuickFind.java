package alg01unionfind;


/**
 * <p>
 * Defects:
 * <ul>
 * <li>Union is expensive</li>
 * </ul>
 * </p>
 */
public class UF1QuickFind implements UnionFindAlgorithm {

    private int[] id;

    public UF1QuickFind(int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    /**
     * This is fast as O(1), hence "QuickFind" name
     */
    public boolean connected(int n1, int n2) {
        return id[n1] == id[n2];
    }

    /**
     * But this is slow as O(N)
     */
    public void union(int n1, int n2) {
        int n1id = id[n1];
        int n2id = id[n2];
        // We change every occurrence of n1id to n2id
        // And this is bad, because it grows as O(N)
        // This will be ultra slow as O(N^2) if we will have
        // N UnionFinds
        for (int i = 0; i < id.length; i++) {
            if (id[i] == n1id) {
                id[i] = n2id;
            }
        }
    }

}
