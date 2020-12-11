public class NodeExpr extends Node {

    private NodeTerm term;
    private NodeAddop addop;
    private NodeExpr expr;
/**
 * Constructor of this class
 * @param term
 * @param addop
 * @param expr
 */
    public NodeExpr(NodeTerm term, NodeAddop addop, NodeExpr expr) {
	this.term=term;
	this.addop=addop;
	this.expr=expr;
    }
/**
 * This method append the expression
 * @param expr
 */
    
    public void append(NodeExpr expr) {
	if (this.expr==null) {
	    this.addop=expr.addop;
	    this.expr=expr;
	    expr.addop=null;
	} else
	    this.expr.append(expr);
    }
/**
 * This method follows the grammar 
 * expr  : term addop expr
      | term
 * @return a double value
 * @param env
 */
    public double eval(Environment env) throws EvalException {
	return expr==null
	    ? term.eval(env)
	    : addop.op(expr.eval(env),term.eval(env));
    }

}
