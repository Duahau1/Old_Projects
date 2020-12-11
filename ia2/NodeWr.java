public class NodeWr extends NodeStmt {

    private NodeExpr expr;

    public NodeWr(NodeExpr expr) {
	this.expr=expr;
    }

    public double eval(Environment env) throws EvalException {
	double out = expr.eval(env);
	System.out.println(out);
	return out;
    }
}
