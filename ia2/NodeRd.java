import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.NumberFormatException;

public class NodeRd extends NodeStmt {

    private String id;

    public NodeRd(String id) {
	this.id = id;
    }

    public double eval(Environment env) throws EvalException {
	double d = 0;
	while (true) {
		try {
			System.out.println("Enter a double");
			String in = env.nextLine();
			d = Double.parseDouble(in);
			env.put(id, d);
			break;
		} catch (NumberFormatException e) {
			System.out.println("Invalid double. Try again.");
		}
	}
	return d;
    }
}
