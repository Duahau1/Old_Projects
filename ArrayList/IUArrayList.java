import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Array-based implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, but
 * ListIterator is unsupported. 
 * 
 * @author 
 *
 * @param <T> type to store
 */
public class IUArrayList<T> implements IndexedUnsortedList<T> {
	private static final int DEFAULT_CAPACITY = 10;
	private static final int NOT_FOUND = -1;

	private T[] array;
	private int rear;
	private int modCount;

	/** Creates an empty list with default initial capacity */
	public IUArrayList() {
		this(DEFAULT_CAPACITY);
	}

	/** 
	 * Creates an empty list with the given initial capacity
	 * @param initialCapacity
	 */
	@SuppressWarnings("unchecked")
	public IUArrayList(int initialCapacity) {
		array = (T[])(new Object[initialCapacity]);
		rear = 0;
		modCount = 0;
	}

	/** Double the capacity of array */
	private void expandCapacity() {
		array = Arrays.copyOf(array, array.length*2);
	}

	@Override
	public void addToFront(T element) {
		// TODO 
		if (rear>=array.length) {
			expandCapacity();
		}
		for (int i=rear;i>0;i--) {
			array[i]=array[i-1];
		}
		array[0]= element;
		modCount++;
		rear++;
	}

	@Override
	public void addToRear(T element) {
		// TODO 
		if (rear>=array.length) {
			expandCapacity();
		}
		array[rear]= element;
		modCount++;
		rear++;

	}

	@Override
	public void add(T element) {
		// TODO 
		if (rear>=array.length) {
			expandCapacity();
		}
		array[rear]= element;
		modCount++;
		rear++;
	}

	@Override
	public void addAfter(T element, T target) {
		// TODO 
		if (rear>=array.length) {
			expandCapacity();
		}
		int index= indexOf(target);

		if (index<0) {
			throw new NoSuchElementException();
		}

		for (int i=rear;i>index+1;i--) {
			array[i]=array[i-1];
		}
		array[index+1]=element;
		modCount++;
		rear++;
	}

	@Override
	public void add(int index, T element) {
		// TODO 
		if (rear>=array.length) {
			expandCapacity();
		}
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		for (int i=rear;i>index;i--) {
			array[i]=array[i-1];
		}
		array[index]=element;
		modCount++;
		rear++;
	}

	@Override
	public T removeFirst() {
		// TODO 
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal=array[0];
		for (int i=0;i<rear-1;i++) {
			array[i]=array[i+1];
		}
		array[rear-1]=null;
		modCount++;
		rear--;
		return retVal;
	}

	@Override
	public T removeLast() {
		// TODO 
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal= array[rear-1];
		array[rear-1]=null;
		modCount++;
		rear--;
		return retVal;
	}

	@Override
	public T remove(T element) {
		int index = indexOf(element);
		if (index == NOT_FOUND) {
			throw new NoSuchElementException();
		}

		T retVal = array[index];
		rear--;
		//shift elements
		for (int i = index; i < rear; i++) {
			array[i] = array[i+1];
		}
		array[rear] = null;
		modCount++;

		return retVal;
	}

	@Override
	public T remove(int index) {
		// TODO 
		if(index<0||index>=size()) {
			throw new IndexOutOfBoundsException();
		}
		T retVal = array[index];

		for (int i=index;i<rear-1;i++) {
			array[i]=array[i+1];
		}
		array[rear-1] = null;
		modCount++;
		rear--;
		return retVal;
	}

	@Override
	public void set(int index, T element) {
		// TODO 
		if(index<0||index>=size()) {
			throw new IndexOutOfBoundsException();
		}
		array[index]=element;
		modCount++;
	}

	@Override
	public T get(int index) {
		// TODO 
		if(index<0||index>=size()) {
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}

	@Override
	public int indexOf(T element) {
		int index = NOT_FOUND;

		if (!isEmpty()) {
			int i = 0;
			while (index == NOT_FOUND && i < rear) {
				if (element.equals(array[i])) {
					index = i;
				} else {
					i++;
				}
			}
		}

		return index;
	}

	@Override
	public T first() {
		// TODO 
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return array[0];
	}

	@Override
	public T last() {
		// TODO 
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return array[rear-1];
	}

	@Override
	public boolean contains(T target) {
		return (indexOf(target) != NOT_FOUND);
	}

	@Override
	public boolean isEmpty() {
		// TODO 
		return (rear==0);
	}

	@Override
	public int size() {
		// TODO 
		return rear;
	}
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		for (int i = 0; i < rear; i++) {
			str.append(array[i]);
			str.append(", ");
		}
		if (rear > 0) {
			str.delete(str.length()-2, str.length()); //drop trailing ", "
		}
		str.append("]");
		return str.toString();
	}




	@Override
	public Iterator<T> iterator() {
		return new ALIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

	/** Iterator for IUArrayList */
	private class ALIterator implements Iterator<T> {
		private int nextIndex;
		private int iterModCount;
		private boolean next;
		
		public ALIterator() {
			nextIndex = 0;
			iterModCount = modCount;
			next=false;
		}

		@Override
		public boolean hasNext() {
			// TODO 
			if (iterModCount!=modCount) {
				throw new ConcurrentModificationException();
			}
			return (nextIndex<rear);
		}

		@Override
		public T next() {
			// TODO 
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T retVal= array[nextIndex];
			nextIndex++;
			next=true;
			return retVal;
		}

		@Override
		public void remove() {
			// TODO
			if (iterModCount!=modCount) {
				throw new ConcurrentModificationException();
			}

			if (!next) {
				throw new IllegalStateException();
			}


			for(int i = nextIndex-1; i<rear-1;i++)
			{
				array[i] = array[i+1];
			}

			array[rear-1]=null;
			nextIndex--;
			next=false;
			rear--;
			modCount++;
			iterModCount++;
			//increment modCount and itermodCount should be in sync. if any of the iterator change the list so they
			//will not blow up. it can be the possiblity that the array 
		}
	}
}
