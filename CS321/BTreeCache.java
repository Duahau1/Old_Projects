import java.util.Iterator;
import java.util.LinkedList;

/**
 * The cache class is implemented for the sake of the Cache
 *
 * 
 */
public class BTreeCache implements Iterable<BTreeNode>
{
	 private LinkedList<BTreeNode> cacheList;
	private int numHits, numMisses;
    private final int MAX_CACHE_SIZE;
   private int size;
   

    public BTreeCache(int MAX_SIZE)
    {
        
    	this.MAX_CACHE_SIZE = MAX_SIZE;
        cacheList = new LinkedList<BTreeNode>();
        this.size=0;
    }

   

    public void clearCache()
    {
        cacheList.clear();
    }

   
    public BTreeNode readNode(int nodeOffset)
    {
        for (BTreeNode n : cacheList)
        {
            if (n.getOffset() == nodeOffset)
            {
                cacheList.remove(n); // the remove method of linked list
                cacheList.addFirst(n); // the add method of linked list
                increaseNumHits();
                return n;
            }
        }
        increaseNumMisses();
        return null;
    }

    public BTreeNode add(BTreeNode nodeToAdd, int offset)
    {
        BTreeNode nodeToReturn = null;
        if (isFull()) {
            nodeToReturn = cacheList.removeLast();
            
        }
       size++;
        cacheList.addFirst(nodeToAdd);
        return nodeToReturn;
    }
   
    public int getNumHitsMiss()
    {
        return numHits + numMisses;
    }

    private void increaseNumHits()
    {
        numHits++;
    }

    private void increaseNumMisses()
    {
        numMisses++;
    }

    public int getNumHits()
    {
        return numHits;
    }

    public int getNumMisses()
    {
        return numMisses;
    }

    public double getHitRatio()
    {
        double ratio = ((double) getNumHits()) / getNumHitsMiss();
        return ratio;
    }

    public int getSize()
    {
        return cacheList.size();
    }

    public boolean isFull()
    {
        return (size == MAX_CACHE_SIZE);
    }

    //The iterator constructor
    @Override
    public Iterator<BTreeNode> iterator()
    {
        return cacheList.iterator();
    }
}       