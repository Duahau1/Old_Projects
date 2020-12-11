import java.util.NoSuchElementException;
/*To create a hash table that contains multiple objects
 * @author Van Nguyen
 */
public class HashTable<V> {

	private int numProbes;
	private int probe;
	private int dupAmount;
	private OpenAddressType type;
	private HashObject<V>[] hashTable;
	private HashObject<V> newHash;
	private int[] frequency;
	private int capacity;
	private int size=0;
	private int maxSize=0;
	private float loadFactor;
	private final int DEFAULT_CAPACITY=13;
	private final float DEFAULT_LOAD_FACTOR=(float) 0.75;
	/*
	 * Constructor number 1 to create a hash table
	 */
	public HashTable() {
		capacity=DEFAULT_CAPACITY;
		hashTable= new HashObject[capacity];
		loadFactor=DEFAULT_LOAD_FACTOR;
		type= OpenAddressType.linear;
		numProbes=0;
		frequency= new int[capacity];

		for (int i=0;i<capacity;i++) {
			frequency[i]=0;
			hashTable[i]=null;
		}
		maxSize=(int)(loadFactor*capacity);
		size=0;
	}
	/*Constructor number 2 to create a hash table
	 * @param capacity of the hash table
	 */

	public HashTable(int newCap) {
		capacity=newCap;
		hashTable=new HashObject[capacity];
		loadFactor=DEFAULT_LOAD_FACTOR;
		type= OpenAddressType.linear;
		numProbes=0;
		frequency= new int[capacity];

		for (int i=0;i<capacity;i++) {
			frequency[i]=0;
			hashTable[i]=null;

		}
		maxSize=(int)(loadFactor*capacity);
		size=0;
	}
	/*Constructor number 3 to create a hash table
	 * @param capacity of the hash table and the load factor
	 */
	public HashTable(int newCap,float newLoad) {
		capacity=newCap;
		hashTable=new HashObject[capacity];
		loadFactor=newLoad;
		numProbes=0;
		type= OpenAddressType.linear;
		frequency= new int[capacity];

		for (int i=0;i<capacity;i++) {
			frequency[i]=0;
			hashTable[i]=null;

		}
		maxSize=(int)(loadFactor*capacity);
		size=0;
	}
	/*Constructor number 4 to create a hash table
	 * 
	 * @param capacity of the hash table and the load factor 
	 * and the addressing type
	 */
	public HashTable(int newCap, float newLoad, OpenAddressType newType ) {
		hashTable=new HashObject[newCap];
		loadFactor=newLoad;
		capacity=newCap;
		type=newType;
		numProbes=0;
		frequency= new int[capacity];

		for (int i=0;i<capacity;i++) {
			frequency[i]=0;
			hashTable[i]=null;

		}
		maxSize=(int)(loadFactor*capacity);
		size=0;
	}
	/*insert the object with value v and its key into the hash table
	 * @param value and key of the object
	 */
	public void put(V value,int key) {

		newHash= new HashObject(value,key);
		for (probe= 0; probe < capacity ; probe++) {
			int hashFunc = getHash(newHash.getKey(),probe);

			if (hashTable[hashFunc] == null) {
				hashTable[hashFunc] = newHash;
				if(probe==0) {
					newHash.setProbeCount(newHash.getProbeCount());
				}
				size++;
				return;
			} 
			if (hashTable[hashFunc].equals(newHash)) {
				frequency[hashFunc]++;
				hashTable[hashFunc].setDuplicateCount(hashTable[hashFunc].getDuplicateCount() + 1);
				dupAmount++;
				return;
			} 
			else {
				newHash.setProbeCount(newHash.getProbeCount() + 1);
				numProbes = numProbes + 1;
			}
		}
	}

	/*
	 * @return true if the value is already in the hash table
	 * 
	 */
	public boolean contains(V value, int key) {
		if(isEmpty()) {
			return false;
		}
		HashObject newHash1= new HashObject(value,key);
		for(int i=0;i<capacity;i++) {
			if(hashTable[i]!=null &&hashTable[i].equals(newHash1)) {
				return true;
			}
		}
		return false;
	}


	/*
	 * @return the value of the hash object that has been removed
	 * @param the value and the key number of the hash object
	 */

