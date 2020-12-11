public class SyntaxException extends Exception {

    private int pos;
    private Token expected;
    private Token found;
/**
 * Constructor of this class 
 * @param position, the expected token and the the token found
 * 
 */
    public SyntaxException(int pos, Token expected, Token found) {
	this.pos=pos;
	this.expected=expected;
	this.found=found;
    }
/**
 * This method provides the message for the error
 * @return a string of error message due to syntax error
 */
    public String toString() {
	return "syntax error"
	    +", pos="+pos
	    +", expected="+expected
	    +", found="+found;
    }

}
