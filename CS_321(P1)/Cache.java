import java.util.NoSuchElementException;

public class Cache<T> implements ICache<T>{
	private double hitNum=0;
	private DLLNode<T> head, tail;
	private double hitRate=0;
	private double missRate=0;
	private boolean getCall;
	private double missNum=0;
	private int capacity=0;
	private DLL<T> list;
	/*
	 * The constructor that create the Cache with the initial implementation of an empty DLL list
	 */
	public Cache(){
		new Cache(100);
	}
	public Cache(int cacheSize) {
		capacity=cacheSize;
		hitRate=0;
		missNum=0;
		hitNum=0;
		getCall=false;
		list= new DLL<T>();

	}
	/**
	 * Gets the data from cache and moves it to the front, if it's there.
	 * If not, returns null reference. 
	 * @param target - object of type T
	 * @return object of type T, or null reference 
	 */
	@Override
	public T get(T target) {
		getCall=true;
		if (list.isEmpty()) {
			list.addToFront(target);
			missNum++;
			return null;
		}
		if (list.contains(target)) {
			list.remove(target);
			list.addToFront(target);
			hitNum++;
			return target;
		}
		missNum++;
		add(target);
		return null;
	}
	/***
	 * Clears contents of the cache,
	 * but doesn't change its capacity. 
	 */
	@Override
	public void clear() {
		list.clear();
	}

	/***
	 * Adds given data to front of cache. 
	 * Removes data in last position, if full.
	 * @param data - object of type T
	 */
	@Override
	public void add(T data) {
		if(list.size()<capacity) {
			if (list.contains(data)) {
				list.remove(data);
			}
			list.addToFront(data);
		}
		else {
			if(list.contains(data)) {
				list.remove(data);
				list.addToFront(data);
			}
			list.addToFront(data);
			list.removeLast();
		}
	}

	/***
	 * Removes data in last position in cache.
	 * @throws IllegalStateException - if cache is empty. 
	 */
	@Override
	public void removeLast() {
		if (isEmpty()) {
			throw new IllegalStateException();
		}
		list.removeLast();

	}
	/**
	 * Removes the given target data from the cache.
	 * @throws NoSuchElementException - if target not found 
	 * @param target - object of type T 
	 */
	@Override
	public void remove(T target) {
		if (list.isEmpty()) {
			throw new NoSuchElementException();
		}
		if (!list.contains(target)) {
			throw new NoSuchElementException();
		}
		list.remove(target);
	}

	/**
	 * Moves data already in cache to the front. 
	 * @throws NoSuchElementException - if data not in cache   
	 * @param data - object of type T
	 */
	@Override
	public void write(T data) {	
		if (!list.contains(data)) {
			throw new NoSuchElementException();
		}
		else {
			list.remove(data);
			list.addToFront(data);
		}
	}

	/**
	 * Get the cache's capacity
	 * @return int capacity of the cache
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 *Get the number of hits 
	 * @return int number of hits
	 */
	public int getHit() {
		return (int)hitNum;
	}
	/**
	 * Get the number of misses
	 * @return int number of misses
	 */
	public int getMiss() {
		return (int)missNum;
	}
	/**
	 * Get hit rate of the cache.
	 * @return double value 
	 */
	@Override
	public double getHitRate() {
		if (!getCall) {
			missNum=1;
		}
		hitRate=hitNum/(hitNum+missNum);
		return hitRate;
	}
	/**
	 * Get miss rate of the cache.  
	 * @return double value 
	 */
	@Override
	public double getMissRate() {	
		missRate=1-getHitRate();
		return missRate;
	}
	
	/**
	 * To check of the cache contain the token
	 */
	public boolean contain(T target) {
		return (list.contains(target)); 
	}
	/**
	 * Whether there's any data in cache. 
	 * @return boolean value 
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

}
