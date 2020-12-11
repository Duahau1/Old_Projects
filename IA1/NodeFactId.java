public class NodeFactId extends NodeFact {

    private String id;
/**
 * Constructor of this class
 * @param pos
 * @param id
 */
    public NodeFactId(int pos, String id) {
	this.pos=pos;
	this.id=id;
    }
/**
 * The value of the id
 * @param env
 * @return double value
 * @throws EvalException if have unrecognized id
 */
    public double eval(Environment env) throws EvalException {
    	if (!env.contains(id)) {
    		throw new EvalException(pos, "unrecognized identifier: " + id);
    	}
    	return env.get(pos,id);
    }

}
