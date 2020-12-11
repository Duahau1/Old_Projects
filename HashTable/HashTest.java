import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

/*The driver class that takes in arguments to create a hash table
 * @author Van Nguyen
 */
public class HashTest {
	private static String message=" ";
	private static int debugLevel;
	private final static int ERROR_EXIT=1;
	private static int maxNum=0;
	private static HashTable newHash1;
	private static HashTable newHash2;
	private static HashTable newHash3;

	public static void main(String[] args) {

		message="\nUsage :[load factor] [hash table capacity] [input file] [input type] [ | debug level]";
		//Check the number of arguments
		if(args.length<4) {
			System.err.println("Missing arguments "+message);
			System.exit(ERROR_EXIT);
		}
		if(args.length>6) {
			System.err.println("Incorrect number of arguments "+message);
			System.exit(ERROR_EXIT);
		}
		// Check the load factor
		if(Double.parseDouble(args[0])>1 || Double.parseDouble(args[0])<0) {
			System.out.println(args[0]);
			System.err.println("The load factor is invalid! "+message);
			System.exit(ERROR_EXIT);
		}
		//Check the hash table capacity
		if(Integer.parseInt(args[1])<=2) {
			System.err.println("The capacity is invalid! "+message);
			System.exit(ERROR_EXIT);

		}
		//		if (Integer.parseInt(args[1])>2&&isPrime(Integer.parseInt(args[1]))) {
		//			System.err.println("The capacity is not a prime number! "+message);
		//			System.exit(ERROR_EXIT);
		//		}	
		//Produce the maxNum 
		maxNum=(int)(Double.parseDouble(args[0])* Integer.parseInt(args[1]));
		//Check the input type
		if(Integer.parseInt(args[3])==0) {
			newHash1= new HashTable<Integer>(Integer.parseInt(args[1]),Float.parseFloat(args[0]));

			newHash2= new HashTable<Integer>(Integer.parseInt(args[1]),Float.parseFloat(args[0])
					, OpenAddressType.double_hash);
			newHash3= new HashTable<Integer>(Integer.parseInt(args[1]),Float.parseFloat(args[0])
					, OpenAddressType.quadratic);
		}
		else if(Integer.parseInt(args[3])==1) {
			newHash1= new HashTable<Long>(Integer.parseInt(args[1]),Float.parseFloat(args[0]));

			newHash2= new HashTable<Long>(Integer.parseInt(args[1]),Float.parseFloat(args[0])
					,OpenAddressType.double_hash);
			newHash3= new HashTable<Long>(Integer.parseInt(args[1]),Float.parseFloat(args[0])
					, OpenAddressType.quadratic);
		}
		else if(Integer.parseInt(args[3])==2) {
			newHash1= new HashTable<String>(Integer.parseInt(args[1]),Float.parseFloat(args[0]));

			newHash2= new HashTable<String>(Integer.parseInt(args[1]),Float.parseFloat(args[0]),OpenAddressType.double_hash);
			newHash3= new HashTable<String>(Integer.parseInt(args[1]),Float.parseFloat(args[0])
					, OpenAddressType.quadratic);
		}
		else if(Integer.parseInt(args[3])==3) {
			newHash1= new HashTable<Character>(Integer.parseInt(args[1]),Float.parseFloat(args[0]));

			newHash2= new HashTable<Character>(Integer.parseInt(args[1]),Float.parseFloat(args[0])
					,OpenAddressType.double_hash); 
			newHash3= new HashTable<Character>(Integer.parseInt(args[1]),Float.parseFloat(args[0])
					, OpenAddressType.quadratic);
		}

		else{
			System.err.println("The input type is not correct! "+message);
			System.exit(ERROR_EXIT);
		}
		//File parse in and also add to the hash table
		File fileName = new File(args[2]);
		try {
			Scanner filescan= new Scanner(fileName);


			if(Integer.parseInt(args[3])==0) {
				Scanner scan = null;
				while(filescan.hasNextLine() && newHash1.getSize() < newHash1.getMaxSize()){
					String line = filescan.nextLine();
					scan = new Scanner(line);
					String vs=" ";
					if(scan.hasNext()) {
						vs= scan.next();
					}
					if(scan.hasNext()) {
						String vd = scan.next();
						HashObject hash1= new HashObject<Integer>(Integer.parseInt(vs),Integer.parseInt(vd));
						HashObject hash2 = new HashObject<Integer>(Integer.parseInt(vs),Integer.parseInt(vd));
						HashObject hash3 = new HashObject<Integer>(Integer.parseInt(vs),Integer.parseInt(vd));
						newHash3.put(hash3.getValue(), hash3.getKey());
						newHash1.put(hash1.getValue(),hash1.getKey());
						newHash2.put(hash2.getValue(),hash2.getKey());
					}
					else {

						HashObject hash1= new HashObject<Integer>(Integer.parseInt(vs),0);
						HashObject hash2= new HashObject<Integer>(Integer.parseInt(vs),0);
						HashObject hash3 = new HashObject<Integer>(Integer.parseInt(vs),0);
						int hash =hash1.hashCode();
						hash1.setKey(hash);
						hash2.setKey(hash);
						hash3.setKey(hash);
						newHash3.put(hash3.getValue(), hash3.getKey());
						newHash1.put(hash1.getValue(),hash1.getKey());
						newHash2.put(hash2.getValue(),hash2.getKey());
					}


				}
				scan.close();
				filescan.close();
			}

			else if(Integer.parseInt(args[3])==1) {
				Scanner scan = null;
				while(filescan.hasNextLine() && newHash1.getSize() < newHash1.getMaxSize()){
					String line = filescan.nextLine();
					scan = new Scanner(line);
					String vs=" ";
					if(scan.hasNext()) {
						vs= scan.next();
					}
					if(scan.hasNext()) {
						String vd = scan.next();
						HashObject hash1= new HashObject<Long>(Long.parseLong(vs),Integer.parseInt(vd));
						HashObject hash2 = new HashObject<Long>(Long.parseLong(vs),Integer.parseInt(vd));
						HashObject hash3 = new HashObject<Long>(Long.parseLong(vs),Integer.parseInt(vd));
						newHash3.put(hash3.getValue(),hash3.getKey());
						newHash1.put(hash1.getValue(),hash1.getKey());
						newHash2.put(hash2.getValue(),hash2.getKey());
					}
					else {

						HashObject hash1= new HashObject<Long>(Long.parseLong(vs),0);
						HashObject hash2= new HashObject<Long>(Long.parseLong(vs),0);
						HashObject hash3 = new HashObject<Long>(Long.parseLong(vs),0);
						int hash =hash1.hashCode();
						hash1.setKey(hash);
						hash2.setKey(hash);
						hash3.setKey(hash);
						newHash3.put(hash3.getValue(), hash3.getKey());
						newHash1.put(hash1.getValue(),hash1.getKey());
						newHash2.put(hash2.getValue(),hash2.getKey());
					}


				}
				scan.close();
				filescan.close();
			}
			else if(Integer.parseInt(args[3])==2) {
				Scanner scan=null;
				//System.out.println(newHash1.getMaxSize());
				while(filescan.hasNextLine() && newHash1.getSize() < newHash1.getMaxSize()){
					String data= filescan.nextLine();
					scan = new Scanner(data);
					String vs=" ";
					if(scan.hasNext()) {
						vs= scan.next();

					}
					if (scan.hasNext()) {
						String vd = scan.next();
						HashObject hash1= new HashObject<String>(vs,Integer.parseInt(vd));
						HashObject hash2 = new HashObject<String>(vs,Integer.parseInt(vd));
						HashObject hash3 = new HashObject<String>(vs,Integer.parseInt(vd));
						newHash3.put(hash3.getValue(), hash3.getKey());
						newHash1.put(hash1.getValue(),hash1.getKey());
						newHash2.put(hash2.getValue(),hash2.getKey());
					}
					else {
						HashObject hash1= new HashObject<String>(data,0);
						HashObject hash2 = new HashObject<String>(data,0);
						HashObject hash3 = new HashObject<String>(data,0);
						int hash=hash1.hashCode();
						hash1.setKey(hash);
						hash2.setKey(hash);
						hash3.setKey(hash);
						newHash3.put(hash3.getValue(), hash3.getKey());
						newHash1.put(hash1.getValue(),hash1.getKey());
						newHash2.put(hash2.getValue(),hash2.getKey());
					}
				}
				scan.close();
				filescan.close();
			}
			else if(Integer.parseInt(args[3])==3) {
				Scanner scan=null;
				while(filescan.hasNextLine() && newHash1.getSize() < newHash1.getMaxSize()){
					String line =filescan.nextLine();
					scan = new Scanner(line);
					String word=" ";
					if(scan.hasNext()) {
						word= scan.next();

					}
					if(scan.hasNext()) {
						String num = scan.next();
						char data =' ';
						int i=0;
						int input1=0;
						while(i<word.length()) {
							data= word.charAt(i);
							System.out.println(data);
							HashObject hash1= new HashObject<Character>(data,Integer.parseInt(num));
							HashObject hash2= new HashObject<Character>(data,Integer.parseInt(num));
							HashObject hash3 = new HashObject<Character>(data,Integer.parseInt(num));
							newHash3.put(hash3.getValue(),input1);
							newHash1.put(hash1.getValue(),input1);
							newHash2.put(hash2.getValue(),input1);
							i++;
						}
					}
					else {

						char data =' ';
						int i=0;
						int input1=0;
						while(i<word.length()) {
							data= word.charAt(i);
							System.out.println(data);
							HashObject hash1= new HashObject<Character>(data,0);
							HashObject hash2= new HashObject<Character>(data,0);
							HashObject hash3 = new HashObject<Character>(data,0);
							input1 =hash1.hashCode();
							newHash3.put(hash3.getValue(), input1);
							newHash1.put(hash1.getValue(),input1);
							newHash2.put(hash2.getValue(),input1);
							i++;
						}

					}

				}
				scan.close();
				filescan.close();
			}
			DecimalFormat frmt = new DecimalFormat("#.###");
			//String print out according to program execution
			//Linear
			System.out.println("");
			System.out.println("Using Linear Hashing....");
			System.out.println("Inserted " + Math.abs(maxNum- newHash1.getNumDuplicates())+ " " + "elements, of which  " + newHash1.getNumDuplicates() + " duplicates ");
			System.out.println("Average number of probes :"+ frmt.format(newHash1.getAverage()));
			//debug level
			if (args.length==5 &&Integer.parseInt(args[4])==1) {
				newHash1.toString();
			}
			//Double Hashing

			System.out.println("");
			System.out.println("Using Double Hashing....");
			System.out.println("Inserted " +Math.abs(maxNum- newHash2.getNumDuplicates()) + " " + "elements, of which  " + newHash2.getNumDuplicates() + " duplicates ");
			System.out.println("Average number of probes :"+ frmt.format(newHash2.getAverage()));
			//debug level
			if (args.length==5 &&Integer.parseInt(args[4])==1) {
				newHash2.toString();
			}
		//Quadratic
			System.out.println("");
			System.out.println("Using Quadratic....");
			System.out.println("Inserted " +Math.abs(maxNum- newHash3.getNumDuplicates()) + " " + "elements, of which  " + newHash3.getNumDuplicates() + " duplicates ");
			System.out.println("Average number of probes :"+ frmt.format(newHash3.getAverage()));
			//debug level
			if (args.length==5 &&Integer.parseInt(args[4])==1) {
				newHash3.toString();
			}
		
		}

		catch(FileNotFoundException e)
		{
			System.out.println("No file of such name found");
			System.exit(ERROR_EXIT);
		}


	}
	/*
	 * @return boolean true if it is a prime number
	 * @param a int number to determine if it is a prime number or not
	 * Check if the input is a prime number
	 */
	public static boolean isPrime( int num) {
		boolean flag = false;
		for(int i = 2; i <= num/2; ++i)
		{
			// condition for nonprime number
			if(num % i == 0)
			{
				flag = true;
			}
		}
		return flag;


	}




}
