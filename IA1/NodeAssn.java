public class NodeAssn extends NodeStmt {

    private String id;
    private NodeExpr expr;
/**
 * The constructor of this class. Create a assign block according to the grammar
 * @param id
 * @param expr
 */
    public NodeAssn(String id, NodeExpr expr) {
	this.id=id;
	this.expr=expr;
    }

    /** This method follows the grammar below
     * term  : fact mulop term | fact
     * @throws Eval Exceptions
     * @param env
     * @return the expression evaluation
     */
    public double eval(Environment env) throws EvalException {
	return env.put(id,expr.eval(env));
    }

}
