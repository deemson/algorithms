package alg01unionfind;

/**
 * <p>
 * Further improvement of union find. We compress paths while searching for roots of nodes.
 * <i>"Compressing"</i> means that we set all childs in a tree to tree's root.
 * </p>
 */
public class UF4WeightedQuickUnionWithPathCompression extends UF3WeightedQuickUnion implements UnionFindAlgorithm {

    public UF4WeightedQuickUnionWithPathCompression(int size) {
        super(size);
    }

    @Override
    protected int root(int i) {
        while (i != parentOf[i]) {
            // We bubble up node up to it's root to compress paths
            parentOf[i] = parentOf[parentOf[i]];
            // We cycle thought the node's parents until we find a node that is parent
            // to itself
            i = parentOf[i];
        }
        return i;
    }
}
