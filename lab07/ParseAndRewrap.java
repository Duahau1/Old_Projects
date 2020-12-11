import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author vannguyen
 *
 */
public class ParseAndRewrap {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner kbd = new Scanner(System.in);
		System.out.print("Please enter a plain text file name:");
		String filename = kbd.nextLine().trim();
		System.out.print("Please enter the maximum number of characters in a single line: ");

		int maxchar = kbd.nextInt();
		System.out.println(filename +" reformatted with maximum line length of "+ maxchar);
		kbd.close();
		File file= new File(filename);
		//System.out.println(filename.length());
		Scanner filescan= new Scanner(file);
		String add="";
		int longest=0;
		while(filescan.hasNextLine()) {
			//read one line
			String line= filescan.nextLine();
			//scan token
			Scanner linescan= new Scanner(line);
			// read each token/word
			while(linescan.hasNext()) {
				//read the next token
				String words= linescan.next();
				// not over max char	
				if (words.length()+add.length()<=maxchar) {
					add+= words+" ";	
				//the longest
				if (add.length()>longest) {
					longest= add.length()-1;
				}
				}
				// rewrap 
				else if(words.length()+add.length()>=maxchar) {
					System.out.println(add);
					add= words;
			}
			}



			linescan.close();
		}

		System.out.println(add);
		System.out.println("\n\tLongest line: " + longest);
		System.out.println("\tShortest line: " + add.length());
		// close the file scanner.
		filescan.close();
	}
















}	

