/**
 * The cache uses this node class
 *
 * 
 */

public class BTreeCacheNode
{
    private BTreeNode node;
    private int offset;

    public BTreeCacheNode(BTreeNode node, int offset)
    {
        this.node = node;
        this.offset = offset;
    }

    
    public BTreeNode getData() /// get data of the node from the file
    {
        return node;
    }

    public int getOffset()
    {
        return offset;
    }
}