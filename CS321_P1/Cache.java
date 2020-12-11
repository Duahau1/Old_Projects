import java.util.NoSuchElementException;

public class Cache<T> implements ICache<T>{
	private int hit=0;
	private int hitRate=1;
	private int accessNum=0;
	private int capacity=0;
	private int size=0;
	private int count=0;
	private DLLNode<T> head, tail;
	/*
	 * The constructor that create the Cache with the initial implementation of an empty DLL list
	 */
	public Cache(){
		new Cache(100);
	}
	public Cache(int capacity) {
		this.capacity=capacity;
		head=tail=null;
		size=0;
	}
	/**
	 * Gets the data from cache and moves it to the front, if it's there.
	 * If not, returns null reference. 
	 * @param target - object of type T
	 * @return object of type T, or null reference 
	 */
	@Override
	public T get(T target) {
		accessNum++;
		if (size==0) {
			return null;
		}
		DLLNode<T> current= head;
		while(current!=null&&!current.getElement().equals(target)) {
			current=current.getNext();
		}
		if (current==null) {
			return null;
		}
		if (current==head) {
			hit++;
			return current.getElement();
		}
		if (current==tail) {
			tail=current.getPrevious();
			tail.setNext(null);
			current.setPrevious(null);			
		}
		current.setNext(head);
		head.setPrevious(current);
		current=head;
		hit++;
		return current.getElement();
		
	}
	/***
	 * Clears contents of the cache,
	 * but doesn't change its capacity. 
	 */
	@Override
	public void clear() {
		DLLNode<T> current=head;
		while(!current.getElement().equals(null)) {
			current.setElement(null);
			current=current.getNext();
		}
		size=0;
	}

	/***
	 * Adds given data to front of cache. 
	 * Removes data in last position, if full.
	 * @param data - object of type T
	 */
	@Override
	public void add(T data) {
		DLLNode<T> newNode= new DLLNode<T>(data);
		if (size==0) {
			head=tail=newNode;
			size++;
		}
		else if(size==1) {
			head=newNode;
			head.setNext(tail);
			tail.setPrevious(head);
			size++;
		}
		else {
			head.setPrevious(newNode);
			newNode.setNext(head);
			head=newNode;
			size++;
			if (size>capacity) {
				tail=tail.getPrevious();
				tail.setNext(null);
				size--;
			}
		}

	}

	/***
	 * Removes data in last position in cache.
	 * @throws IllegalStateException - if cache is empty. 
	 */
	@Override
	public void removeLast() {
		if (size==0) {
			throw new IllegalStateException();
		}
		if (size==1) {
			head=tail=null;
		}
		else {
			tail=tail.getPrevious();
			tail.setNext(null);
		}
		size--;
	}
	/**
	 * Removes the given target data from the cache.
	 * @throws NoSuchElementException - if target not found 
	 * @param target - object of type T 
	 */
	@Override
	public void remove(T target) {
		DLLNode<T> targetNode=head;
		while(targetNode!=null&&!targetNode.getElement().equals(target)) {
			targetNode=targetNode.getNext();
		}
		if (targetNode==null) {
			throw new NoSuchElementException();
		}
	if (head==tail) {
		head=tail=null;
	}
	if (targetNode==head) {
		head=head.getNext();
		head.setPrevious(null);
	}
	if (targetNode==tail) {
		tail=tail.getPrevious();
		tail.setNext(null);
	}
	else {
		targetNode.getNext().setPrevious(targetNode.getPrevious());
		targetNode.getPrevious().setNext(targetNode.getNext());
	}
	size--;	
	}

	/**
	 * Moves data already in cache to the front. 
	 * @throws NoSuchElementException - if data not in cache   
	 * @param data - object of type T
	 */
	@Override
	public void write(T data) {
		accessNum++;
		if (size==0) {
			throw new NoSuchElementException();
		}
		DLLNode<T> current= head;
		while(current!=null&&!current.getElement().equals(data)) {
			current=current.getNext();
		}
		if (current==null) {
			throw new NoSuchElementException();
		}
		if (current==tail) {
			tail=current.getPrevious();
			tail.setNext(null);
			current.setPrevious(null);			
		}
		current.setNext(head);
		head.setPrevious(current);
		current=head;
		hit++;
	}
	/**
	 * Get hit rate of the cache.
	 * @return double value 
	 */
	@Override
	public double getHitRate() {
		hitRate=hit/accessNum;
		return hitRate;
	}
	/**
	 * Get miss rate of the cache.  
	 * @return double value 
	 */
	@Override
	public double getMissRate() {
		
		return (1-hitRate);
	}
	/**
	 * Whether there's any data in cache. 
	 * @return boolean value 
	 */
	@Override
	public boolean isEmpty() {
		return (size==0);
	}

}
