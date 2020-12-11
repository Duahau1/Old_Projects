public class NodeFactId extends NodeFact {

    private String id;

    public NodeFactId(int pos, String id) {
	this.pos=pos;
	this.id=id;
    }

    public double eval(Environment env) throws EvalException {
	if (!env.contains(id)) {
		throw new EvalException(pos, "unrecognized identifier: " + id);
	}
	return env.get(pos,id);
    }

}
