public class NodeUnaryMinus extends NodeFact {

    private NodeFact fact;
/**
 * Constructor of this class
 * @param fact
 */
    public NodeUnaryMinus(NodeFact fact) {
	this.fact = fact;
    }
/**
 * 
 * Recursive call from parseFact() will return the value of fact equals to one of the three factexpr/num/id
 * @param env
 * @return 0- fact.eval(env)
 * 
 */
    public double eval(Environment env) throws EvalException {
    	return 0 - fact.eval(env);
    }

}