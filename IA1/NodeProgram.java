public class NodeProgram extends Node {

    private NodeBlock block;
/**
 * Constructor of this class
 * @param block
 */
	public NodeProgram(NodeBlock block) {
		this.block = block;
	}
/**
 * This method let the block evaluates the environment
 * @return double
 * @param environment
 */
    public double eval(Environment env) throws EvalException {
	return this.block.eval(env);
    }
}