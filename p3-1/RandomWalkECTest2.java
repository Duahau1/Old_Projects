import java.util.Random;
import java.util.Scanner;

public class RandomWalkECTest2 {
	private static int gridSize = 0;
	private static long seed = 0;

	/**
	 *
	 */
	private static void getInput()
	{
	Scanner scan = new Scanner(System.in);
	System.out.print("Enter grid size");
	gridSize=scan.nextInt();
	while(gridSize<0||gridSize==0) {
		System.out.println("Error:grid size must be positive");
		System.out.print("Enter grid size");
		gridSize=scan.nextInt();
	
	}
	Random rand =new Random();
	System.out.print("Enter random seed (0 for no seed)");
	seed=scan.nextInt();
	rand.setSeed(seed);
	while(seed<0) {
	System.out.println("Error: random seed must be >=0");
	System.out.print("Enter random seed (0 for no seed)");
	seed=scan.nextInt();}	
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// call getInput to process user input
		getInput();
		// create RandomWalk object using the appropriate constructor
		if (seed==0) {
		RandomWalkEC2 walk = new RandomWalkEC2(gridSize);
		// create the random walk and then print it
	
		walk.createWalk();
		System.out.println(walk);}
		if (seed>0) {
			RandomWalkEC2 walk = new RandomWalkEC2(gridSize, seed);
			// create the random walk and then print it
		
			walk.createWalk();
			System.out.println(walk);}
		
		}

}
