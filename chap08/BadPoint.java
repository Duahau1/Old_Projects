import java.awt.Point;

/**
 * Class that contains shadow variables for x and y inherited from Point to demonstrate
 * that inherited methods are referring to inherited x and y while new methods are using
 * new x and y.
 * 
 * @author mvail
 */
public class BadPoint extends Point {

	private int x; //BAD - creates a shadow variable of Point's x - line should be deleted
	private int y; //BAD - creates a shadow variable of Point's y - line should be deleted
	private int z;
	
	public BadPoint(int x, int y, int z){
//		super(x, y);	//correct way to initialize x and y, by passing them to Point's constructor
		this.x = x;	//BAD - value of this.x is different than value of super.x
		this.y = y;	//BAD - value of this.y is different than value of super.y
		this.z = z;
	}
	
	public String toString() {
		String returnStr = "x: " + x + ", y: " + y + ", z: " + z;	//shows what this class thinks x and y are
		returnStr += "\n" + super.toString();	//shows what Point thinks x and y are
		return returnStr;
		
	}
	
}
