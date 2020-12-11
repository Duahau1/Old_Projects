/**
 * Exception to be thrown when a syntax error is encountered
 * @author jbuffenb
 */
public class SyntaxException extends Exception {

	private int pos;
	private Token expected;
	private Token found;
	
	/**
	 * Constructor for SyntaxException
	 * @param pos the position of the exception in the program
	 * @param expeceted what was expected
	 * @param found what was found instead of expected
	 */
	public SyntaxException(int pos, Token expected, Token found) {
		this.pos=pos;
		this.expected=expected;
		this.found=found;
	}

	public String toString() {
		return "syntax error"+", pos="+pos+", expected="+expected+", found="+found;
	}
}
