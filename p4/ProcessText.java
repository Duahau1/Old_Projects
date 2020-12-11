
import java.io.File;

public class ProcessText {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length== 0) {
			System.err.println("Usage: java ProcessText file1 [file2 ...]");
			System.exit(1);}
		for (String a: args) {
			File file = new File (a);
			if (file.exists()) {
				TextStatistics sta = new TextStatistics(file);
				System.out.println(sta);
			}
			else System.out.println("Invalid file path: not-a-file.txt");
		}
	}

}
