public class NodeBoolExpr extends Node {

    private NodeExpr expr1;
    private NodeRelop relop;
    private NodeExpr expr2;

    public NodeBoolExpr(NodeExpr expr1, NodeRelop relop, NodeExpr expr2) {
	this.expr1 = expr1;
	this.relop = relop;
	this.expr2 = expr2;
    }

    public double eval(Environment env) throws EvalException {
	return relop.op(expr1.eval(env), expr2.eval(env));
    }
}
