public class NodeFactFact extends NodeFact {

    private NodeFact fact;

    public NodeFactFact(NodeFact fact) {
	this.fact = fact;
    }

    public double eval(Environment env) throws EvalException {
	return 0 - fact.eval(env);
    }

}
