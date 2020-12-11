import java.util.NoSuchElementException;

public class Stack<T> implements IStack<T> {

	private final int CAPACITY=50;
	private int top;
	private T[] stack;
	/*
	 * constructor to implement a new stack
	 * 
	 */
	public Stack() {
		
		stack= (T[])(new Object[CAPACITY]);
		top =0;
	}

	@Override
	public void push(T value) {
		// TODO Auto-generated method stub
		top++;
		stack[top]=value;
		
	}

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal= stack[top];
		
		top--;
		return retVal;
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return stack[top];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return top;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (top==0);
	}

}
