
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/* @author vannguyen
 * Name: Van Nguyen 
 * CS221 Section 3
 */
/**
 * @author vannguyen
 * This program reads in the contents of a given file and ensures that the data is formatted correctly.
 * The valid format rules are as follows:
The first row contains two white-space-separated, positive integers.
The first integer specifies the number of rows in a grid.
The second integer specifies the number of columns in the grid.
Each subsequent row represents one row of the grid and should contain exactly one white-space-separated value for each grid column.
 * 
 * 
 * 
 * 
 * 
 * The invalid format rules are as follows:
The file can't be found.
The file contains data in the wrong format.
The values don't match the expected data types.
There are fewer rows and/or columns of data than specified.
There are more rows and/or columns of data than specified.
There are more or less than 1 starting point
There are more or less than 1 ending point
There are unknown characters -different from [1, 2, O, T, X] like in invalid5.dat
 *
 */
public class FormatChecker {



	private static int DIMENSION = 2;
	private static int row,col;
	private static String file; 
	private static String exception=null;
	private static String inputexception=null;
	private final static char START = '1';
	private final static char END = '2';
	private final static char OPEN = 'O'; 
	private final static char CLOSED = 'X';
	private final static char TRACE = 'T';
	/**
	 * Constructor that contains all the method to validate the specified file
	 * 
	 * @param filenames
	 *            the grid file to validate
	 * 
	 * @throws FileNotFoundException 
	 * 
	 */
	public FormatChecker(String filenames) throws FileNotFoundException{

		file=filenames;
		this.check();
		this.checkrow();
		this.checkcol();
		this.matchrow();
		this.Matchcol();
		this.bodycheck();

	}
	
	public String getError() {
		return exception;
	}
	
	
	/**
	 *This method checks whether the file gave the 2 numbers, one for the row and one for the column 
	 * 
	 *
	 *           
	 * @return a string if there is an error
	 * - Error will occur if the numbers given are more than 2 numbers
	 * 
	 * ; null otherwise if there is no error
	 * @throws FileNotFoundException 
	 * 
	 */
	public String check() throws FileNotFoundException  {

		
			Scanner lineonescanner= new Scanner(new File(file));
			// get the first line and remove all white space to get accurate token
			String[] lineone = lineonescanner.nextLine().split(" ");
			lineonescanner.close();
			if (lineone.length!=DIMENSION) {
				exception="Grid dimensions input is not: "+ DIMENSION + " It has "+lineone.length+" values ";
				return exception;
			}
			
			return null;
	}
	/**
	 * This method get the grid column from the file
	 * 
	 * @return return a string if there is an error
	 * - Error will occur if we did not format the number correctly
	 * 
	 * ; null otherwise if there is no error
	 * 
	 * @throws FileNotFoundException
	 *             if the specified file is invalid
	 * 
	 */
	public String checkcol() throws FileNotFoundException {
		Scanner scan= new Scanner(new File(file));
		try {
			scan.nextInt() ;
			col = Integer.parseInt(scan.next());
			scan.close();
			return null;

		} catch (NumberFormatException e) {
			scan.close();
			inputexception=e.toString();
			return inputexception;
		} catch (InputMismatchException e) {
			inputexception=e.toString()+" for "+scan.next();
			scan.close();
			return inputexception;
		}

	}

	
	/**
	 *This method checks the column in the grid to see if they match the given dimension
	 * 
	 * 
	 * @return a string if there is an error
	 * Error will occur if the value of column given on the first line did not match with the columns in the grid
	 * ; null otherwise if there is no error
	 * @throws FileNotFoundException 
	 * 
	 */
	public String Matchcol()throws FileNotFoundException {
		Scanner notlineonescanner= new Scanner (new File(file));
		notlineonescanner.nextLine();
		while(notlineonescanner.hasNext()) {
			String[] line = notlineonescanner.nextLine().split(" ");
			if (col != line.length) {
				notlineonescanner.close();
				exception="Grid column does match the expected value (" + col + ")." + " Grid col: " + line.length;
				return exception;
			}
		}
		notlineonescanner.close();
		return null;
	}

	/**
	 *This method checks the row in the grid to see if they match the given dimension
	 * 
	 *
	 * @return a string if there is an error
	 * Error will occur if the value of row given on the first line did not match with the rows in the grid
	 * ; null otherwise if there is no error
	 * @throws FileNotFoundException 
	 * 
	 */
	
	public String matchrow() throws FileNotFoundException {
		int lineCount = 0;
		Scanner notlineonescanner = new Scanner(new File(file));
		// skip row & col since those have been read
		notlineonescanner.nextLine();
		while (notlineonescanner.hasNext()) {
			notlineonescanner.nextLine();
			lineCount++;
		}
		notlineonescanner.close();
		if (row != lineCount) {
			exception="Grid row does not match the expected value (" + row + ")." + " Grid row: " + lineCount;
			return exception;
		}
		notlineonescanner.close();
		return null;
	}

	/**
	 * This method get the row column from the file
	 * 
	 * @return return a string if there is an error
	 * - Error will occur if we did not format the number correctly
	 * 
	 * ; null otherwise if there is no error
	 * 
	 * @throws FileNotFoundException
	 *             if the specified file is invalid
	 * 
	 */
	public String checkrow() throws FileNotFoundException {
		Scanner scan = new Scanner(new File(file));
		
		try {
			row = Integer.parseInt(scan.next());
			scan.close();
			return null;
		} catch (NumberFormatException e) {
			scan.close();
			inputexception=e.toString();
			return inputexception;
		} catch (InputMismatchException e) {
			scan.close();
			inputexception=e.toString()+" for "+scan.next();
			return inputexception;
		}
	}
	/**
	 * This method read the body of the file
	 * 
	 * @return return a string if there is an error
	 * Error will occur if there is more than one start or end point in the body or there is any missing signs from [1, 2, O, T, X]
	 * ; null otherwise if there is no error
	 * @throws FileNotFoundException
	 *             if the specified file is invalid
	 * 
	 */
	public String bodycheck() throws FileNotFoundException {
		int beginCount = 0;
		int finishCount = 0;
		Scanner notlineonescanner = new Scanner(new File(file));
		notlineonescanner.nextLine();
		try {
			while (notlineonescanner.hasNext()) {
				String[] line = notlineonescanner.nextLine().split(" ");
				for (String string : line) {
					char[] strChar = string.toCharArray();
					for (char a : strChar) {
						if (a == START) {
							beginCount++;
						}
						if (a == END) {
							finishCount++;
						}
			
						if (a != START && a != END && a != OPEN && a != TRACE && a != CLOSED) {
							notlineonescanner.close();
							exception=	"The file have unknown character (" + a + ") expected [1, 2, O, T, X]";
							return exception;
						}
					}
				}
			}
				if (beginCount != 1) {
					notlineonescanner.close();
					exception ="The number of starting positions (" + beginCount
							+ ") does not match the expected of 1 starting point ";
					return exception;
				}
				if (finishCount != 1) {
					notlineonescanner.close();
					exception="The number of ending positions (" + finishCount
							+ ") does not match the expected of 1 end point";
					return exception;
				}
					notlineonescanner.close();
			}catch (NumberFormatException e) {
			notlineonescanner.close();
			System.out.println(e.toString());
			exception=e.toString();
			return exception;
		} catch (InputMismatchException e) {
			notlineonescanner.close();
			exception=e.toString();
			return exception;
		}
		return null;
		
	}

}


