import javax.swing.JFrame;


/**
 * Creates a GUI that shows a random walk one step at a time.
 * @author amit
 *
 */
/**
 * @author vannguyen
 *
 */

public class RandomWalkGUIEC2 {
	
	/**
	 * Verify input parameters.
	 * @param gridSize the size of the grid
	 * @param seed the seed for the random number generator
	 */
	private static void checkInput(int gridSize, long seed) 
	{
		if (gridSize <= 0) {
			System.err.println("Error: grid size must be positive!");
			System.exit(1);
		}
		if (seed < 0) {
			System.err.println("Error: seed must be >= 0!");
			System.exit(1);
		}
	}
	
	/**
	 * Creates the GUI frame and displays a animated grid map on it.
	 * @param walker  the RandomWalk object used for creating the walk
	 * @param gridSize  the size of the grid
	 */
	private static void createGUI(RandomWalkEC2 walker, int gridSize)
	{
		JFrame frame = new JFrame("RandomWalk GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridMapEC2 panel = new GridMapEC2(walker, gridSize);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Get input using the command line arguments and create and display the walk.
	 * @param args
	 */
	public static void main(String[] args)
	{
		int gridSize = 10;
		long seed = 0;
		
		if (args.length == 0) {
			System.err.println("Usage: java RandomWalkGUI <gridSize> [<random seed>]");
			System.exit(1);
		}
		
		if (args.length == 1) {
			gridSize = Integer.parseInt(args[0]);
		} else if (args.length == 2) {
			gridSize = Integer.parseInt(args[0]);
			seed = Long.parseLong(args[1]);
		}

		checkInput(gridSize, seed);
		
		RandomWalkEC2 walker;
		if (seed > 0) {
			walker = new RandomWalkEC2(gridSize, seed);
		} else {
			walker = new RandomWalkEC2(gridSize);
			
		}
	
		createGUI(walker, gridSize);
	}

}
