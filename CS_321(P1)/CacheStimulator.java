import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CacheStimulator {
	private static Cache<String> cacheOne, cacheTwo;
	private static boolean twoLevel=false;
	private static File file;

	public static void main(String[] args) {
		//Verify the command line, it can either be one of the 2 options in the manual 

		if (args.length<1) {
			manual();
		}
		// If we choose L1
		if(args[0].equals("1")) {
			if(args.length<3) {
				manual();
			}
			cacheOne= new Cache<String>(Integer.parseInt(args[1]));
			readFile(new File(args[2]));
		}
		//If we choose L2
		else if(args[0].equals("2")) {
			if(args.length<4) {
				manual();
			}
			if(Integer.parseInt(args[2])<=Integer.parseInt(args[1])) {
				System.out.println("ERROR: L2 cache size <L1 cache size");
				manual();	
			}
			twoLevel=true;
			cacheOne= new Cache<String>(Integer.parseInt(args[1]));
			cacheTwo = new Cache<String>(Integer.parseInt(args[2]));
			readFile(new File(args[3]));
		}
	}
	/**
	 * The error system
	 */
	public static void manual() {
		System.out.println("Incorrect command line");
		System.out.println("The correct commands are shown below:");
		System.out.println("java CacheSimulator [1] [L1 cache size] [filename] ");
		System.out.println("java CacheSimulator [1 | 2] [L1 cache size] [ | L2 cache size] [filename]");
		System.exit(1);

	}
	/**
	 * Read the file and print out the results
	 * @param file
	 */
	public static void readFile(File file) {

		double accessNum=0;
		double cacheOneNM=0;
		double cacheOneNH=0;
		double cacheTwoNH=0;
		try {

			if(twoLevel) {
				System.out.println("L1 cache with "+ cacheOne.getCapacity()+ " entries created");
				System.out.println("L2 cache with "+ cacheTwo.getCapacity()+ " entries created");
			}
			else {
				System.out.println("L1 cache with "+ cacheOne.getCapacity()+ " entries created");
			}
			System.out.println("...");
			//Verify the file name

			Scanner filescan= new Scanner(file);
			String curr;
			while(filescan.hasNextLine()) {
				String line =filescan.nextLine();
				StringTokenizer token = new StringTokenizer(line," \t");		
				accessNum += token.countTokens();
				while(token.hasMoreTokens()) {
					curr= token.nextToken();
					if(twoLevel) {
						if(cacheOne.contain(curr)) {
							cacheOne.get(curr);
							cacheTwo.write(curr);
						}
						else {
							if(cacheTwo.contain(curr)) {
								cacheOne.add(curr);
								cacheTwo.get(curr);
							}
							else {
								cacheOne.add(curr);
								cacheTwo.add(curr);
							}
						}

					}
					else {
						cacheOne.get(curr);
					}
				}
			}
			filescan.close();
			DecimalFormat intFormat = new DecimalFormat("####");
			DecimalFormat doubleFormat = new DecimalFormat("#0.00");
			if (twoLevel) {
				System.out.println("\nNumber of L1 cache hits: " + (int) cacheOne.getHit() 
						+ "\nL1 cache hit rate: "+ doubleFormat.format((cacheOne.getHit()/accessNum)*100)+"%");
				System.out.println("Number of L2 cache hits: " + (int) cacheTwo.getHit() 
						+ "\nL2 cache hit rate: " + doubleFormat.format((cacheTwo.getHit()/(accessNum-cacheOne.getHit()))*100)+"%");
				System.out.println("\nTotal number of accesses: " + (int) accessNum);
				System.out.println("Total number of cache hits: " + (int) (cacheOne.getHit()+cacheTwo.getHit()));
				System.out.println("Overall hit rate " + doubleFormat.format(((cacheOne.getHit()+cacheTwo.getHit())/accessNum)*100)+"%");

			} else {
				System.out.println("\nNumber of L1 cache hits: " + (int) cacheOne.getHit() 
						+ "\nL1 cache hit rate: "+ doubleFormat.format((cacheOne.getHit()/accessNum)*100)+"%");
				System.out.println("\nTotal number of accesses: " + (int) accessNum);
				System.out.println("Total number of cache hits: " + (int) (cacheOne.getHit()));
				System.out.println("Overall hit rate " + doubleFormat.format((cacheOne.getHit()/accessNum)*100)+"%");
			}
		}
		catch(FileNotFoundException e){
			System.out.println("File not Found");
		}



	}

}


