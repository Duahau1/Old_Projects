import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;


public class sumnumber {
	public static void main(String[]arg) {
		Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream("filename.txt"), "utf-8")) {
	   writer.write("something");
		
		int sum=0;
		File file =new File(input.txt);	
		if (file.exists()) {	
			Scanner scan =new Scanner (file);
			scan.useDelimiter("/");
			while(scan.hasNext()) {
				int x=-1;   
				x=scan.nextInt();
				sum+=x;
			}
		}
		else {
			System.out.println("Blow up!");
		}
		System.out.println("The sum is "+sum);
	}	
}
