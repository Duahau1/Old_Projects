import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Search for shortest paths between start and end points on a circuit board
 * as read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to
 * a GUI according to options specified via command-line arguments.
 * 
 * @author mvail
 */
public class CircuitTracer {
	private CircuitBoard board;
	private Storage<TraceState> stateStore;
	private ArrayList<TraceState> bestPaths;

	/** launch the program
	 * @param args three required arguments:
	 *  first arg: -s for stack or -q for queue
	 *  second arg: -c for console output or -g for GUI output
	 *  third arg: input file name 
	 */
	public static void main(String[] args) {
		if (args.length != 3) {
			printUsage();
			System.exit(1);
		}
		try {
			new CircuitTracer(args); //create this with args
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/** Print instructions for running CircuitTracer from the command line. */
	private static void printUsage() {
		//TODO: print out clear usage instructions when there are problems with
		// any command line args
		System.out.println("java CircuitTracer <-s | -q> <-c | -g> <file>");
		System.out.println("Options:");
		System.err.println("   -q   -- use a queue for storage");
		System.err.println("   -s   -- use a stack for storage");
		System.err.println("   -g   -- run program in GUI mode ");
		System.err.println("   -c   -- run program in console mode");
	}
	
	/** 
	 * Set up the CircuitBoard and all other components based on command
	 * line arguments.
	 * 
	 * @param args command line arguments passed through from main()
	 */
	private CircuitTracer(String[] args) {
		//TODO: parse command line args
		//TODO: initialize the Storage to use either a stack or queue
		//TODO: output results to console or GUI, according to specified choice
		char storageType = args[0].substring(1, 2).charAt(0);
		char programType = args[1].substring(1, 2).charAt(0);
		String file = args[2];
		try {
			if (storageType != 'q' && storageType != 's') {
				printUsage();
				System.out.println("The indicated storage type is not supported");
				System.exit(1);
			}

			if (programType != 'c' && programType != 'g') {
				printUsage();
				System.out.println("The indicated program mode is not supported");
				System.exit(1);
			}
			//TODO: read in the CircuitBoard from the given file
			board = new CircuitBoard(file);

			if (storageType == 's') {
				stateStore = new Storage<TraceState>(Storage.DataStructure.stack);
			} else {
				stateStore = new Storage<TraceState>(Storage.DataStructure.queue);
			}
			bestPaths = new ArrayList<>();
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		}

		// run the search for best paths
		// output results to console or GUI, according to specified choice
		//TODO: run the search for best paths
		search();
		switch (programType) {
		case 'c':
			for (TraceState state : bestPaths) {
				System.out.println(state);
			}
			break;
		// default to GUI
		default:
			new CircuitGUI(bestPaths, board);
			break;
		}
		
	}
	/**
	 * Find the shortest path available among all the founded best paths
	 * 
	 * @return the shortest of all them all
	 */
	private int getshortestPath() {
		int shortestPath = Integer.MAX_VALUE;
		for (TraceState traceState : bestPaths) {
			if (traceState.pathLength() < shortestPath) {
				shortestPath = traceState.pathLength();
			}
		}
		return shortestPath;
	}	
	/**
	 * Following the instruction on the website,searching for all the shortest paths for the file
	 */
	private void search() {
		// get open points adjacent to our starting position
		ArrayList<Point> openStart = board.searchAdjacents(board.getStartingPoint().x, board.getStartingPoint().y);

		// create new TraceState starting at the open positions
		for (Point point : openStart) {
			stateStore.store(new TraceState(board, point.x, point.y));
		}

		while (!stateStore.isEmpty()) {
			// get a possible valid state from the pile
			TraceState state = stateStore.retrieve();
			if (state.isComplete()) {
				if (state.pathLength() == getshortestPath()) {
					bestPaths.add(state);
				} else if (state.pathLength() < getshortestPath()) {
					bestPaths.clear();
					bestPaths.add(state);
				}
			} else {
				ArrayList<Point> adjacent = state.getBoard().searchAdjacents(state.getRow(), state.getCol());
				for (Point point : adjacent) {
					stateStore.store(new TraceState(state, point.x, point.y));
				}
			}
		}
	}

	
} // class CircuitTracer
