import java.util.Comparator;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Mergesort algorithm.
 *
 * @author CS221
 */
public class Sort1
{	
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. 
	 * As configured, uses WrappedDLL. Must be changed if using 
	 * your own IUDoubleLinkedList class. 
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() 
	{
		return new WrappedDLL<T>(); //TODO: replace with your IUDoubleLinkedList for extra-credit
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @see IndexedUnsortedList 
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) 
	{
		quicksort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnsortedList 
	 */
	public static <T> void sort(IndexedUnsortedList <T> list, Comparator<T> c) 
	{
		quicksort(list, c);
	}

	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface, 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 */
	private static <T extends Comparable<T>> void quicksort(IndexedUnsortedList<T> list)
	{
		// TODO: Implement recursive mergesort algorithm 
		if(list.size()<2) {
			return;
		}
		T pivot= list.first();
		IndexedUnsortedList<T> left= newList();
		IndexedUnsortedList<T> right= newList();
		while(!list.isEmpty()) {
			if(pivot.compareTo(list.first())<0) {
				left.add(list.removeFirst());
			}
			else {
				right.add(list.removeFirst());
			}
		}
		//recursive
		quicksort(left);
		quicksort(right);
		// assemble
		while(!left.isEmpty()) {
			list.add(left.removeFirst());
		}
		list.add(pivot);
		while(!right.isEmpty()) {
			list.add(left.removeFirst());
		}
	}

	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list*
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void quicksort(IndexedUnsortedList<T> list, Comparator<T> c)
	{
		// TODO: Implement recursive mergesort algorithm using Comparator

		if(list.size()<2) {
			return;
		}
		T pivot= list.first();
		IndexedUnsortedList<T> left= newList();
		IndexedUnsortedList<T> right= newList();
		while(!list.isEmpty()) {
			if(c.compare(pivot,list.first())<0) {
				left.add(list.removeFirst());
			}
			else {
				right.add(list.removeFirst());
			}
		}
		//recursive
		quicksort(left,c);
		quicksort(right,c);
		// assemble
		while(!left.isEmpty()) {
			list.add(left.removeFirst());
		}
		list.add(pivot);
		while(!right.isEmpty()) {
			list.add(left.removeFirst());
		}
	}




}
