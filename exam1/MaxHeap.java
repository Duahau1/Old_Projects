import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Class representing a max heap in a heap ADT. 
 * 
 * @author Van Nguyen 
 *
 * @param <T> - generic object of type T 
 */
public class MaxHeap<T>{

	private HeapNode<T>[] heap;
	private int heapSize;
	private int capacity;
	private final int DEFAULT_CAPACITY=50;
/*
 * The constructor which creates a empty heap with a empty array of 
 * heap nodes
 */
	public MaxHeap() {
		heapSize=0;
		capacity=DEFAULT_CAPACITY;
		heap = new HeapNode[capacity];	

	}
	/*
	 * The constructor which add a list of objects to heap with a list of
	 * key values associated with each object 
	 * @param T[] object and int[] keys
	 */
	public MaxHeap(T[] objects, int[] keys) {
		
		capacity = DEFAULT_CAPACITY;
		heapSize=objects.length;
		heap = new HeapNode[capacity];
		for (int j =0; j<objects.length;j++) {
			HeapNode<T> heapNode = new HeapNode<T>(objects[j], keys[j]);
			heap[j + 1] = heapNode;
		}
		for(int i=heapSize/2;i>=1;i--) {
			maxHeapify(i);
		}
		
	}
	/*
	 * @return T the max heap at position 1
	 */
	public T heapMax(){
		return heap[1].getObject(); 
	}
	/*
	 * If it's empty you throw NoSuchElement Exception to bail out.
	 * @return T the max heap at position 1
	 */
	public T extractHeapMax() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal= heap[1].getObject();
		heap[1]=heap[heapSize];
		heapSize--;
		maxHeapify(1);
		return retVal;
	}
	/*To increase the key value of a node
	 * @param the current position and the key new key value
	 * 
	 */
	public void increaseHeapKey(int current,int key) {
		if (key > heap[current].getKey()) {
			heap[current].setKey(key);
		}
		while(current>1 && heap[parent(current)].getKey()<heap[current].getKey()) {
			exchange(current,parent(current));
			current=parent(current);
		}
	}
	/*
	 * To insert a new value to the next spot at the bottom at the heap
	 * @param T value (or the object) and the key value you pass in
	 */
	public void maxHeapInsert(T value,int key ) {
		if(heapSize> capacity) {
			expandCapacity();
		}
		HeapNode<T> newNode = new HeapNode<T>(value, key);
		heap[heapSize+1]=newNode;
		heapSize++;
		int parent =parent(heapSize);
		int cont= heapSize;
		while(parent>=1 && heap[parent].getKey()<heap[cont].getKey()) {
			exchange(parent,cont);
			cont=parent(cont);
			parent=parent(cont);
		}
	}
	/*Recursive procedure for maintaining the max heap property
	 * @param the position you want to rearrange the heap
	 */
	public void maxHeapify(int position ) {
		int l=left(position);
		int r=right(position);
		int largest = 0;
			if(l<=heapSize &&heap[l].getKey()>heap[position].getKey()) {
				largest=l;
			}
			else {
				largest=position;
			}
			if(r<=heapSize &&heap[r].getKey()>heap[largest].getKey()) {
				largest=r;
			}
			if(largest!=position) {
				exchange(position, largest);	
				maxHeapify(largest);
			}
		
	}

	/*
	 * To get the heapSize
	 * @return heapSize
	 */

	public int getHeapSize() {
		return heapSize;
	}

	/*
	 * To check if the max heap is empty
	 * @return true if the heap is empty
	 */

	public boolean isEmpty() {
		return (heapSize==0);
	}


	/*
	 * To swap the two nodes
	 * @param the 2 indexes you want to swap with each other
	 */

	private void exchange (int x, int y) {
		HeapNode<T> temp;
		temp=heap[x];
		heap[x]=heap[y];
		heap[y]=temp;
	}
	/*To get the index of the left child
	 * @param the position of a node
	 * @return index of the left child
	 */
	private int  left(int position) {
		return (position*2);
	}
	/*To get the index of the right child
	 * @param the position of a node
	 * @return index of the right child
	 */
	private int  right(int position) {
		return (2*position+1);
	}

	/*To get the index of the parent of a child
	 * @param int position: the position of the current pointer
	 * @return the position of its parent
	 */
	private int parent(int position) {
		// TODO Auto-generated method stub
		return (position/2);
	}
/*
 * To set the heap Size
 * @param the size
 */
	private void setHeapSize(int size) {
		heapSize=size;
	}
	/*
	 * To set the heap capacity
	 * @param the size
	 */
	private void setCapacity(int cap) {
		capacity =cap;
	}
	/*
	 * To expand the capacity of the heap in case it gets overflow
	 * 
	 */
	private void expandCapacity() {
		heap=Arrays.copyOf(heap, capacity*2);
		//capacity = DEFAULT_CAPACITY * 2;

	}


}
