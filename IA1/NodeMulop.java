public class NodeMulop extends Node {

    private String mulop;
/**
 * Constructor of this class 
 * @param pos
 * @param mulop
 */
    public NodeMulop(int pos, String mulop) {
	this.pos=pos;
	this.mulop=mulop;
    }
/**
 * This method have mul/divide operations
 * @param d
 * @param e
 * @return
 * @throws EvalException if division by 0
 */
    public double op(double d, double e) throws EvalException {
	if (mulop.equals("*"))
	    return d*e;
	if (mulop.equals("/")) {
		if(e==0) {
			throw new EvalException(pos,"invalid divison");
		}
	return d/e;
	}
	throw new EvalException(pos,"bogus mulop: "+mulop);
    }

}
