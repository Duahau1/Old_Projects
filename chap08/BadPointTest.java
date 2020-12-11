
/**
 * Demonstrates why shadow variables are a bad thing.
 * 
 * @author mvail
 */
public class BadPointTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BadPoint p = new BadPoint(1, 2, 3);
		
		System.out.println(p);
		
		System.out.println(p.getLocation()); //an inherited Point method

	}

}
