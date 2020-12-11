import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
/*
 * The BTree Class
 */
public class BTree {
	public BTreeNode root;
	public int degree;
	public static RandomAccessFile file;
	private int location;
	String fileName;
	
	/**
	 * Constructor to create a BTree file 
	 * @param degree for BTree 
	 * @param BTee binary file 
	 * @throws IOException
	 */
	public BTree(int degree, String fileName) throws IOException
	{
		File inputfiles = null;
		inputfiles = new File(fileName);
		if(inputfiles.exists()) {
			inputfiles.delete();
		}
		inputfiles.createNewFile();
		
		this.fileName=fileName;
		
		//using random access file to read and write binary file.
		this.file = new RandomAccessFile(inputfiles, "rw");
		writeMetaData();
		this.degree = degree;	
		this.root = allocateNode();	

	}
	/**
	 * Constructor for BTree search class.
	 * @param fileName
	 * @throws IOException
	 */
	public BTree(String fileName) throws IOException
	{
		File btreefile = new File(fileName);
		this.file= new RandomAccessFile(btreefile, "rw");
		readMetaData();
		this.root = diskRead(location);
	}

	
	/**
	 * Allocate the node 
	 * @return BTreeNode
	 */
	public BTreeNode allocateNode() throws IOException
	{
		BTreeNode newNode = new BTreeNode(degree);
		newNode.setPosition((int)file.length());
		diskWrite(newNode);
		return newNode;
	}

	/**
	 * Closing the b tree. Need to write the root information again.
	 * @throws IOException
	 */
	public void bTreeClose() throws IOException 
	{
		diskWrite(this.root);
		writeMetaData();
		file.close();
	}

	/**
	 * Writing the meta data - starts from 0 
	 * @throws IOException
	 */
	public void writeMetaData() throws IOException
	{
		file.seek(0);
		file.writeInt(degree);
		file.writeInt(location);
	}
	
	/**
	 * Reading the meta data - starts from 0
	 * @throws IOException
	 */
	public void readMetaData() throws IOException
	{
		file.seek(0);
		degree = file.readInt();
		location = file.readInt();
	}
	/**
	 * To print out all the necessary meta data information related to the root
	 * This is for debugging only
	 */
	public static void printMetaData() throws IOException {
		file.seek(0);
		System.out.println("Location: " + file.readInt());
		System.out.println("Degree: " + file.readInt());
	}
	
	/**
	 * Writing to the disk(or the BTree file)
	 * @param node
	 * @throws IOException
	 */
	public void diskWrite(BTreeNode node) throws IOException
	{
		// isLeaf | NumofObjects | location of child pointers
		//So when we read the position, we just need to readBoolean and readInt 
		file.seek(node.getPosition()); 
		file.writeBoolean(node.isLeaf);
		file.writeInt(node.getNumOfObjects());
		//Keys| Frequency
		for(int i = 0; i < node.objects.length; i++)
		{
			TreeObject obj = node.objects[i];
			file.writeLong(obj.getKey());
			file.writeInt(obj.getFrequency());
		}
		// Pointers to all the child(in case this node has children)
		for(int j = 0; j < node.pointers.length; j++) 
		{
			file.writeInt(node.pointers[j]);
		}

	}
	/**
	 * Reading from the disk(of the BTree file) and get all the information related to the node
	 * @param location
	 * @return the BTree Node that we want at a specific position
	 * @throws IOException
	 */
	public BTreeNode diskRead(int location) throws IOException 
	{
		BTreeNode node = null;
		
		node = new BTreeNode(degree);
		
		node.setPosition(location);
		
		file.seek(node.getPosition());

		node.isLeaf = file.readBoolean();
		
		node.setNumOfObjects(file.readInt());
		
		for(int i = 0; i < node.objects.length; i++) 
		{
			node.objects[i].setKey(file.readLong());
			node.objects[i].setFrequency(file.readInt());
		}
		
		for(int j = 0; j < node.pointers.length; j++)
		{
			node.pointers[j] = file.readInt();
		}
		return node;
	}
	
