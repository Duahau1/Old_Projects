
public class NodeRelop extends Node {
	private String boolop;
/**
 * Constructor of this class
 * @param pos
 * @param relop
 */
    public NodeRelop(int pos, String relop) {
	this.pos=pos;
	this.boolop = relop;
    }
/**
 * This method compares 2 numbers
 * @param op1
 * @param op2
 * @return 1.0 or 0.0 depends on the result of the operation
 * @throws EvalException
 */
    public double op(double op1, double op2) throws EvalException {
	switch (boolop) {
		case "<":
			if(op1<op2) {
				return 1.0;
			}
			else {
				return 0.0;
			}
		case "<=":
			if(op1<=op2) {
				return 1.0;
			}
			else {
				return 0.0;
			}
		case ">":
			if(op1>op2) {
				return 1.0;
			}
			else {
				return 0.0;
			}
		case ">=":
			if(op1>=op2) {
				return 1.0;
			}
			else {
				return 0.0;
			}
		case "<>":
			if(op1!=op2) {
				return 1.0;
			}
			else {
				return 0.0;
			}
		case "==":
			if(op1==op2) {
				return 1.0;
			}
			else {
				return 0.0;
			}
		default:
			throw new EvalException(pos,"bogus relop: "+boolop);
	}
    }
}
