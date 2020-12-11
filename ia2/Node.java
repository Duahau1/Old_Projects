
public abstract class Node {

	protected int pos=0;

	/**
	 * This is an abstract method
	 * @param env
	 * @return double value
	 * @throws EvalException
	 */
	public double eval(Environment env) throws EvalException {
		throw new EvalException(pos,"cannot eval() node!");
	}

}
