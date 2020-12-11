package p1;
import java.lang.Object;
import java.lang.String;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class ParkingSpot {
// Default constructor
public ParkingSpot() {
}
private String streetName;
private int locationX;
private int locationY;
private String thestreet;
private boolean available;
// Constructor
public ParkingSpot(String streetName, int locationX, int locationY) {
// TODO Auto-generated constructor stub
	}
public boolean isAvailable() {
	return available;
}
public int getlocationX() {
return locationX;
}
public int getlocationT() {
return locationY;
}
public String getStreet(){
return thestreet;
}
public void setAvailale() {
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





public static void main (String []arg) {
Scanner kbd= new Scanner (System.in);
Random rand = new Random();
int carX = rand.nextInt(100);
int carY= rand.nextInt(100);
System.out.println("Position of vehicle : "+ " x= " + carX + " y= " + carY);
int x =rand.nextInt(100);
int y =rand.nextInt(100);
ParkingSpot spot1 = new ParkingSpot("W.Boise",x,y);

ParkingSpot spot2 = new ParkingSpot("E.Boise",x,y);

ParkingSpot spot3 = new ParkingSpot("E.Chrisway",x,y);

ParkingSpot spot4 = new ParkingSpot("W.Chrisway",x,y);


}









}




