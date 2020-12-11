import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Single-linked node implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, but
 * ListIterator is unsupported.
 * 
 * @author 
 * 
 * @param <T> type to store
 */
public class IUSingleLinkedList<T> implements IndexedUnsortedList<T> {
	private LinearNode<T> head, tail;
	private int size;
	private int modCount;

	/** Creates an empty list */
	public IUSingleLinkedList() {
		head = tail = null;
		size = 0;
		modCount = 0;
	}

	@Override
	public void addToFront(T element) {
		LinearNode<T> newNode= new LinearNode<T>(element);
		newNode.setNext(head);
		head=newNode;
		if (tail==null) {
			tail=newNode;
		}
		size++;	
		modCount++;
	}

	@Override
	public void addToRear(T element) {
		LinearNode<T> newNode= new LinearNode<T>(element);
		if (tail!=null) {
			tail.setNext(newNode);
		}
		else {
			head=newNode;
		}
		tail=newNode;
		size++;
		modCount++;
	}

	@Override
	public void add(T element) {
		// TODO 
		addToRear(element);
	}

	@Override
	public void addAfter(T element, T target) {
		// TODO 
		LinearNode<T> targetNode= head;

		while(targetNode!=null && !targetNode.getElement().equals(target)) {
			targetNode=targetNode.getNext();	
		}
		if (targetNode==null) {
			throw new NoSuchElementException();
		}
		LinearNode<T> newNode= new LinearNode<T>(element);
		if(target.equals(tail.getElement())) {
			tail.setNext(newNode);
			tail=newNode;
		}
		else{
			newNode.setNext(targetNode.getNext());
			targetNode.setNext(newNode);
		}

		size++;
		modCount++;
	}

	@Override
	public void add(int index, T element) {
		// TODO 
		if(index<0||index>size()) {
			throw new IndexOutOfBoundsException();
		}
		int number=0;
		LinearNode<T> targetindex=head;
		
		LinearNode<T> newNode= new LinearNode<T>(element);
		while(number!=index) {
			targetindex=targetindex.getNext();
			number++;
		}
		

		if (size()==0) {
			head=tail=newNode;
		}
		else if(index==0) {
			newNode.setNext(head);
			head=newNode;
		}
		else if (index==size()) {
			tail.setNext(newNode);
			tail=newNode;
		}
		else {

			newNode.setNext(targetindex.getNext());
			targetindex.setNext(newNode);
		}
		size++;
		modCount++;

	}

	@Override
	public T removeFirst() {
		// TODO 
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		T element= head.getElement();
		if (head==tail) {
			head=tail=null;
		}
		else {
			head=head.getNext();
		}
		size--;
		modCount++;
		return element;
	}

	@Override
	public T removeLast() {
		// TODO 
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T element=tail.getElement();

		if (head==tail) {
			head=tail=null;
		}

		else {
			LinearNode<T> newTail= head;
			while(newTail.getNext()!=tail) {
				newTail=newTail.getNext();
			}
			tail=newTail;	
			tail.setNext(null);
		}
		size--;
		modCount++;
		return element;
	}

	@Override
	public T remove(T element) {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		
		LinearNode<T> previous = null;
		LinearNode<T> current = head;

		while (current != null && !current.getElement().equals(element)) {
				previous = current;
				current = current.getNext();
			
		}

		if (current==null) {
			throw new NoSuchElementException();
		}

		if (size() == 1) { //only node
			head = tail = null;
		} else if (current == head) { //first node
			head = current.getNext();
		} else if (current == tail) { //last node
			tail = previous;
			tail.setNext(null);
		} else { //somewhere in the middle
			previous.setNext(current.getNext());
		}

		size--;
		modCount++;

		return current.getElement();
	}

