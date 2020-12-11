
public class NodeStmtWr extends NodeStmt {
	
	private NodeExpr expr;
/**
 * Contructor of this class
 * @param expr
 */
    public NodeStmtWr(NodeExpr expr) {
	this.expr=expr;
    }
/**
 * This method write the output like System.out.println
 * @param environment
 * @return the things you want to write
 */
    public double eval(Environment env) throws EvalException {
	double out = expr.eval(env);
	System.out.println(out);
	return out;
    }
}
