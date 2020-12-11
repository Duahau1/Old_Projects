/**
 * Excption class for eval() in the Node class.
 * @author jbuffenb
 */
public class EvalException extends Exception {

	private int pos;
	private String msg;

	/**
	 * Constructor for EvalException
	 * @param pos the starting position of the erroneous symbol
	 * @param msg the message to be displayed when the exception is thrown
	 */
	public EvalException(int pos, String msg) {
		this.pos=pos;
		this.msg=msg;
	}

	public String toString() {
		return "eval error"+", pos="+pos+", "+msg;
	}
}
