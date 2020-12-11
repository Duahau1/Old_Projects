import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T>{
	private LinearNode<T> head, tail;
	private int size;
	private int modCount;

	/** Creates an empty list */
	public IUDoubleLinkedList() {
		head = tail = null;
		size = 0;
		modCount = 0;
	}

	@Override
	public void addToFront(T element) {

		LinearNode<T> newNode= new LinearNode<T>(element);
		if(isEmpty()) {
			head=tail=newNode;
			newNode.setPrevious(null);
			newNode.setNext(null);
		}
		else {
			newNode.setNext(head);
			newNode.setPrevious(null);
			head.setPrevious(newNode);
			head=newNode;
		}
		size++;
		modCount++;
	//ListIteratot<T> list = ListIterator();
		
	//list.add(element);
	
	
	}

	@Override
	public void addToRear(T element) {
		LinearNode<T> newNode= new LinearNode<T>(element);
		if (isEmpty()) {
			head=tail=newNode;
		}
		else {
			newNode.setNext(null);
			newNode.setPrevious(tail);
			tail.setNext(newNode);
			tail=newNode;
		}
		size++;
		modCount++;
	}

	@Override
	public void add(T element) {
		addToRear(element);
	}

	@Override
	public void addAfter(T element, T target) {
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
			newNode.setPrevious(tail);
			tail=newNode;
		} 
		else {
			newNode.setNext(targetNode.getNext());
			targetNode.getNext().setPrevious(newNode);
		}
		newNode.setPrevious(targetNode);
		targetNode.setNext(newNode);
		size++;
		modCount++;

	}

	@Override
	public void add(int index, T element) {
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
		else if (index==size()) {
			tail.setNext(newNode);
			newNode.setPrevious(tail);
			tail=newNode;
		}
		else if(index==0) {
			head.setPrevious(newNode);
			newNode.setNext(head);
			head=newNode;
		}
		else {
			targetindex.getPrevious().setNext(newNode);
			newNode.setPrevious(targetindex.getPrevious());
			targetindex.setPrevious(newNode);
			newNode.setNext(targetindex);
		}
		size++;
		modCount++;
	}

	@Override
	public T removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal = head.getElement();
		head =head.getNext();
		if (head==null) {
			tail=null;
		}
		else {
			head.setPrevious(null);
		}
		size--;
		modCount++;
		return retVal;
	}

	@Override
	public T removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		T retVal = tail.getElement();
		tail= tail.getPrevious();

		if (tail==null) {
			head= null;
		}
		else {
			tail.setNext(null);
		}
		size--;
		modCount++;
		return retVal;
	}

	@Override
	public T remove(T element) {
		LinearNode<T> targetNode= head;
		while(targetNode!=null && !targetNode.getElement().equals(element)) {
			targetNode=targetNode.getNext();	
		}
		if (targetNode==null) {
			throw new NoSuchElementException();
		}
		T retVal= targetNode.getElement();

		if (targetNode==head) {
			head =head.getNext();
			if (head==null) {
				tail=null;
			}
			else {
				head.setPrevious(null);
			}
		}

		else if (targetNode==tail) {
			tail= tail.getPrevious();
			if (tail==null) {
				head= null;
			}
			else {
				tail.setNext(null);
			}
		}
		else {
			targetNode.getPrevious().setNext(targetNode.getNext());
			targetNode.getNext().setPrevious(targetNode.getPrevious());
		}
		size--;
		modCount++;
		return retVal;
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		if(index<0||index>=size()) {
			throw new IndexOutOfBoundsException();
		}
		int number=0;
		LinearNode<T> current = head;

		while(number!=index) {
			current=current.getNext();
			number++;
		}
		T retVal=current.getElement();

		if (size()==1) {
			head=tail=null;
		}
		else if (current==head) {
			head =head.getNext();
			if (head==null) {
				tail=null;
			}
			else {
				head.setPrevious(null);
			}

		}
		else if (current==tail) {

			tail= tail.getPrevious();
			if (tail==null) {
				head= null;
			}
			else {
				tail.setNext(null);
			}

		}
		else {

			current.getPrevious().setNext(current.getNext());
			current.getNext().setPrevious(current.getPrevious());
		}
		size--;
		modCount++;
		return retVal;	

	}

	@Override
	public void set(int index, T element) {
		// TODO Auto-generated method stub
		if(index<0||index>=size()) {
			throw new IndexOutOfBoundsException();
		}
		LinearNode<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		current.setElement(element);
		modCount++;
	}

	@Override
	public T get(int index) {
		if(index<0||index>=size()) {
			throw new IndexOutOfBoundsException();
		}
		LinearNode<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}

		return current.getElement();
	}

	@Override
	public int indexOf(T element) {
		int index=0;
		LinearNode<T> current = head;

		while(current!=null&&!current.getElement().equals(element)) {
			index++;
			current=current.getNext();
		}
		if (current==null) {
			index=-1;
		}
		return index;

	}

	@Override
	public T first() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return (indexOf(target)!=-1);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size==0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		LinearNode<T> current= head;
		while(current !=null) {
			str.append(current.getElement().toString());
			str.append(", ");
			current=current.getNext();
		}
		if(!isEmpty()) {
			str.delete(str.length()-2, str.length());
		}
		str.append("]");
		return str.toString();
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new DLLIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return new DLLIterator();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		// TODO Auto-generated method stub
		return new DLLIterator(startingIndex);
	}
	private class DLLIterator implements ListIterator<T> {
		private int nextindex;
		private LinearNode<T> nextNode;
		private LinearNode<T> lastReturnNode;
		private LinearNode<T> node;
		private int iterModCount, previous;

		public DLLIterator() {
			this(0);

		}
		public DLLIterator (int startingIndex) {
			if (startingIndex<0||startingIndex>size) {
				throw new IndexOutOfBoundsException();
			}
			nextNode = head;
			for (int i=0; i<startingIndex;i++) {
				nextNode=nextNode.getNext();
			}
			nextindex=startingIndex;
			iterModCount = modCount;
			lastReturnNode = null;
			//node = null;
		}



		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if (iterModCount!=modCount) {
				throw new ConcurrentModificationException();
			}

			return (nextNode!=null);

		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T retVal = nextNode.getElement();
			lastReturnNode = nextNode;
			nextNode = nextNode.getNext();
			nextindex++;
			return retVal;

		}
		public void remove() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (lastReturnNode==null) {
				throw new IllegalStateException();
			}
			if (head==tail) {
				head=tail=null;
			}
			//			if (lastReturnNode == head) {
			//				head = head.getNext(); 
			//			} else {
			//				lastReturnNode.getPrevious().setNext(lastReturnNode.getNext());
			//			}
			//
			//
			//			if (lastReturnNode == tail) {
			//				tail = tail.getPrevious(); 
			//
			//			} else {
			//				lastReturnNode.getNext().setPrevious(lastReturnNode.getPrevious()); 
			//			}
			else if( lastReturnNode==head) {//remove head
				head=head.getNext();
				head.setPrevious(null);
			}
			else if(lastReturnNode==tail) {// remove tail
				tail=tail.getPrevious();
				tail.setNext(null);
			}
			else {//general case
				lastReturnNode.getPrevious().setNext(lastReturnNode.getNext());
				lastReturnNode.getNext().setPrevious(lastReturnNode.getPrevious()); 
			}
			// do i need to update nextnode
			if (nextNode ==lastReturnNode) {// last mmove was previous
				nextNode=nextNode.getNext();
			}
			else {//last move was next
				nextindex--;
			}
			lastReturnNode = null;
			size--;
			//nextindex--;
			modCount++;
			iterModCount++;
		}
		@Override
		public void add(T arg0) {
			// TODO Auto-generated method stub
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			node = new LinearNode<T>(arg0);
			//Checks for an empty list.
		
			if (isEmpty()) {
				head = tail = node;
				//Checks for the end of the list.
//			} else if (nextNode==null) { 
//				node.setPrevious(tail);
//				tail.setNext(node);
//				tail = node;
//
//				//Checks for the beginning of the list.
//			} else if (nextNode== head) {
//				node.setNext(head);
//				head.setPrevious(node);
//				head = node;
//				node.setPrevious(null);
//				
			} else if (nextindex==size) { 
				node.setPrevious(tail);
				tail.setNext(node);
				tail = node;

				//Checks for the beginning of the list.
			} else if (nextindex== 0) {
				node.setNext(head);
				head.setPrevious(node);
				head = node;
				node.setPrevious(null);
				//General case for all other nodes.
			} else { 

				node.setNext(nextNode);
				node.setPrevious(nextNode.getPrevious());
				nextNode.getPrevious().setNext(node);
				nextNode.setPrevious(node);
			}	
			
			lastReturnNode = null;
			size++;
			nextindex++;
			modCount++;
			iterModCount++;


		}
		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			if (iterModCount!=modCount) {
				throw new ConcurrentModificationException();
			}

			return (nextNode!=head);
		}
		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (lastReturnNode==tail) {
				return size;
			}
			else {
				return nextindex;
			}

		}

		@Override
		public T previous() {
			// TODO Auto-generated method stub
			if(! hasPrevious()) {
				throw new NoSuchElementException();
			}
			if (nextNode==null) {
				nextNode=tail;
			}			

			else {
				nextNode=nextNode.getPrevious();
			}
			lastReturnNode=nextNode;
			nextindex--;
			return lastReturnNode.getElement();

		}
		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (lastReturnNode==head) {
				return -1;
			}
			else {
				return nextindex-1;
			}


		}
		@Override
		public void set(T arg0) {
			// TODO Auto-generated method stub
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (lastReturnNode == null) {
				throw new IllegalStateException();
			}
			lastReturnNode.setElement(arg0);
			modCount++;
			iterModCount++;

		}


	}
}