	/**
	 * Writing the node meta data
	 * @param node
	 * @param location
	 * @throws IOException
	 */
	public void writeNodeMetaData(BTreeNode node, int location) throws IOException
	{
		file.seek(location);
		file.writeBoolean(node.isLeaf);
		file.writeInt(node.getNumOfObjects());
	}
	

	// ALL the important methods are down here
	/**
	 * 
	 * This function searches a key value in the b tree.(without changing any data)
	 * @param node - starting point
	 * @param object - searching for
	 * @return TreeObject - searched 
	 */
	public TreeObject bTreeSearch(BTreeNode node, TreeObject object) 
	{
		int i = 0;
		BTreeNode childNode = null;
		while( i < node.getNumOfObjects() && object.getKey() > node.objects[i].getKey()) 
		{
			i = i + 1;
		}
		
		if( i < node.getNumOfObjects() && object.getKey() == node.objects[i].getKey())
		{
			return node.objects[i];
		}
		else if(node.isLeaf) 
		{
			return null;
		}
		else
		{
			try {
				childNode = diskRead(node.pointers[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return bTreeSearch(childNode, object);
		}
	}
	
	/**
	 * Creating an empty B-tree
	 * @param T - BTree
	 */
	public void bTreeCreate() throws IOException
	{
		BTreeNode newNode = allocateNode();
		newNode.isLeaf = true;
		newNode.setNumOfObjects(0);
		diskWrite(newNode);
		this.root = newNode;
	}
	/**
	 * This method splits the node to two nodes because the node is full
	 * @param node
	 * @param index
	 */
	public void bTreeSplitChild(BTreeNode node, int index) throws IOException
	{
		BTreeNode z = allocateNode();
		BTreeNode y = new BTreeNode(degree);
		y = diskRead(node.pointers[index]);

		z.isLeaf = y.isLeaf;
		z.setNumOfObjects(degree-1);
		for(int j = 0; j <= degree-2; j++) 
		{
			z.objects[j].setKey(y.objects[j+degree].getKey());
			z.objects[j].setFrequency(y.objects[j+degree].getFrequency());
		}
		if(!y.isLeaf) 
		{
			for(int k = 0; k <= degree-1; k++) 
			{			
				z.pointers[k] = y.pointers[k+degree];
			}
		}
		y.setNumOfObjects(degree-1);

		for(int in = node.getNumOfObjects(); in >= index+1; in--)
		{
			node.pointers[in+1] = node.pointers[in];
		}
		node.pointers[index+1] = z.getPosition();

		for(int m = node.getNumOfObjects()-1; m >= index; m--) 
		{
			node.objects[m+1].setKey(node.objects[m].getKey());
			node.objects[m+1].setFrequency(node.objects[m].getFrequency());
		}
		
		node.objects[index].setKey(y.objects[degree-1].getKey());
		node.objects[index].setFrequency(y.objects[degree-1].getFrequency());
		node.setNumOfObjects(node.getNumOfObjects()+1);

		for(int b = y.getNumOfObjects(); b < y.objects.length; b++)
		{
			y.objects[b].setKey(0);
			y.objects[b].setFrequency(0);
		}

		for(int c = degree; c < y.pointers.length; c++)
		{
			y.pointers[c] = 0;
		}
		//write to the disk
		diskWrite(y);
		diskWrite(z);
		diskWrite(node);
	}


	/**
	 * This method inserts the tree object to the node which is not full.
	 * @param node - BTreeNode
	 * @param key - TreeObject
	 * @throws IOException
	 */
	public void bTreeInsertNonfull(BTreeNode node, TreeObject key) throws IOException
	{
		BTreeNode childNode = null;
		int i = node.getNumOfObjects()-1;// in the book they start from 1 but our array starts from 0
		if(node.isLeaf) 
		{
			while(i >= 0 && key.getKey() < node.objects[i].getKey()) 
			{
				node.objects[i+1].setKey(node.objects[i].getKey());
				node.objects[i+1].setFrequency(node.objects[i].getFrequency());
				i = i - 1;
			}
			node.objects[i+1].setKey(key.getKey());
			node.objects[i+1].setFrequency(key.getFrequency());
			node.setNumOfObjects(node.getNumOfObjects()+1);
			diskWrite(node);
		}
		else
		{
			while(i >= 0 && key.getKey() < node.objects[i].getKey())
			{
				i--;
			}
			i++;

			childNode = diskRead(node.pointers[i]);

			if(childNode.getNumOfObjects() == (2*degree-1)) 
			{
				bTreeSplitChild(node, i);
				if(key.getKey() > node.objects[i].getKey()) 
				{
					i++;
				}
			}
			childNode = diskRead(node.pointers[i]);
			bTreeInsertNonfull(childNode, key);
		}
	}
	/**
	 * This search method is saving frequency data to the disk
	 * @param node
	 * @param key
	 * @return TreeObject
	 * @throws IOException
	 */
	private TreeObject bTreeSearchForInsert(BTreeNode node, TreeObject key) throws IOException
	{
		if(key.getFrequency() == 0) {
			return null;
		}
		int i = 0;//in the book they start from 1 but our array starts from 0
		BTreeNode childNode = null;
		while( i < node.getNumOfObjects() && key.getKey() > node.objects[i].getKey()) 
		{
			i = i + 1;
		}
		if( i < node.getNumOfObjects() && key.getKey() == node.objects[i].getKey())
		{
			node.objects[i].incFrequency();

			diskWrite(node);
			return node.objects[i];
		}
		else if(node.isLeaf) 
		{
			return null;
		}
		else 
		{
			childNode = diskRead(node.pointers[i]);
			return  bTreeSearchForInsert(childNode, key);
		}
	}
	
	/**
	 * This method inserts tree object to the b tree
	 * @param key - tree object need to be inserted
	 * @throws IOException
	 */
	public void bTreeInsert(TreeObject key) throws IOException
	{
		BTreeNode r = this.root;
		TreeObject add = bTreeSearchForInsert(root, key);
		//If the key is already in there, we don't insert it into the BTree, we just increase the frequency of the object
		
		if(add != null) 
		{
			return;
		}
		if(r.getNumOfObjects() == (2*degree-1))
		{
			BTreeNode s = allocateNode();
			this.root = s;
			s.isLeaf = false;
			s.setNumOfObjects(0);
			this.location = s.getPosition();
			s.pointers[0] = r.getPosition();
			bTreeSplitChild(s, 0);
			bTreeInsertNonfull(s, key);
		}		
		else 
		{
			bTreeInsertNonfull(r, key);
		}
	}
	/**
	 * This method is for printing out the result as in order traversal order.
	 * @param node
	 * @param seqLength
	 * @param fw
	 * @throws Exception 
	 */
	public void inOrderTraversal(BTreeNode node, int seqLength, FileWriter fw) throws Exception
	{
		BTreeConvert data = new BTreeConvert(seqLength);
		
		if(node.isLeaf())
		{
			for(int i = 0; i < node.getNumOfObjects(); i++)
			{
				fw.write(data.getDNAString(seqLength,node.objects[i].getKey()) + " : ");
				fw.write(node.objects[i].getFrequency() + "\n");
			}
		}
		
		else
		{
			for(int j = 0; j < node.getNumOfObjects(); j++)
			{
				BTreeNode n = diskRead(node.pointers[j]);
				inOrderTraversal(n, seqLength, fw);
				if(j < node.getNumOfObjects())
				{
					fw.write(data.getDNAString(seqLength,node.objects[j].getKey()) + " : ");
					fw.write(node.objects[j].getFrequency() + "\n");
				}
			}
			
			try {
				BTreeNode n = diskRead(node.pointers[node.getNumOfObjects()]);
				inOrderTraversal(n, seqLength, fw);
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
		}
	}
	public void delete(TreeObject object) throws IOException {
		TreeObject retVal =null;// The object that needs to be deleted
		BTreeNode del=null; // The node that contains the deleted object
		BTreeNode node= root;
		retVal = bTreeSearch(node,object);

		System.out.println(retVal.getKey());
		del= bTreeSearchforDelete(node,object);

		if(del.isLeaf) {
			//Case 1: The node is a leaf and has number of objects greater than t-1
			if(del.getNumOfObjects()>degree-1) {
				for(int i=0;i<del.objects.length;i++) {

					TreeObject obj = del.objects[i];

					if(obj.equals(retVal)) {


						if(i==del.objects.length-1) {
							del.objects[del.getNumOfObjects()-1]=new TreeObject(-1);
							del.setNumOfObjects(del.getNumOfObjects()-1);	
						}
						else {
							System.out.println("Lala"+i);	
							for(int j=i;j<del.objects.length-1;j++) {
								del.objects[j]=del.objects[j+1];
							}
							del.objects[del.getNumOfObjects()-1]=new TreeObject(-1);
							del.setNumOfObjects(del.getNumOfObjects()-1);	
						}
					}

				}

				diskWrite(del);
				System.out.println(del.objects[0]);
				System.out.println(del.objects[1]);
				System.out.println(del.objects[2]);

			}
		}	
		/*
		//Case 2a: The node is a leaf and has number of objects greater than t-1
		else if(!del.isLeaf()) {
			int index=0;
			BTreeNode found=null;
			for(int i=0;i<del.objects.length;i++) {

				TreeObject obj = del.objects[i];

				if(obj.equals(retVal)) {
					index=i;
					found=findPredecessor(del,index);
					if(found!=null&&found.getNumOfObjects()>degree-1) {
						TreeObject predecessor=null;		
						predecessor=found.objects[found.getNumOfObjects()-1];
						for(int j=0;j<found.objects.length;j++) {

							TreeObject ob = del.objects[i];

							if(ob.equals(retVal)) {

								if(i==del.objects.length-1) {
									del.objects[del.getNumOfObjects()-1]=new TreeObject(-1);
									del.setNumOfObjects(del.getNumOfObjects()-1);	
								}
								else {
										
									for(int m=j;m<del.objects.length-1;m++) {
										del.objects[m]=del.objects[m+1];
									}
									del.objects[del.getNumOfObjects()-1]=new TreeObject(-1);
									del.setNumOfObjects(del.getNumOfObjects()-1);	
								}
							}

						}

					}
					
					diskWrite(found);
					diskWrite(del);
					
				}

			}

		}
*/
	}
	/**
	 * 
	 * The bugging method to print out all the information related to a node
	 * @param the location of a node
	 */
	public void printNode(int location) throws IOException {
		
		file.seek(location);
		
		System.out.println("isLeaf:"+file.readBoolean());
		System.out.println("numObjects:"+file.readInt()+"\n");
		
		}
	
	//Testing purpose. I think we will remove it out after we have the delete method ready to go
	
	public static void main(String[] args) throws IOException {
		BTree tree = new BTree(2, "BTree");
		tree.bTreeInsert(new TreeObject(15));
		tree.bTreeInsert(new TreeObject(29));
		tree.bTreeInsert(new TreeObject(67));
		tree.bTreeInsert(new TreeObject(22));
		tree.bTreeInsert(new TreeObject(34));
		tree.bTreeInsert(new TreeObject(88));
		System.out.println("Child 0 of the root node location: "+ tree.root.pointers[0]);
		tree.printNode(8);
		System.out.println("Child 1 of the root node location: "+tree.root.pointers[1]);
		tree.printNode(122);
		System.out.println("The root node location: "+tree.root.getPosition());
		tree.printNode(65);
	
	}
}
