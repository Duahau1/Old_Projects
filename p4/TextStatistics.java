import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class TextStatistics implements TextStatisticsInterface {
DecimalFormat df = new DecimalFormat ("#.00");
	File file = new File ("");
	final private int NUMCHAR=26;
	final private int MAXWORD=23;
	
	private char current;
	private double average;
	private int lin =0;
	private int cha =0;
	private int wo =0;
	private double other=0;
	private int w;
	private static final String DELIMITERS = "[\\W\\d_]+";
	private int sum=0;
	private int[] wordlength= new int [MAXWORD];
	private int[] charactercount= new int[NUMCHAR];
	//constructor
	public TextStatistics(File file) {
		this.file =file;
		try {
			Scanner filescan = new Scanner(file);
			while(filescan.hasNextLine()) {
				String line = filescan.nextLine();
				Scanner linescan = new Scanner (line).useDelimiter(DELIMITERS);
				lin++;
				int x= line.length();
				cha+=x+1;
				
				for(int ch = 0; ch < line.length(); ch++)
				{
					current = line.charAt(ch);
					if(current >= 'A' && current <= 'Z')
					{
						charactercount[current - 'A']++;
					}
					else if(current >= 'a' && current <= 'z')
					{
						charactercount[current - 'a']++;
					}	
					else other++;
				}		
				while (linescan.hasNext()) {
					String token = linescan.next();
					wo++;
					
					if (token.length()>0 && token.length()<MAXWORD) {
						wordlength[token.length()]++;	
					}
					for (int i =0; i<wordlength.length; i++) {
						sum+=wordlength[i];
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
		}
	}
	@Override
	public int getCharCount() {
		// TODO Auto-generated method stub
		return cha;
	}

	@Override
	public int getWordCount() {
		// TODO Auto-generated method stub
		return wo;
	}

	@Override
	public int getLineCount() {
		// TODO Auto-generated method stub
		return lin;
	}

	@Override
	public int[] getLetterCount() {
		// TODO Auto-generated method stub
		return charactercount;

	}

	@Override
	public int[] getWordLengthCount() {
		// TODO Auto-generated method stub
		return  wordlength;
	}

	@Override
	public double getAverageWordLength() {
		// TODO Auto-generated method stub
		average = (double)((cha-other-lin)/wo);
		return average;
	}
	public String toString() {
		String s= " ";
		String d=" ";
		String m=" ";
		char l =' ';
		char n=' ';
		System.out.println("Statistics for " + file + "\n==========================================================\n" +
				getLineCount() + " lines\n" + getWordCount() + " words\n" + getCharCount() + " characters\n------------------------------\n") ;

		for (int letter = 0, chu=13; letter <= 13 && chu<charactercount.length; letter++, chu++) {
			l= 	(char) (letter+'a');	
			n= (char)(chu+'a');
			if (charactercount[letter]<10) {
			s= " "+l +"= "+ charactercount[letter]+' '+ "      "+ n+" = " +charactercount[chu];	
			System.out.println(s);}
			else {s= " "+l +"= "+ charactercount[letter]+ "      "+ n+" = " +charactercount[chu];	
			System.out.println(s);
			}
			}
		System.out.println("------------------------------\nlength  frequency\n------  ---------\n" );
		for (int letter = 1; letter < wordlength.length; letter++) {
			if (letter<10) {
			d= " "+letter+' ' + "        "+ wordlength[letter];
			System.out.println(d);}
			else if(letter>=10 && wordlength[letter]!=0)
			{d= " "+letter + "        "+ wordlength[letter];
			System.out.println(d);
		
			}
		
		}

		return  "\n\nAverage word length = " + df.format(getAverageWordLength()) + 
				"\n==========================================================\n";
	}

}

