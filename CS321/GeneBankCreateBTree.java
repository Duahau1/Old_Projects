import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Creates a BTree from a gbk file.
 * 
 */
public class GeneBankCreateBTree {

	

	public static final int MAX_LENGTH = 31;
	public static final int DEBUG_LEVEL = 1;
	public static final long A = 0b00L;
	public static final long T = 0b11L;
	public static final long C = 0b01L;
	public static final long G = 0b10L;
	
	public static void main(String[] args) throws IOException {
		if (args.length < 4) {
			printUsage();
		}

		boolean useCache = false;

		try {
			int c = Integer.parseInt(args[0]);
			if (c == 0) useCache = false;
			else if (c == 1) useCache = true;
			else printUsage();
		} catch (NumberFormatException e) {
			printUsage();
		}

		int degreeB = 0;

		try {
			int deg = Integer.parseInt(args[1]);
			if (deg < 0) printUsage();
			else if (deg == 0) degreeB = getOptimalDegree();
			else degreeB = deg;
		} catch (NumberFormatException e) {
			printUsage();
		}
		int sequenceLength = 0;

		try {
			int len = Integer.parseInt(args[3]);
			if (len < 1 || len > MAX_LENGTH) printUsage();
			else sequenceLength = len;
		} catch (NumberFormatException e) {
			printUsage();
		}

		int cacheSize = 0;
		int debugLevel = 0;

		if (args.length > 4) {
			if (useCache) {
				try {
					int csize = Integer.parseInt(args[4]);
					if (csize < 1) printUsage();
					else cacheSize = csize;
				} catch (NumberFormatException e) {
					printUsage();
				}
			}
			if (!useCache||args.length > 5) {
				try {
					int dlevel = Integer.parseInt(useCache ? args[5] : args[4]);
					if (dlevel < 0 || dlevel > DEBUG_LEVEL) printUsage();
					else debugLevel = dlevel;
				} catch (NumberFormatException e) {
					printUsage();
				}
			}
		}

		File filegbk = new File(args[2]);

		BufferedReader in = null; // Scanner does not trim() and manually trim() does not give correct frequency
		try {

			in = new BufferedReader(new FileReader(filegbk));

		} catch (FileNotFoundException e) {
			System.err.println("File not found: "+filegbk.getPath());
		}
		String BTreeFile = (filegbk + ".btree.data." + sequenceLength + "." + degreeB);

		long startTime= System.currentTimeMillis();

		BTree tree = new BTree(degreeB, BTreeFile, useCache, cacheSize);
		BTreeData parse = new BTreeData(tree);

		String line = null;
		line = in.readLine().toLowerCase().trim();
		boolean inSequence = false;
		int sequencePosition = 0;
		int charPosition = 0;
		long sequence = 0L;
		//READ OFF THE FILE because there can be more than 1 ORIGIN
		while (line != null) {
			if (inSequence) {
				if (line.startsWith("//")) {
					inSequence = false;
					sequence = 0;
					sequencePosition = 0;
				} else {

					while (charPosition< line.length()) {

						char c = line.charAt(charPosition++);
						switch (c) {
						case 'a':
							sequence = ((sequence<<2) | A);
							if (sequencePosition < sequenceLength) sequencePosition++;
							break;
						case 't':
							sequence = ((sequence<<2) | T);
							if (sequencePosition < sequenceLength) sequencePosition++;
							break;
						case 'c':
							sequence = ((sequence<<2) | C);
							if (sequencePosition < sequenceLength) sequencePosition++;
							break;
						case 'g':
							sequence = ((sequence<<2) | G);
							if (sequencePosition < sequenceLength) sequencePosition++;
							break;
						case 'n':
							sequencePosition = 0;
							sequence = 0;
							continue;
						default:
							continue;
						}
						if (sequencePosition >= sequenceLength) {
							tree.bTreeInsert(sequence & (~(0xffffffffffffffffL<<(sequenceLength<<1))));
						}
					
					}

				}
			} else if (line.startsWith("ORIGIN")) {
				inSequence = true;
			}
			line = in.readLine();
			charPosition = 0;
		}

		if (debugLevel == 1) {
			File dumpFile = new File("dump");
			dumpFile.delete();
			dumpFile.createNewFile();
			FileWriter fw = new FileWriter(dumpFile);
			tree.inOrderPrintToWriter(tree.getRoot(),fw,sequenceLength);
			fw.close();
			//tree.inOrderPrint(tree.getRoot()); //only to debug
		}

		else if(debugLevel==0) {
			printUsage();
			tree.inOrderPrint(tree.getRoot());// printing the information of the entire BTree for debug
		}

		if (useCache) tree.flushCache();
		in.close();

		//the process end
		long endTime = System.currentTimeMillis();

		if(Integer.parseInt(args[0])==0) {
			System.out.println("The process without the cache took " + ((endTime - startTime)/1000) + " seconds \n");
		}
		if(Integer.parseInt(args[0])==1) {
			System.out.println("The process with the cache took "+ ((endTime - startTime)/1000) + " seconds \n");
		}
	}


	private static void printUsage() {
		System.err.println("Usage: java GeneBankCreateBTree <cache> <degree> <gbk file> <sequence length> [<cache size>] [<debuglevel>]");
		System.exit(1);
	}

	public static int getOptimalDegree(){
		double optimum;
		int sizeOfPointer = 4;
		int sizeOfObject = 12;
		int sizeOfMetadata = 5;
		double diskBlockSize = optimum = 4096;
		optimum += sizeOfObject;
		optimum -= sizeOfPointer;
		optimum -= sizeOfMetadata;
		optimum /= (2 * (sizeOfObject + sizeOfPointer));
		return (int) Math.floor(optimum);
	}
}