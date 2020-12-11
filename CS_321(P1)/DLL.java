import java.util.NoSuchElementException;

/**The Double Linked List 
 *
 */
public class DLL<T> {
	private DLLNode<T> head, tail;
	private int size;
	/** Creates an empty list */
	public DLL() {
		head = tail = null;
		size = 0;
	}
	/**
	 * To add the element to the front of the list
	 */

	public void addToFront(T element) {

		DLLNode<T> newNode= new DLLNode<T>(element);
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
	}

	/**
	 * To remove the last element
	 * @return the removed element
	 */
	public T removeLast() {
		if (isEmpty()) {
			throw new IllegalStateException();
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
		return retVal;
	}
	/**
	 * To remove the targeted element in the list 
	 */
	public void remove(T element) {
		DLLNode<T> current= head;
		while(current!=null && !current.getElement().equals(element)) {
			current=current.getNext();
		}
		if (current==null) {
			throw new NoSuchElementException();
		}
		if (current==head) {
			if (head==tail) {
				head=tail=null;
			}
			else{
				head=head.getNext();
				head.setPrevious(null);
			}
		}
		else if(current==tail) {
			if(tail==head) {
				head=tail=null;
			}
			else {
				tail= tail.getPrevious();
				tail.setNext(null);
			}
		}
		else {
			current.getPrevious().setNext(current.getNext());
			current.getNext().setPrevious(current.getPrevious());
		}
		size--;
	}

	/**
	 * To find the index of the element
	 * @return the index of the target, or -1 if it was not there	
	 */
	public int indexOf(T element) {
		int index=0;
		DLLNode<T> current = head;

		while(current!=null&&!current.getElement().equals(element)) {
			index++;
			current=current.getNext();
		}
		if (current==null) {
			index=-1;
		}
		return index;

	}
	/**
	 * To clear all the data in the list
	 */
	public void clear() {
		DLLNode<T> current=head;
		while(head!=null) {
			removeLast();
		}
	}
	/**
	 * To check if the list contains the target element
	 * @return true if it's in there; false if it is not in there
	 */
	public boolean contains(T target) {
		return (indexOf(target)!=-1);
	}
	/**
	 * To check if the list is empty
	 * @return true if it is empty and false if it's not empty
	 */
	public boolean isEmpty() {
		return (size==0);
	}
	/**
	 * To get the list size
	 * @return the size of the list
	 */

	public int size() {
		return size;
	}

}
