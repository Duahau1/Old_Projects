public class NodeFactExpr extends NodeFact {

    private NodeExpr expr;
/**
 * Constructor of this class which is one part of fact
 * @param expr
 */
    public NodeFactExpr(NodeExpr expr) {
	this.expr=expr;
    }
/**
 * @param env
 * @return double value 
 */
    
    public double eval(Environment env) throws EvalException {
	return expr.eval(env);
    }

}
