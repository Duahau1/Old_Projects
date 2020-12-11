public abstract class NodeStmt extends Node {
    public abstract double eval(Environment env) throws EvalException;
}
