public class NodeBoolExpr extends Node {

    private NodeExpr expr1;
    private NodeRelop relop;
    private NodeExpr expr2;
/**
 * Constructor of this class
 * @param expr1
 * @param relop
 * @param expr2
 */
    public NodeBoolExpr(NodeExpr expr1, NodeRelop relop, NodeExpr expr2) {
	this.expr1 = expr1;
	this.relop = relop;
	this.expr2 = expr2;
    }
/**
 * This will send the comparision to the relop node to evaluate
 * @param environemnt
 * @return 1.0/0.0 depends on the result of the comparision
 */
    public double eval(Environment env) throws EvalException {
	return relop.op(expr1.eval(env), expr2.eval(env));
    }
}