	@Override
	public T remove(int index) {
		if(index<0||index>=size()) {
			throw new IndexOutOfBoundsException();
		}
		int number=0;
		LinearNode<T> current = head;
		LinearNode<T> previous = head;
		while(number!=index) {
			previous=current;
			current=current.getNext();
			number++;
		}
		T retVal=current.getElement();
		
		
		if (index==0) {
			if (size()==1) {
				head=tail=null;
			}
			else {
				head=head.getNext();
			}
			
		}
		else if (index==size()-1) {
			tail=previous;
			tail.setNext(null);
		}
		else {
			previous.setNext(current.getNext());	
		}
		size--;
		modCount++;
		return retVal;	   
	}

	@Override
	public void set(int index, T element) {
		// TODO 
		if(index<0||index>=size()) {
			throw new IndexOutOfBoundsException();
		}

		LinearNode<T> current = head;
int num=0;
		while(num!=index) {
			current=current.getNext();
			num++;
		}
		current.setElement(element);

		modCount++;
	}

	@Override
	public T get(int index) {
		// TODO 
		if(index<0||index>=size()) {
			throw new IndexOutOfBoundsException();
		}
		LinearNode<T> current = head;
		int num=0;
		while(num!=index) {
			current=current.getNext();
			num++;
		}

		return current.getElement();
	}

	@Override
	public int indexOf(T element) {
		// TODO 
		int index=0;
		LinearNode<T> current = head;

		while(current!=null&&!current.getElement().equals(element)) {
			current=current.getNext();
			index++;
		}
		if (current==null) {
			index=-1;
		}
		return index;


	}

	@Override
	public T first() {

		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal=head.getElement();
		return retVal;
	}

	@Override
	public T last() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		return tail.getElement();


	}

	@Override
	public boolean contains(T target) {
		// TODO 

		return (indexOf(target)!=-1);
	}

	@Override
	public boolean isEmpty() {
		// TODO 
		return (size==0);
	}

	@Override
	public int size() {
		// TODO 
		return size;
	}
	//	public String toString() {
	//		StringBuilder str= new StringBuilder();
	//		LinearNode<T>current=head;
	//		str.append("[");
	//		while(current!=null) {
	//			str.append(current.getElement().toString());
	//			str.append(",");
	//			current=current.getNext();
	//		}
	//		str.delete(size-2,size);
	//		if (!isEmpty()) {
	//			str.delete(str.length()-2,str.length());	
	//		}
	//		str.append("]");
	//		return str.toString();
	//
	//
	//	}
	public String toString() {
		if (head == null) {
			return "[]";
		} else {
			String result = "[" + head.getElement();
			LinearNode current = head.getNext();
			while (current != null) {
				result += ", " + current.getElement();
				current = current.getNext();
			}
			result += "]";
			return result;
		}
	}


	@Override
	public Iterator<T> iterator() {
		return new SLLIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

	/** Iterator for IUSingleLinkedList */
	private class SLLIterator implements Iterator<T> {
		private LinearNode<T> nextNode;
		private LinearNode<T> previous;
	//	private LinearNode<T> previous1;
		private int iterModCount;

		private boolean next;
		/** Creates a new iterator for the list */
		public SLLIterator() {
			nextNode = head;
			iterModCount = modCount;
			previous=null;
		//	previous1=null;
			next=true;
		}

		@Override
		public boolean hasNext() {
			// TODO 
			if (iterModCount!=modCount) {
				throw new ConcurrentModificationException();
			}

			return nextNode!=null;
		}

		@Override
		public T next() {
			// TODO 

			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T retVal=nextNode.getElement();
		//	previous1=previous;
			previous=nextNode;
			nextNode=nextNode.getNext();
			next=false;
			return retVal;
		}

		@Override
		public void remove() {
			// TODO
			if (iterModCount!=modCount) {
				throw new ConcurrentModificationException();
			}
			if (previous==null||next) {
				throw new IllegalStateException();	
			}
			if (previous==head) {
				head=nextNode;

			}
			else {
				LinearNode<T> current= head;
				while(current.getNext()!= previous) {
					current=current.getNext();
				}
			if (nextNode==null) {
				tail=current;
				tail.setNext(null);
			}
			else {
				current.setNext(nextNode);
			}
		
			previous=current;
			}
			size--;
			next=true;
			modCount++;
			iterModCount++;
		}
	
	}
}
