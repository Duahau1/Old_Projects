/**
 * Class representing a priority queue using the Max heap 
 * 
 * @author Van Nguyen 
 *
 * @param <T> - generic object of type T 
 */
public class PQueue<T> {
	
	private MaxHeap<T> heap;
	/*
	 * The constructor that creates a priority queue
	 */
	public PQueue(){
		heap = new MaxHeap<T>();
	}
/*
 * The constructor that takes in the list of objects and added in the priority queue
 * and other is the associated key value
 * @param T[] objects and int[] key value
 */
	public PQueue(T[] objects, int[] values) {
		heap=new MaxHeap(objects,values);
	}
	/*To get the highest priority object
	 * @return T the highest priority object
	 */
	public T maximum() {
		T retVal=heap.heapMax();
		return retVal;
	}
	/*To extract the highest priority object
	 * @return T the highest priority object
	 */
	public T extractMax() {
		T retVal=heap.extractHeapMax();
		return retVal;
	}
	/*
	 * To increase the key element of the current position to y
	 * @param int current and int y
	 */
	public void increaseKey(int current, int y) {
		heap.increaseHeapKey(current, y);
	}
	/*To insert the Element with the associated key
	 * @param T value and int key
	 * 
	 */
	public void insert(T value, int key) {
		heap.maxHeapInsert(value, key);
	}
	/*
	 * To check if the priority queue is empty
	 * @return true if it's empty
	 */
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	/*
	 * To get the size of the priority queue
	 * @return the size of the priority queue
	 */
	public int size() {
		return heap.getHeapSize();
	}

}
