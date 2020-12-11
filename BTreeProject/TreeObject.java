/** The Tree Object class
 * 
 */
public class TreeObject  implements Comparable<TreeObject> {
	private long key;
	private int frequency; 
	private boolean exist;
	/**
	 * Constructor to create a Tree Object 
	 * @param key
	 * @param frequency
	 */
	public TreeObject(long key) {
		this.key = key;
		this.frequency = 1; // 0 if not found
		this.exist=true;
	}
	public boolean isExist(){
	return exist;
	}
	public void setExist(boolean status){
	exist=status;	
	}
	
	/**
	 * Get a key
	 * @return key
	 */
	public long getKey() {
		return key;
	}

	/**
	 * Set the key to a tree object
	 * @param key
	 */
	public void setKey(long key) {
		this.key = key;
	}

	/**
	 * Get a frequency
	 * @return frequency
	 */
	public int getFrequency() {
		return frequency;
	}
	/**
	 * To increase the frequency of a tree object
	 * @return frequency
	 */
	public void incFrequency() {
		frequency++;
	}
	
	/**
	 * Set frequency to the given value
	 * @param frequency
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	/** Overwrite the equal method
	 * @param A tree Object
	 * @Return true if the 2 the key values are equal 
	 */
	public boolean equals(TreeObject BTobject) {
		if (BTobject==this) {
			return true;
		}
		if(!(BTobject instanceof TreeObject)) {
			return false;
		}
		TreeObject comp=BTobject;
		return (getKey()==comp.getKey());
	}
	
	
	/** Overwrite the compareTo method
	 * @param A tree Object
	 * @Return a number based on the comparison process of the 2 objects 
	 */
	@Override
	public int compareTo(TreeObject BTobject) {
		if (this.getKey() <  BTobject.getKey()) {
			return -1;
		} else if (this.getKey() >  BTobject.getKey()) {
			return 1;
		} else {
			return 0;
		}
		
	}
	
	
	/**Overwrite the toString method
	 * @Return a string to print out 
	 */
	@Override
	public String toString() {
		return "Tree Object [key=" + key + "]" + "[frequency=" + frequency + "]";
	}
	
	
}
