import java.util.HashMap;
import java.util.Scanner;

public class Environment {
	
	HashMap<String, Double> maps;
	Scanner scan;
	
	/**
	 * Constructor of this class. Store the variables into the hashmap with its  double value
	 */
	public Environment() {
		this.maps = new HashMap<String, Double>();
		this.scan = new Scanner(System.in);
	}
	
	/**
	 * Check if the environment has defined a symbol
	 * @param var The symbol to check
	 * @return true if it contains the symbol, false if not
	 */
	public boolean contains(String var) {
		return maps.containsKey(var);
	}
	
	/**
	 * Put a variable with its value into hash map
	 * @param variable
	 * @param value
	 * @return the value of the variable
	 */
	public double put(String var, double val) {
		this.maps.put(var, (Double) val);
		return val; 
	}

	/**
	 * Get variable form a position in the program
	 * @param position
	 * @param variable
	 * @return the variable 
	 * @throws EvalException
	 */
	public double get(int position, String variable) throws EvalException {
		if(maps.containsKey(variable)){
			return maps.get(variable);
		}
		throw new EvalException(position, "Undefined variable");
	}

	/**
	 * This method scans the next line
	 * @return the line scanned in
	 */
	public String nextLine() {
		return this.scan.nextLine();
	}
	/**
	 * This method closes the scanner	
	 */
	public void close() {
		this.scan.close();
	}
	/**
	 * This method scans the next double
	 * @return the number scanned in
	 */
	public double nextDouble() {
		return this.scan.nextDouble();
	}
}
