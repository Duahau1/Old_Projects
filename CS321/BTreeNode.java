import java.util.LinkedList;
/**
 * The node class used by BTree
 *
 * 
 */
public class BTreeNode
{
	private LinkedList<TreeObject> objects;
	private LinkedList<Integer> children;
    private int numOfObjects; //4 bytes
    private boolean isLeaf;// 1 bytes
    private int parent; 
    private int offset;

    public BTreeNode()
    {
        parent = -1; // the root node has no parent
        children = new LinkedList<Integer>();
        objects = new LinkedList<TreeObject>();
        numOfObjects = 0;
    }

   

    public void setOffset(int offset)
    {
        this.offset = offset;
    }

    public int getOffset()
    {
        return offset;
    }

    public void setNumOfObjects(int number)
    {
        numOfObjects = number;
    }

    public int getNumOfObjects()
    {
        return numOfObjects;
    }

    public void setParent(int parent)
    {
        this.parent = parent;
    }

    public int getParent()
    {
        return parent;
    }
    public void addChild(int child)
    {
        children.add(child);// the linked list method
    }

    public int removeChild(int index)
    {
        return children.remove(index);
    }

    public int getChild(int index)
    {
        return children.get(index).intValue();
    }

    public TreeObject removeKey(int index)
    {
        return objects.remove(index);
    }

    public void addKey(TreeObject obj)
    {
        objects.add(obj);
    }

    public TreeObject getObject(int key)
    {
        TreeObject element = objects.get(key);
        return element;
    }

    public void setIsLeaf(boolean isLeaf)
    {
        this.isLeaf = isLeaf;
    }

    public boolean isLeaf()
    {
        return isLeaf;
    }
    @Override
    public String toString()
    {
        String s = new String();
        s += "keys: ";
        for (int i = 0; i < objects.size(); i++)
        {
            s += (objects.get(i) + " ");
        }
        s += "\nchildren: ";
        for (int i = 0; i < children.size(); i++)
        {
            s += (children.get(i) + " ");
        }
        return s;
    }

    public LinkedList<Integer> getChildren()
    {
        return children;
    }
    public void addKey(TreeObject element, int index)
    {
        objects.add(index, element);
    }

    public void addChild(Integer child, int index)
    {
        children.add(index, child);
    }

    public LinkedList<TreeObject> getObjects()
    {
        return objects;
    }

   
}