/*
 * Parsing inputs from given files
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BTreeData {

	private BufferedReader buffer;
	private char DNAstr[]; //array to hold characters from each line
	private int curr; //current position in that line
	private BTreeConvert seq; //sequence builder

	private boolean hasData; //to check if we have remaining data
	private boolean nextSeq; //next sequence

	/**
	 * The constructor that takes in the file name and the desired length 
	 * @param file name
	 * @param length of the sequence 
	 * @throws Exception 
	 */
	public BTreeData(String fileName, int length) throws Exception {

		seq = new BTreeConvert(length); //initialize sequence builder for desired length

		buffer = new BufferedReader(new FileReader(fileName)); // FileReader is convenience class for reading character files.
		DNAstr = new char[0]; //initialize the empty current string
		curr = 0; //the current position is 0
		hasData = findOrigin(); //identify the "ORIGIN" keyword to read sequence
		nextSeq = false; // the next sequenced is not ready to parse in 
		moveToNext(); // obtain next sequence
	}

	/**
	 * To check if we have next sequence
	 * @return true if we have next sequence or the sequence is ready to parse in 
	 */
	public boolean hasNext() {
		return nextSeq;
	}

	/**
	 * Read in the next sequence and moves to next sequence (if possible)
	 * @return the next sequence
	 * @throws Exception 
	 */
	public long Next() throws Exception {
		if (nextSeq) { 
			long retVal = seq.getDNAKey();
			
			moveToNext(); // to obtain next sequence (if possible)
			return retVal; 

		} else {
			throw new Exception("Not found string");
		}

	}

	/**
	 * To find the line that contains the word "ORIGIN"
	 * @return true if we found the keyword "ORIGIN "
	 */
	private boolean findOrigin() throws IOException {

		String current=" ";
		while (!current.equals("ORIGIN")) {
			current = buffer.readLine();
			if (current!=null) {
				current = current.trim();
			} else {
				break;
			}
		}
		return (current!=null); 

		//return true if ORIGIN is located(because test4.gbk does not have ORIGIN keyword and any sequence to parse)
		//test5.gbk has more than one ORIGIN keyword
	}

	/**
	 * 	To obtain the sequence for the next string
	 * @return true if we have the next sequence to parse in
	 * @throws Exception if there is any errors with parsing 
	 */
	private boolean moveToNext() throws Exception {

		if (hasData) { //act only if you have data

			nextSeq = false; //assume you have not found it 
			while(hasData){ //try to build next sequence while file still has data

				if (curr >= DNAstr.length) { //current line empty 
					String tmp = buffer.readLine();//try to read next line
					
					//System.out.println(tmp);
					if (tmp==null) {
						hasData = false; 
						return false;
					} 
					else if (tmp.trim().equals("//")) { // means that we have reached the end of block
						seq.resetAccumulators(); 
						hasData = findOrigin(); //reset and find next ORIGIN (if possible)
						continue;
					}

					else { //read line from file successfully
						Scanner tmpScan = new Scanner(tmp.trim()); 
						int lineno = tmpScan.nextInt(); //reads the line number
						curr = 0; //set the current position to 0
						DNAstr = tmpScan.nextLine().trim().toCharArray(); //split line into DNAstr character array
						//System.out.println(DNAstr);
					}
				} 
				else {// if we still have data in the DNAstr[]
					char c = DNAstr[curr++]; //get next character from the DNAstr and read in the next sub-query 
					System.out.println("Current is  "+c);
					switch (c) {
					case 'n':
					case 'N':
						seq.resetAccumulators(); //reset if we hit 'N' or 'n' in the sequence
						continue;
					case 'A':
					case 'a':
					case 'T':
					case 't':
					case 'C':
					case 'c':
					case 'G':
					case 'g':
						seq.getDNAbits(c); //if there is no exceptions are thrown ,add it to the sequence
						
						if (seq.isDone()) { //if the sequence is completed, return true
							System.out.println(seq.getDNAString(3, seq.getDNAKey()));
							System.out.println(seq.getDNAKey());
							nextSeq = true; 
							
							return true;
						}
					default: // ignore that character and keep going until you find the next valid character
						continue;
					}
				}
			}
		} 

		return false; //if  we don't have any data at all


	}
	
	public static void main (String args[]) throws Exception {
		BTreeData b = new BTreeData("test1.gbk",3);
		b.Next();
		
	}
}

