
/*
 * To convert the DNA string to binary sequence and convert binary sequence back to DNA string (for the BTree file in the
 *  command line argument)
 */
public class BTreeConvert {

	private long DNAkey;  // the DNA sequence accumulator
	private long leftest; //the shifting bits
	private int seqlen; //length of sequence
	private int remainChar; // remaining number of letters still needed

	/**
	 * The constructor to store the sequence length
	 * @param the sequence length
	 * @throws Exception if the sequence length is greater than 31 or less than 1
	 * 
	 */
	public BTreeConvert(int seqlen) throws Exception {
		if (seqlen < 1 || seqlen > 31) throw new Exception("Invalid sequence length");
		this.seqlen = seqlen;
		// To obtain the numbers of spots we need as we know
		// we need the num spots = seqlen *2 => we have to move (seqlen -1)*2 
		leftest = (seqlen - 1) << 1; 

		DNAkey = 0;
		remainChar = seqlen;
	}

	/**
	 *  To take a character and encode it corresponding to the letter
	 * 	@param input character (either upper case or lower case)
	 * 	@return encoded string
	 */
	public static long binConvert(char x) throws Exception {
		switch(x) {
		case 'A':
		case 'a': return 0b00;
		case 'T':
		case 't': return 0b11;
		case 'C':
		case 'c': return 0b01;
		case 'G':
		case 'g': return 0b10;
		default: throw new Exception("Invalid options"); //if there is any other character apart from the those listed above
		}
	}

	/**
	 *  To take a long number but only looks at the two lowest bits
	 * 	@param a long number
	 * 	@return  a letter corresponding to the two lowest bits
	 */
	public static char charConvert(long y) {
		switch((short)y & 0b11) {
		case 0b00: return 'a';
		case 0b11: return 't';
		case 0b01: return 'c';
		case 0b10: return 'g';
		}
		return 0;
	}

	/**
	 *  To get the accumulator value of the sequence
	 * 	
	 * 	@return  the accumulator value of the sequence
	 */
	public long getDNAKey() throws Exception {
		if (remainChar > 0) throw new  Exception("Invalid DNA");
		return DNAkey;
	}

	/**
	 *  To build the DNA string that represents the sequence of a given length
	 * 	corresponding to given key (searching purpose)
	 * 	@param length of the sequence
	 * 	@param the long key
	 * 	@return the built string
	 */

	public static String getDNAString(int seqlen, long key) {
		char[] buf = new char[seqlen];
		for (int i=0; i<seqlen; i++) {
			buf[i] = charConvert(key);
			key >>= 2;//shift 2 bits at a time in order to convert the binary sequence into the character string 
		}
		return new String(buf);
	}



	/**
	 * For debugging purpose only
	 * @return the remaining characters to parse in 
	 *  
	 */
	public int  getR() {
		return remainChar;
	}

	/**
	 * To convert the sequence into bits and move the accumulator 2 bits right, then discard the rightmost 2 bits
	 * and set the leftmost 2 bits with new data
	 * We are doing a single character at a time
	 * @param character to convert to binary number
	 * 
	 */
	public void getDNAbits(char c) throws Exception {
		try {

			DNAkey = (DNAkey >> 2) | (binConvert(c) << leftest);
			switch(remainChar) {
			case 0: 
				break;
			case -1:
				throw new Exception();
			default:
				remainChar -= 1;
				break;
			}
		} catch(Exception e) {
			DNAkey = 0;
			remainChar = seqlen; //if something goes wrong then reset sequence and start from beginning
		}
	}
	/**
	 *  To check if the sequence is complete in length
	 * 	@return true if the sequence is complete
	 */
	public boolean isDone() {
		return remainChar == 0;
	}


	/**
	 *  To resets all accumulators
	 * 	set the key to 0 and the number of remaining characters to 0
	 * as we access a totally new sequence
	 * 	
	 */
	public void resetAccumulators() {
		DNAkey = 0;
		remainChar = seqlen;
	}

}
