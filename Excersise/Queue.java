
public class Queue<T> implements IQueue {
	private int head, tail;
	private T[] queue;
	private int size;
	private final int CAPACITY =7;

	/*
	 * constructor to implements an empty queue
	 * 
	 */
	public Queue() {
		head=tail=1;
		size=0;
		queue= (T[]) new Object[CAPACITY];
	}


	@Override
	public void enqueue(Object value) {
		// TODO Auto-generated method stub
		queue[tail]=(T) value;
		if(tail==queue.length-1) {
			tail=1;
		}
		else {
			tail++;
		
		}
		size++;
	}

	@Override
	public Object dequeue() {
		// TODO Auto-generated method stub
		T retVal= queue[head];
		if(head==queue.length-1) {
			head=1;
		}
		else {
			head++;
		}
		size--;
		return retVal;
	}

	@Override
	public Object front() {
		// TODO Auto-generated method stub
		return queue[head];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size==0);
	}

}