	public V remove(V value, int key) {
		if (!contains(value, key)||isEmpty()) {
			throw new  NoSuchElementException();
		}
		HashObject retHash= new HashObject(value,key);
		V retVal=null;
		for(int i=0; i<capacity;i++ ) {
			if(hashTable[i]!=null&&hashTable[i].equals(retHash)) {
				hashTable[i]=null;
				frequency[i]=0;
				retVal= (V) retHash.getValue();
			}
		}
		size--;
		return retVal;
	}
	/*
	 * To remove all of the elements from the hash table
	 * (i.e. sets all the slots in the array to null 
	 * and resets all of the frequencies) and resets the size and 
	 * number of probes, but the capacity and load factor do not change.
	 */
	public void clear() {
		for(int i=0;i<capacity;i++) {
			hashTable[i]=null;
			frequency[i]=0;
		}
		size=0;
		numProbes=0;

	}
	/*@return the hashing index
	 * @param int key value and int number of attempts to 
	 * insert a given value into the table
	 */
	public int getHash(int key, int attempt ) {
		int retVal=0;
		if (type==OpenAddressType.linear) {
			retVal= ((hash1(key,capacity)+attempt)) % capacity;
		}
		else if(type==OpenAddressType.double_hash) {
			retVal= (hash1(key,capacity)
					+attempt*hash2(key,capacity)) % capacity;
		}
		else if(type==OpenAddressType.quadratic) {
			retVal= (int) ((hash1(key,capacity)
					+(double)attempt*0.5+ (double)(Math.pow(attempt,2)*0.5)) % capacity);
		}
		else {
			throw new IllegalStateException();
		}
		return retVal;
	}


	/*@param the key value and the capacity of the hash table
	 * @return hash number
	 * The hash function 1 of the double hashing method
	 */
	private int hash1(int k,int m ) {

		int retVal= k % m; 
		return retVal;
	}
	/*@param the key value and the capacity of the hash table
	 * @return the hash number
	 * The hash function 2 of the double hashing method
	 */
	private int hash2(int k,int m ) {
		int retVal= 1+ (k % (m-2)); 
		return retVal;
	}
	/*
	 * @return the OpenAddressType we are choosing
	 * To get the type of hashing
	 */
	public OpenAddressType getType() {
		return type;
	}
	/*To get the capacity of the hash table
	 * @return the capacity of the hash table
	 */
	public int getCapacity() {
		return capacity;
	}
	/*
	 * To get the load factor 
	 * @return the load factor
	 */
	public float getLoadFactor() {
		return loadFactor;

	}

	/*
	 * To get the size of the hash table
	 * @return the size of the hash table 
	 */
	public int size() {
		return size;
	}
	/*
	 * @return true if the hash table is empty
	 */
	public boolean isEmpty() {
		return (size==0);
	}
	/* To get the number of probes of one hash object
	 * @return int number of probes
	 */
	public int getNumProbes() {
		//return numProbes;
		if(isEmpty() || newHash.getProbeCount()==0  ){
			return 0;
		}
		else {
			return (newHash.getProbeCount());
		}
	}

	/* To get the number of probes
	 * @return int number of probes
	 */
	public int getTotalProbes() {
		return numProbes;

	}
	/*@return the maxSize of the hash table
	 * To get the max size of the hash table
	 */
	public int getMaxSize() {
		maxSize=(int) (loadFactor*capacity);
		if (maxSize>capacity) {
			throw new IllegalStateException();
		}

		return maxSize;
	}

	/*@param the value and the key of the object
	 * @return int the frequency you look for a given value and its key
	 * If the value is not found
	 * in the hash table, the method should return -1.
	 */
	public int getFrequency(V value, int key ) {
		if (!contains(value,key)) {
			return -1;
		}

		HashObject retHash=new HashObject(value,key);
		int retVal=0;
		for(int i=0; i<capacity;i++ ) {
			if(hashTable[i]!=null&&hashTable[i].equals(retHash)) {
				retVal =frequency[i];
			}
		}	
		return retVal;
	}
	/*
	 * @return the number of inserted
	 */
	public int getSize() {
		return size;
	}

	/*
	 * @return the number of duplicates
	 */
	public int getNumDuplicates() {
		int retVal=0;
		for(int h: frequency) {
			retVal=retVal + h;
		}

		return retVal;
	}

	/**
	 * Gets the average number of probes for the table.
	 * @return the average number of probes
	 */
	public double getAverage() {
		return (double)getTotalProbes() / (double) size;
	}
	/*
	 *@return the whole hash table
	 */
	public String toString() {
		String retS=" ";
		for (int i=0;i<capacity;i++) {
			if(hashTable[i]!=null) {
				retS= "table "+"["+i+"] "+ hashTable[i].toString() +" "+ frequency[i];
				System.out.println(retS);
			}
		}
		return null;
	}

}

