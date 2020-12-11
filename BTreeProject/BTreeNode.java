import java.util.Arrays;

/**
 * The BTreeNode class
 * 
 */
public class BTreeNode {

	public TreeObject objects[];
	public int pointers[];
	public boolean isLeaf;
	private int numOfObjects;
	private int maxNum;
	private int degree;
	private int position;
	/**
	 * 
	 * Basic a constructor to create a B- Tree Node
	 * 
	 * @param degree(utilize all the capacity in each node [optimal t degree])
	 */
	public BTreeNode(int degree) {
		this.degree = degree;
		this.maxNum = degree * 2 - 1;
		this.objects = new TreeObject[degree * 2 - 1];// maximum key = 2*degree -1
		// Create a list of Tree Objects
		for (int i = 0; i < (degree * 2 - 1); i++) {
			this.objects[i] = new TreeObject(0);
		}
		this.pointers = new int[degree * 2]; // A list of pointers with the parent pointer is 4 bytes
		this.isLeaf = true; // every new created node is a leaf
		this.numOfObjects = 0;// the number of objects in each node
		this.position=0;
	}

	/**
	 * Check if we are at t-1 number of objects in the node
	 * 
	 * @return true if the number of nodes is equal to degree -1
	 */
	public boolean delCond() {
		return (numOfObjects == (degree - 1));
	}

	/**
	 * Add the node in the BtreeNode
	 * 
	 * @param TreeObject
	 * @throws Exception 
	 */
	public void addNode(TreeObject insert) throws Exception {
		if (isFull()) {
			throw new Exception();
		} else {
			objects[numOfObjects] = insert;
			numOfObjects++;
		}
	}

	/**
	 * Remove the node in the BtreeNode
	 * 
	 * @return a deleted TreeObject
	 * @param the deleted node
	 */
	public TreeObject removeNode(TreeObject del) {
		if (isEmpty()) {
			return null;
		} else {
			int check = 0;
			TreeObject retVal = null;
			while (check < numOfObjects && !objects[check].equals(del)) {
				check++;
			}
			retVal = objects[check];
			numOfObjects--;
			for (int i = check; i < numOfObjects; i++) {
				objects[i] = objects[i + 1];			
			}
			
				objects[numOfObjects] = null;
				return retVal;	
		}
	}

	/**
	 * Get an object from TreeObject class
	 * 
	 * @return objects
	 */
	public TreeObject[] getObjects() {
		return objects;
	}

	/**
	 * Set object to the given value
	 * 
	 * @param objects
	 */
	public void setObjects(TreeObject[] objects) {
		this.objects = objects;
	}

	/**
	 * sets a single object in the node
	 * 
	 * @param index
	 * @param object
	 */
	public void setSingleObject(int index, TreeObject object) {
		objects[index] = object;
	}

	/**
	 * returns a single object from the node
	 * 
	 * @param index
	 * @return
	 */
	public TreeObject getSingleObject(int index) {
		return objects[index];
	}

	/**
	 * shifts the objects in the node to put a smaller object in its place
	 * 
	 * @param index
	 * @param node
	 */
	public void shift(int index, BTreeNode node) {
		for (int i = index; i < getNumOfObjects(); i++) {
			objects[index] = objects[index + 1];
		}
	}

	/**
	 * Get a pointer
	 * 
	 * @return pointer
	 */
	public int[] getPointers() {
		return pointers;
	}

	/**
	 * Set pointers to the given value
	 * 
	 * @param pointers
	 */
	public void setPointers(int[] pointers) {
		this.pointers = pointers;
	}

	/**
	 * Get a isLeaf
	 * 
	 * @return isLeaf
	 */
	public boolean isLeaf() {
		return isLeaf;
	}

	/**
	 * Set isLeaf to the given value
	 * 
	 * @param isLeaf
	 */
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**
	 * Get the number of objects
	 * 
	 * @return numOfobject
	 */
	public int getNumOfObjects() {
		return numOfObjects;
	}

	/**
	 * get the max number of objects the node can hold
	 * 
	 * @return
	 */
	public int getMaxNum() {
		return maxNum;
	}

	/**
	 * Set the number of object to the given value
	 * 
	 * @param numOfObjects
	 */
	public void setNumOfObjects(int numOfObjects) {
		this.numOfObjects = numOfObjects;
	}

	/**
	 * Check if the node is full
	 * 
	 * @return true if the node is full
	 */
	public boolean isFull() {
		return (numOfObjects == maxNum);
	}

	/**
	 * Check if the node is empty
	 * 
	 * @return true if the node is empty
	 */
	public boolean isEmpty() {
		return (numOfObjects == 0);
	}
	/**
	 * Get a position
	 * @return position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Set the position to the given value
	 * @param position
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	/**
	 * Overwrite the toString method Return the string to print out
	 */
	@Override
	public String toString() {
		return "BTreeNode [objects=" + '\n' + Arrays.toString(objects) + ", pointers=" + Arrays.toString(pointers) + ", isLeaf="
				+ isLeaf + ", numOfObjects=" + numOfObjects + position+"= + position ]";
	}
}
