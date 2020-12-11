import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

// This is the main class/method for the interpreter.
// Each command-line argument is a complete program,
// which is scanned, parsed, and evaluated.
// All evaluations share the same environment,
// so they can share variables.

public class Interpreter {
/**
 * The main method
 * @param args
 */
	
    public static void main(String[] args) {
    Environment env=new Environment();
	Parser parser=new Parser();
	for (String stmt: args)
	    try {
	    stmt=stmt.trim();
	    parser.parse(stmt).eval(env);
	    } catch (SyntaxException e) {
		System.err.println(e);
	    } catch (EvalException e) {
		System.err.println(e);
	    }
   env.close();
    }
   /**
    * This method format the number by setting the minimum fraction digits
    * @param number
    * @return the formatted number
    */
    public static String format(Number number) {
        NumberFormat numFormat = DecimalFormat.getInstance();
        numFormat.setRoundingMode(RoundingMode.FLOOR);
        numFormat.setMinimumFractionDigits(0);
        numFormat.setMaximumFractionDigits(2);
        return numFormat.format(number);
    }

}
