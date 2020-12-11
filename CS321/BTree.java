import java.io.*;

/**
 * To write B-Tree
 *
 * 
 */
public class BTree
{

	private int degree;
	private BTreeNode root;
	private RandomAccessFile file;
	private File inputFile;
	private int placeToInsert;
	private int rootOffset;
	private int bTreeNodeSize;
	private BTreeCache cache;

	public BTree(int degree, String fileName, boolean useCache, int cacheSize)
	{
		bTreeNodeSize = 32 * degree - 3; 
		rootOffset = 12;
		placeToInsert = rootOffset + bTreeNodeSize;

		this.degree = degree;
		if (useCache)
			cache = new BTreeCache(cacheSize);

		BTreeNode x = new BTreeNode();
		root = x;
		root.setOffset(rootOffset);
		x.setIsLeaf(true);
		x.setNumOfObjects(0);
		try
		{
			inputFile = new File(fileName);
			inputFile.delete();
			inputFile.createNewFile();
			file = new RandomAccessFile(inputFile, "rw");
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Error: File is corrupt or missing.");
			System.exit(-1);
		}
		catch (IOException io)
		{

			System.err.println("Error: IO Exception occurred.");
			System.exit(-1);
		}

		writeMetadata();
	}
	//for the search class

	public BTree(int degree, File fileName, boolean useCache, int cacheSize)
	{

		try
		{
			file = new RandomAccessFile(fileName, "rw");
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Error:missing File.");
			System.exit(-1);
		}

		readMetadata();
		root = readNode(rootOffset);
	}

	public void bTreeInsert(long k)
	{
		BTreeNode r = this.root;
		int i = r.getNumOfObjects();
		if (i == (2 * degree - 1))
		{
			TreeObject obj = new TreeObject(k);
			while (i > 0 && obj.compareTo(r.getObject(i - 1)) < 0)
			{
				i--;
			}
			if (i < r.getNumOfObjects())
			{
			}
			if (i > 0 && obj.compareTo(r.getObject(i - 1)) == 0)
				r.getObject(i - 1).increaseFrequency(); //increase the frequency 
			else
			{
				BTreeNode s = new BTreeNode();
				s.setOffset(r.getOffset());
				this.root = s;
				r.setOffset(placeToInsert);
				r.setParent(s.getOffset());
				s.setIsLeaf(false);
				s.addChild(r.getOffset());
				splitChild(s, 0, r);
				insertNonfull(s, k);
			}
		}
		else
			insertNonfull(r, k);
	}

	public void insertNonfull(BTreeNode x, long k)
	{
		int i = x.getNumOfObjects();
		TreeObject treeObject = new TreeObject(k);
		if (x.isLeaf())
		{
			if (x.getNumOfObjects() != 0)
			{
				while (i > 0 && treeObject.compareTo(x.getObject(i - 1)) < 0)
				{
					i--;
				}
			}
			if (i > 0 && treeObject.compareTo(x.getObject(i - 1)) == 0)
			{
				x.getObject(i - 1).increaseFrequency();
			}
			else
			{
				x.addKey(treeObject, i);
				x.setNumOfObjects(x.getNumOfObjects() + 1);
			}
			writeNode(x, x.getOffset());
		}
		else
		{
			while (i > 0 && (treeObject.compareTo(x.getObject(i - 1)) < 0))
			{
				i--;
			}
			if (i > 0 && treeObject.compareTo(x.getObject(i - 1)) == 0)
			{
				x.getObject(i - 1).increaseFrequency();
				writeNode(x, x.getOffset());
				return;
			}
			int offset = x.getChild(i);
			BTreeNode y = readNode(offset);
			if (y.getNumOfObjects() == 2 * degree - 1)
			{
				int j = y.getNumOfObjects();
				while (j > 0 && treeObject.compareTo(y.getObject(j - 1)) < 0)
				{
					j--;
				}
				if (j > 0 && treeObject.compareTo(y.getObject(j - 1)) == 0)
				{
					y.getObject(j - 1).increaseFrequency();
					writeNode(y, y.getOffset());
					return;
				}
				else
				{
					splitChild(x, i, y);
					if (treeObject.compareTo(x.getObject(i)) > 0)
						i++;
				}
			}
			offset = x.getChild(i);
			BTreeNode child = readNode(offset);
			insertNonfull(child, k);
		}
	}

