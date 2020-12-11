

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/* @author vannguyen
 * Name: Van Nguyen 
 * HomeWork FormatChecker
 * CS221 Section 3
 */
/**
 * @author vannguyen
 * This program reads in the contents of a given file and ensures that the data is formatted correctly.
 * The valid format rules are as follows:
The first row contains two white-space-separated, positive integers.
The first integer specifies the number of rows in a grid.
The second integer specifies the number of columns in the grid.
Each subsequent row represents one row of the grid and should contain exactly one white-space-separated double value for each grid column.
 * 
 * 
 * 
 * 
 * 
 * The invalid format rules are as follows:
 * The file can't be found.
The file contains data in the wrong format.
The values don't match the expected data types.
There are fewer rows and/or columns of data than specified.
There are more rows and/or columns of data than specified.
 *
 */
public class FormatChecker {



	private static int DIMENSION = 2;
	private static int row,col;
	private static String file; 
	private static String exception=null;
	private static String inputexception=null;
	/**
	 * Constructor that contains all the method to validate the specified file
	 * 
	 * @param filenames
	 *            the grid file to validate
	 * 
	 * @throws FileNotFoundException 
	 * 
	 */
	
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
	public static String check(String file) throws FileNotFoundException  {

		
			Scanner lineonescanner= new Scanner(new File(file));
			// get the first line and remove all white space to get accurate token
			String lineone = lineonescanner.nextLine().replaceAll("\\s","");
			System.out.println(lineone);

			System.out.println(lineone.length());
			
			//lineonescanner.close();
			if (lineone.length()!=DIMENSION) {
				exception="Grid dimensions input is not: "+ DIMENSION + " It has "+lineone.length()+" values ";
				System.out.println(exception);
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
	 * Error will occur if the value of any number in the body are not formatted correctly
	 * ; null otherwise if there is no error
	 * @throws FileNotFoundException
	 *             if the specified file is invalid
	 * 
	 */
	public String bodycheck() throws FileNotFoundException {
		Scanner notlineonescanner = new Scanner(new File(file));
		notlineonescanner.nextLine();
		try {
			while (notlineonescanner.hasNext()) {
				String str = notlineonescanner.next();
				Double.parseDouble(str);
			}
			notlineonescanner.close();
		} catch (NumberFormatException e) {
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
	/**
	 * MAIN method: valid the specified files
	 * @param args files to validate
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException  {
		final int EXIT_ERROR= 1;
		if (args.length < 1) {
			System.out.println("Usage: \n\t" + "java FormatChecker file1 [file2 ... fileN]");
			System.exit(EXIT_ERROR);
		}
		else {
			check(args[0]);
		}			
					
	}
}




