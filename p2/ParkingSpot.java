import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.Scanner;



/**
 * Represents a parking spot in a city.
 *
 * To create a new parking spot, you will use the Constructor. To print the
 * details of the spot, you just need to print the object.
 * <pre><code>
 * ParkingSpot spot = new ParkingSpot("Beacon St.", 1, 85);
 * System.out.println(spot);
 * </code></pre>
 *
 * @author CS121 Instructors
 */
public class ParkingSpot
{
	private String street;
	private int locationX;
	private int locationY;
	private boolean available;
	private double costPerInterval;

	/**
	 * The minimum time interval which can be paid for.
	 */
	public final int INTERVAL = 10; // minutes

	/**
	 * The default costPerInterval for each INTERVAL minutes
	 */
	public final double DEFAULT_COST = 0.25;

	/**
	 * Constructor: creates a parking spot object with specified values.
	 * @param street is the street on which the parking spot is located
	 * @param locationX is the x (E-W) coordinate
	 * @param locationY is the Y (N-S) coordinate
	 */
	public ParkingSpot(String street, int locationX, int locationY)
	{
		this.street = street;
		this.locationX = locationX;
		this.locationY = locationY;
		available = true;
		costPerInterval = DEFAULT_COST;
	}

	/**
	 * Checks whether the parking spot is available
	 * @return true if spot is available
	 */
	public boolean isAvailable()
	{
		return available;
	}

	/**
	 * Returns the cost for one time interval
	 * @return the costPerInterval
	 */
	public double getCostPerInterval()
	{
		return costPerInterval;
	}

	/**
	 * Sets the cost for one time interval
	 * @param cost the cost for one time interval.
	 */
	public void setCostPerInterval(double cost)
	{
		this.costPerInterval = cost;
	}

	/**
	 * Sets the space to be available or not available
	 * @param available true is the parking spot is available, false otherwise
	 */
	public void setAvailable(boolean available)
	{
		this.available = available;
	}

	/**
	 * Get the street on which the parking space is located
	 * @return the street
	 */
	public String getStreet()
	{
		return street;
	}

	/**
	 * Get the east-west coordinate of the parking spot
	 * @return the locationX
	 */
	public int getLocationX()
	{
		return locationX;
	}

	/**
	 * Get the north-south coordinate of the parking spot
	 * @return the locationY
	 */
	public int getLocationY()
	{
		return locationY;
	}

	/* Returns a String representation of the parking spot
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "[street = " + street + ", locationX = " + locationX
				+ ", locationY = " + locationY + ", available = " + available
				+ ", costPerInterval = " + costPerInterval+ "]";
	}
	public static void main (String[]arg) {
		Scanner kbd= new Scanner (System.in);
		Random rand = new Random();
		int carX = rand.nextInt(100);
		int carY= rand.nextInt(100);
		System.out.println("Position of vehicle : "+ " x= " + carX + " y= " + carY);
		System.out.print("Enter your time : ");
		double time= kbd.nextDouble();
		
		NumberFormat currencyFmt =NumberFormat.getCurrencyInstance();
		
		// Spot1
		int x =rand.nextInt(100);
		int y =rand.nextInt(100);
		ParkingSpot spot1 = new ParkingSpot("W.Boise",x,y);
		double fee1= spot1.costPerInterval;
		fee1= 0.25;
		System.out.println("Spot1 "+ spot1.toString());
		int Distance1= x-carX + y- carY;
		System.out.println("Distance:"+Math.abs(Distance1));
		double totalCost1;
		double c = Math.ceil(time/10);
		totalCost1=c*fee1;
		
		
		System.out.println("Total Cost : " + currencyFmt.format(totalCost1));		
				
		// Spot2	
		int z =rand.nextInt(100);
		int w =rand.nextInt(100);
		ParkingSpot  spot2 = new ParkingSpot("E.Boise",z,w);
		double fee2= spot2.costPerInterval;
		fee2=0.25 ;
		System.out.println("Spot2 "+spot2.toString());
		int Distance2= z-carX +w- carY;
		double totalCost2;
		totalCost2=c*fee2;
		
		System.out.println("Distance:"+Math.abs(Distance2));
		System.out.println("Total Cost : " + currencyFmt.format(totalCost2));	
		
		// Spot3	
		int n =rand.nextInt(100);
		int m =rand.nextInt(100);
		ParkingSpot spot3 = new ParkingSpot("E.Chrisway",n,m);
		spot3.setCostPerInterval(0.3);
		System.out.println("Spot3 "+spot3.toString());
		int Distance3= n-carX +m- carY;
		
		double totalCost3;
		totalCost3=c*0.3;
		
		System.out.println("Distance:"+Math.abs(Distance3));
		System.out.println("Total Cost : " + currencyFmt.format(totalCost3));	
		
		
		// Spot4
		int a =rand.nextInt(100);
		int b =rand.nextInt(100);
		ParkingSpot spot4 = new ParkingSpot("W.Chrisway",a,b);
		spot4.setCostPerInterval(0.3);
		System.out.println("Spot4 "+spot4.toString());
		int Distance4= a-carX +b- carY;
		
		double totalCost4;
		totalCost4=c*0.3;
		System.out.println("Distance:"+Math.abs(Distance4));
		System.out.println("Total Cost : " + currencyFmt.format(totalCost4));	
		
		if(Math.abs(Distance1)<Math.abs(Distance2) && Math.abs(Distance1)<Math.abs(Distance3) && Math.abs(Distance1)<Math.abs(Distance4)) {
			System.out.println("Distance to the closet spot is "+Math.abs(Distance1));
			System.out.println("Closet Spot: "+ spot1.toString());}
		else if (Math.abs(Distance2)<Math.abs(Distance1) && Math.abs(Distance2)<Math.abs(Distance3) && Math.abs(Distance2)<Math.abs(Distance4)) {
			System.out.println("Distance to the closet spot is "+Math.abs(Distance2));
			System.out.println("Closet Spot: "+ spot2.toString());}
		else if (Math.abs(Distance3)<Math.abs(Distance1) && Math.abs(Distance3)<Math.abs(Distance2) && Math.abs(Distance3)<Math.abs(Distance4)){
			System.out.println("Distance to the closet spot is "+Math.abs(Distance3));
			System.out.println("Closet Spot: "+ spot3.toString());}
		else if (Math.abs(Distance4)<Math.abs(Distance1) && Math.abs(Distance4)<Math.abs(Distance2) && Math.abs(Distance4)<Math.abs(Distance3)) {
			System.out.println("Distance to the closet spot is "+Math.abs(Distance4));
			System.out.println("Closet Spot: "+ spot4.toString());}


		
		
		
		
		
	}

}

