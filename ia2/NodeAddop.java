public class NodeAddop extends Node {

    private String addop;
    /**
     * The constructor of this class. 
     * @param id
     * @param expr
     */
    public NodeAddop(int pos, String addop) {
	this.pos=pos;
	this.addop=addop;
    }
/**
 * This method add /subtract 2 numbers
 * @param d
 * @param e
 * @return
 * @throws EvalException
 */
    public double op(double d, double e) throws EvalException {
	if (addop.equals("+"))
	    return d+e;
	if (addop.equals("-"))
	    return d-e;
	throw new EvalException(pos,"bogus addop: "+addop);
    }

}
