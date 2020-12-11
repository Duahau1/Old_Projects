public class NodeStmtWhile extends NodeStmt {

	private NodeBoolExpr boolexpr;
    private NodeStmt stmt;
/**
 * Constructor of this class
 * @param boolexpr
 * @param stmt
 */
    public NodeStmtWhile(NodeBoolExpr boolexpr, NodeStmt stmt) {
	this.boolexpr = boolexpr;
	this.stmt = stmt;
    }
/**
 * This method evaluates the while loop
 * @param environment
 * @return double
 */
    public double eval(Environment env) throws EvalException {
	while (boolexpr.eval(env) != 0) {
		stmt.eval(env);
	}
	return 0;
    }

}