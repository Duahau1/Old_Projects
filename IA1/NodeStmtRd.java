import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.NumberFormatException;

public class NodeStmtRd extends NodeStmt {

    private String id;
/**
 * Constructor of this class
 * @param id
 */
    public NodeStmtRd(String id) {
	this.id = id;
    }
/**
 * This method reads in data
 * @return the things you read in
 * @param environment
 */
    public double eval(Environment env) throws EvalException {
	double num = 0;
	while (true) {
		try {
			System.out.println("Enter a double:");
			String in = env.nextLine();
			num = Double.parseDouble(in);
			env.put(id, num);
			break;
		} catch (NumberFormatException e) {
			System.out.println("Error:Not a double");
		}
	}
	return num;
    }
}