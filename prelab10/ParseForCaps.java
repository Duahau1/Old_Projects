import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParseForCaps {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner kbd = new Scanner(System.in);

		System.out.print("Enter a filename: ");
		String filename = kbd.nextLine().trim(); //trim removes extra whitespace
		kbd.close(); 
		File file=new File(filename);
		Scanner scan = new Scanner(file);
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			Scanner linescan= new Scanner(line);
			while(linescan.hasNext()) {
				String token = linescan.next();
				for(int i=0; i<token.length();i++) {
					Character t=token.charAt(i);
					if(Character.isUpperCase(t)) {
						System.out.print(t);
					}
				}
			}
			linescan.close();
		}
		scan.close();













	}
}