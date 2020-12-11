import java.io.File;
import java.util.Scanner;


/**
 * Search with a given sequence
 *
 *
 */
public class GeneBankSearch {

	private static boolean useCache = false;
	private static String btreeFile, queryFile;
	private static int cacheSize, debugLevel = 0;


	public static void main(String[] args) {

		if(args.length < 3 || args.length > 5) {
			printUsage();
		}		

		if (args[0].equals("1")) {
			useCache = true;
		} else if (!(args[0].equals("0") || args[0].equals("1"))) {
			printUsage();
		}

		btreeFile = args[1];
		queryFile = args[2];

		if (useCache && args.length >= 4) {
			cacheSize = Integer.parseInt(args[3]);
		}

		if(args.length == 5)
			debugLevel = Integer.parseInt(args[4]);

		String sequence = "", deg = "";

		for(int i = btreeFile.length()-1; i >= 0; i--) {
			if(btreeFile.charAt(i) != '.')
				deg += btreeFile.charAt(i);
			else break;
		}
		deg = reverseString(deg);

		for (int i = btreeFile.length()-deg.length()-2; i >= 0; i--) {
			if(btreeFile.charAt(i) != '.')
				sequence += btreeFile.charAt(i); //concat
			else break;
		}
		sequence = reverseString(sequence);
		long startTime= System.currentTimeMillis();
		
		int degree = Integer.parseInt(deg);
		int sequencelen = Integer.parseInt(sequence);
		
		System.out.println("BTree Search Table");
		System.out.println("##########################################");
		System.out.println("The degree of the tree: " + degree);
		System.out.println("The sequence length: " + sequencelen);
		
		try {
			Boolean flag=false;
			BTreeConvert gbc = new BTreeConvert();
			BTree tree = new BTree(degree, new File(args[1]), useCache, cacheSize);
			Scanner scan = new Scanner(new File(queryFile));
			int lineNum=1;
			while(scan.hasNext()) {
				String query = scan.nextLine(); //sequence to search for
				
				long q = gbc.convertStringToLong(query);
				String binaryString = gbc.convertLongToString(q, sequencelen);
				TreeObject result = tree.search(tree.getRoot(), q);
				
				if(result != null)System.out.println("Seq"+ (lineNum++) +"  "+gbc.convertLongToString(result.getData(), Integer.parseInt(sequence))+":"+ result.getFrequency());
				if(result==null) {
					continue;
				}
			}
			
			//the process end
			long endTime = System.currentTimeMillis();

			if(Integer.parseInt(args[0])==0) {
				System.out.println("The process without the cache took " + ((endTime - startTime)) + " milli seconds \n");
			}
			if(Integer.parseInt(args[0])==1) {
				System.out.println("The process with the cache took "+ ((endTime - startTime)) + " milli seconds \n");
			}
			System.out.println("##########################################");
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String reverseString(String s) {
		if(s.length() == 1)
			return s;
		return "" + s.charAt(s.length() - 1) + reverseString(s.substring(0, s.length() - 1));
	}

	private static void printUsage() {
		System.err.println("Usage: java GeneBankSearch <0/1(no/with Cache)> <btree file> <query file> [<cache size>] [<debug level>]\n");
		System.exit(-1); 
	}
}