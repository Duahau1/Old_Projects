public class NodeFactNum extends NodeFact {

    private String num;
/**
 * Constructor of this class
 * @param num
 */
    public NodeFactNum(String num) {
	this.num=num;
    }
/**
 * This method get the number of the program
 * @param env
 * @return double
 */
    public double eval(Environment env) throws EvalException {
	return Double.parseDouble(num);
    }
   
}