	public void splitChild(BTreeNode x, int i, BTreeNode y)
	{
		BTreeNode z = new BTreeNode();
		z.setIsLeaf(y.isLeaf());
		z.setParent(y.getParent());
		for (int j = 0; j < degree - 1; j++)
		{
			z.addKey(y.removeKey(degree));
			z.setNumOfObjects(z.getNumOfObjects() + 1);
			y.setNumOfObjects(y.getNumOfObjects() - 1);
		}

		if (!y.isLeaf())
		{
			for (int j = 0; j < degree; j++)
			{
				z.addChild(y.removeChild(degree));
			}
		}

		x.addKey(y.removeKey(degree - 1), i);
		x.setNumOfObjects(x.getNumOfObjects() + 1);
		y.setNumOfObjects(y.getNumOfObjects() - 1);

		if (x == root && x.getNumOfObjects() == 1)
		{
			writeNode(y, placeToInsert);
			placeToInsert += bTreeNodeSize;
			z.setOffset(placeToInsert);
			x.addChild(z.getOffset(), i + 1);
			writeNode(z, placeToInsert);
			writeNode(x, rootOffset);
			placeToInsert += bTreeNodeSize;
		}
		else
		{
			writeNode(y, y.getOffset());
			z.setOffset(placeToInsert);
			writeNode(z, placeToInsert);
			x.addChild(z.getOffset(), i + 1);
			writeNode(x, x.getOffset());
			placeToInsert += bTreeNodeSize;
		}
	}

	public TreeObject search(BTreeNode x, long k)
	{
		int i = 0;
		TreeObject obj = new TreeObject(k);
		while (i < x.getNumOfObjects() && (obj.compareTo(x.getObject(i)) > 0))
		{
			i++;
		}
		if (i < x.getNumOfObjects() && obj.compareTo(x.getObject(i)) == 0)
		{
			return x.getObject(i);
		}
		if (x.isLeaf())
		{
			return null;
		}
		else
		{
			int offset = x.getChild(i);
			BTreeNode y = readNode(offset);
			return search(y, k);
		}
	}

	public BTreeNode searchForDel(BTreeNode x, long k)
	{
		int i = 0;
		TreeObject obj = new TreeObject(k);
		while (i < x.getNumOfObjects() && (obj.compareTo(x.getObject(i)) > 0))
		{
			i++;
		}
		if (i < x.getNumOfObjects() && obj.compareTo(x.getObject(i)) == 0)
		{
			return x;
		}
		if (x.isLeaf())
		{
			return null;
		}
		else
		{
			int offset = x.getChild(i);
			BTreeNode y = readNode(offset);
			return searchForDel(y, k);
		}
	}

	public BTreeNode getRoot()
	{
		return root;
	}

	public void inOrderPrint(BTreeNode n)
	{
		System.out.println(n);
		if (n.isLeaf() == true)
		{
			for (int i = 0; i < n.getNumOfObjects(); i++)
			{
				System.out.println(n.getObject(i));

			}
			return;
		}
		for (int i = 0; i < n.getNumOfObjects() + 1; ++i)
		{
			int offset = n.getChild(i);
			BTreeNode y = readNode(offset);
			inOrderPrint(y);
			if (i < n.getNumOfObjects())
				System.out.println(n.getObject(i));

		}
	}

	public void inOrderPrintToWriter(BTreeNode node, FileWriter fw, int sequenceLength) throws IOException
	{
		BTreeConvert data = new BTreeConvert();

		if (node.isLeaf())
		{
			for (int i = 0; i < node.getNumOfObjects(); i++)
			{
				fw.write(data.convertLongToString(node.getObject(i).getData(), sequenceLength)+" : ");
				fw.write(node.getObject(i).getFrequency() + "\n");

			}
			return;
		}

		for (int i = 0; i < node.getNumOfObjects() + 1; ++i)
		{
			int offset = node.getChild(i);
			BTreeNode y = readNode(offset);
			inOrderPrintToWriter(y, fw, sequenceLength);

			if (i < node.getNumOfObjects())
			{
				fw.write(data.convertLongToString(node.getObject(i).getData(), sequenceLength)+" : ");
				fw.write(node.getObject(i).getFrequency() + "\n");

			}
		}
	}

	public void writeNode(BTreeNode n, int offset)
	{
		if (cache != null)
		{
			BTreeNode cnode = cache.add(n, offset);
			// if a node was pushed off, write it
			if (cnode != null) writeNodeToFile(cnode, cnode.getOffset());
		}
		else
		{
			writeNodeToFile(n, offset);
		}
	}

