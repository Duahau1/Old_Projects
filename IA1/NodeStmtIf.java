public class NodeStmtIf extends NodeStmt {

	private NodeBoolExpr boolexpr;
	private NodeStmt ifstmt;
	private NodeStmt elsestmt;
/**
 * Constructor of this class
 * @param boolexpr
 * @param ifstmt
 * @param elsestmt
 */
	public NodeStmtIf(NodeBoolExpr boolexpr, NodeStmt ifstmt, NodeStmt elsestmt) {
		this.boolexpr = boolexpr;
		this.ifstmt = ifstmt;
		this.elsestmt = elsestmt;
	}
/**
 * This method assess the ifstatement to see of we need if else block or just if block
 * @return double
 * @param environment
 */
	public double eval(Environment env) throws EvalException {
		double cond = this.boolexpr.eval(env);
		if (cond == 0.0) {
			if (elsestmt != null) {
				return elsestmt.eval(env);
			} 
			else {
				return 0;
			}
		}
		else {
			return ifstmt.eval(env);
		}
	}
}