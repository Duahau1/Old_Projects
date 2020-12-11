
import java.io.File;
import java.io.FileNotFoundException;

public class ProcessText {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		if (args.length== 0) {
			System.err.println("Usage: java ProcessText file1 [file2 ...]");
			System.exit(1);}
		for (String a: args) {
			
			
				GridMonitor sta = new GridMonitor(a);
				System.out.println(sta);
			
			
		}
	}

}
