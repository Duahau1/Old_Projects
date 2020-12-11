public class NodeWhile extends NodeStmt {

	private NodeBoolExpr boolexpr;
    private NodeStmt stmt;

    public NodeWhile(NodeBoolExpr boolexpr, NodeStmt stmt) {
	this.boolexpr = boolexpr;
	this.stmt = stmt;
    }

    public double eval(Environment env) throws EvalException {
	while (boolexpr.eval(env) != 0) {
		stmt.eval(env);
	}
	return 0;
    }

}
