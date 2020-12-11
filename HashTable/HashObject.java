import java.util.Objects;
/*To create Hash Objects
 * @author Van Nguyen
 */

public class HashObject<V> {
	private V value;
	private int key;
	private int duplicateCount;
	private int probeCount;
	/*@param value of type V and a int key
	 * Constructor to create a hash Object
	 */
	public HashObject(V value, int key) {
		this.key=key;
		this.value=value;
		this.duplicateCount=0;
		this.probeCount=0;
	}
	/**
	 * gets the number of duplicates of the object
	 * @return duplicateCount
	 */
	public int getDuplicateCount(){
		return duplicateCount;
	}
	/**
	 * gets the number of probes of the object
	 * @return probeCount
	 */
	public int getProbeCount(){
		return probeCount;
	}
	/**
	 * sets the duplicateCount equal to the parameter
	 * @param dupCount
	 */
	public void setDuplicateCount(int dupCount){
		this.duplicateCount = dupCount;
	}

	/**
	 * sets the probeCount equal to the parameter
	 * @param probCount
	 */
	public void setProbeCount(int probCount){
		this.probeCount = probCount;
	}

	/**
	 * increments duplicate count
	 */
	public void incrementDupCount(){
		this.duplicateCount++;
	}

	/**
	 * increments the probe count
	 */
	public void incrementProbeCount(){
		this.probeCount++;
	}
	/*
	 *@return value of the hash object 
	 */
	public V getValue() {
		return value;
	}

	/*
	 *@return int key of the hash object 
	 */
	public int getKey() {
		return key;
	}
	/*
	 * @param value of the hash object
	 */
	public void setValue(V newVal) {
		value=newVal;
	}
	/*
	 * @param key of the hash object
	 */
	public void setKey(int newK) {
		key=newK;
	}
	/*
	 * @return the value and the key of the hash Object
	 */
	public String toString()
	{
		return value + " " ; 
	}
	/*To check if the 2 hash objects are equal
	 * @ param HashObject 
	 *@return true/false to determine if the objects are equal
	 */
	public boolean equals(Object newHash) {
		if (newHash==this) {
			return true;
		}
		if(!(newHash instanceof HashObject)) {
			return false;
		}
		HashObject hc=(HashObject) newHash;
		return (getValue().equals(hc.getValue())&&getKey()==hc.getKey());
	}
	/*
	 * To create the key of the hash object
	 */
	public int hashCode() {
		return (key=Math.abs(Objects.hash(value,key)));
	}

}
