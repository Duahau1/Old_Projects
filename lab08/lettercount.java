import java.util.Scanner;

public class lettercount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan= new Scanner(System.in);
		System.out.println("Enter a string");
		String line = scan.nextLine();
		int other=0;
		final int LETTERS= 26;
		int [] lower= new int[LETTERS];
		int [] upper= new int[LETTERS];
		for (int i=0;i<line.length();i++) {
			char c= line.charAt(i);
			if(c>='a'&&c<'z') {
			lower[c-'a']++;
			}
			if(c>='A'&&c<='Z') {
		upper[c-'A']++;
			
			}
			else {	
		other++;
				
			}
		}
	System.out.println("Letter counts\n=======================");
	Character c='a';	
	Character C='A';
	for (int i=0;i<LETTERS;i++,c++,C++) {
	System.out.print(c+" : "+lower[c-'a']);
	System.out.print("\t");
	System.out.print(C+" : "+upper[C-'A']);
	System.out.println();
	}
	
	}

}