	private void writeNodeToFile(BTreeNode n, int offset)
	{
		int i = 0;
		try
		{
			writeNodeMetadata(n, n.getOffset());
			file.writeInt(n.getParent());
			for (i = 0; i < 2 * degree - 1; i++)
			{
				if (i < n.getNumOfObjects() + 1 && !n.isLeaf())
				{
					file.writeInt(n.getChild(i));
				}
				else if (i >= n.getNumOfObjects() + 1 || n.isLeaf())
				{
					file.writeInt(0);
				}
				if (i < n.getNumOfObjects())
				{
					long data = n.getObject(i).getData();
					file.writeLong(data);
					int frequency = n.getObject(i).getFrequency();
					file.writeInt(frequency);
				}
				else if (i >= n.getNumOfObjects() || n.isLeaf())
				{
					file.writeLong(0);
				}
			}
			if (i == n.getNumOfObjects() && !n.isLeaf())
			{
				file.writeInt(n.getChild(i));
			}
		}
		catch (IOException ioe)
		{
			System.err.println("Error: IO Exception occurred!");
			System.exit(-1);
		}
	}

	public BTreeNode readNode(int offset)
	{

		BTreeNode y = null;

		// if node is cached, we can just read it from there
		if (cache != null) {
			y = cache.readNode(offset);}
		if (y != null) { 
			return y;}

		//Read all the information related to the BTree
		y = new BTreeNode();
		TreeObject obj = null;
		y.setOffset(offset);
		int k = 0;
		try
		{
			file.seek(offset);
			boolean isLeaf = file.readBoolean();
			y.setIsLeaf(isLeaf);
			int n = file.readInt();
			y.setNumOfObjects(n);
			int parent = file.readInt();
			y.setParent(parent);
			for (k = 0; k < 2 * degree - 1; k++)
			{
				if (k < y.getNumOfObjects() + 1 && !y.isLeaf())
				{
					int child = file.readInt();
					y.addChild(child);
				}
				else if (k >= y.getNumOfObjects() + 1 || y.isLeaf())
				{
					file.seek(file.getFilePointer() + 4);
				}
				if (k < y.getNumOfObjects())
				{
					long value = file.readLong();
					int frequency = file.readInt();
					obj = new TreeObject(value, frequency);
					y.addKey(obj);
				}
			}
			if (k == y.getNumOfObjects() && !y.isLeaf())
			{
				int child = file.readInt();
				y.addChild(child);
			}
		}
		catch (IOException io)
		{
			System.err.println(io.getMessage());
			System.exit(-1);
		}

		return y;
	}

	public void writeMetadata()
	{
		try
		{
			file.seek(0);
			file.writeInt(degree);
			file.writeInt(32 * degree - 3);
			file.writeInt(12);
		}
		catch (IOException io)
		{
			System.err.println("Error: IO Exception occurred.");
			System.exit(-1);
		}
	}

	public void readMetadata()
	{
		try
		{
			file.seek(0);
			degree = file.readInt();
			bTreeNodeSize = file.readInt();
			rootOffset = file.readInt();
		}
		catch (IOException io)
		{
			System.err.println("Error: IO Exception occurred.");
			System.exit(-1);
		}
	}

	public void writeNodeMetadata(BTreeNode x, int offset)
	{
		try
		{
			// isLeaf | numOfObjects
			file.seek(offset);
			file.writeBoolean(x.isLeaf());
			file.writeInt(x.getNumOfObjects());
		}
		catch (IOException io)
		{
			System.err.println("Error: IO Exception occurred.");
			System.exit(-1);
		}
	}

	public void flushCache()
	{
		if (cache != null)
		{
			for (BTreeNode cacheNode : cache)
				writeNodeToFile(cacheNode, cacheNode.getOffset());
		}
	}
	/**
	 * The delete method
	 */
	public void delete(BTreeNode x,TreeObject obj) {
		TreeObject found = null;
		found=search(x,obj.getData());

	}

	public static void main (String args[]) {
	
		int set_zero=0;
		for ( int ctr = 0; ctr < 100; ctr++)
		{ 
			set_zero = set_zero(); 
		System.out.println("hello");
		}
		
		
if(false) {
	System.out.println("bye");}

}

private static int set_zero() {return 110;} 
}