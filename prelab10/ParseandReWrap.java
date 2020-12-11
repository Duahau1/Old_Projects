import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParseandReWrap {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner kbd = new Scanner(System.in);

		System.out.print("Enter a filename: ");
		String filename = kbd.nextLine().trim(); //trim removes extra whitespace
		System.out.print("Please enter the maximum number of characters in a single line:");
		int maxchar= kbd.nextInt();
		System.out.println(filename +" reformatted with maximum line length of " + maxchar); 
		kbd.close(); 
		File file=new File(filename);
		Scanner scan = new Scanner(file);
		String add= "";
		int longest=0;
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			Scanner linescan= new Scanner(line);
			while(linescan.hasNext()) {
				String token = linescan.next();
				// string concatenate 
				if (token.length()+add.length()<=maxchar) {
					add+= token +" ";
					// longest line	
					if(add.length()>longest) {
						longest= add.length()-1;
					}
				}
				else if (token.length()+add.length()>=maxchar) {
					System.out.println(add);
					add= token + " ";
				}
			}

			linescan.close();
		}
		System.out.println(add);
		System.out.println(" Longest line:"+ longest);
		System.out.println(" Shortest line:"+ add.length());
		scan.close();
	}
}
