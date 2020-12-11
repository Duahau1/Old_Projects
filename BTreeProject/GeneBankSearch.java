import java.io.*;
import java.util.*;
/*
 * Search for keys in the BTree
 */
public class GeneBankSearch {

	public static void main(String[] args) 
	{
		boolean hasCache = false;
		TreeObject insearchedObject = new TreeObject(0);
		TreeObject foundObject = new TreeObject(0);
		String btreeFile = null;
		String queryFile= null;
		int cacheSize = 0;
		int debugLevel = 0;
		
		String tempString = null;
		boolean displayMode = false;
		if(args.length<1) {
			System.err.println("Usage:[0 | 1(no/with Cache)] [btree file] [query file] [ | cache size] [ | debug level]\n");
			return;
		}
		if(args[0].equals("0"))
		{
			hasCache = false;
			btreeFile = args[1];
			queryFile = args[2];
			debugLevel = Integer.parseInt(args[3]);
			displayMode = true;
			
			if(debugLevel != 0)
			{
				System.err.println("<Debug Level)>: only 0. \n");
				displayMode = false;
				return;
			}

			if(args.length != 4)
			{
				System.err.println("Usage:[0 | 1(no/with Cache)] [btree file] [query file] [ | cache size] [ | debug level]\n");
				displayMode = false;

				if(btreeFile == null){
					System.err.println("Enter accurate BTree file name.");
					return;
				}

				if(queryFile == null){
					System.err.println("Enter accurate query file name.");
					return;
				}

				return;
			}

			

		}
		else if(!(args[0].equals("0") || args[0].equals("1")))
		{
			System.err.println("<0/1 (no/with Cache)>: Input argument 0 or 1");
			displayMode = false;
			return;
		}


		try {
			BTree b = new BTree(btreeFile);
			RandomAccessFile file = new RandomAccessFile(btreeFile, "rw");			
			Scanner queryScan = new Scanner(new File(queryFile));
			Scanner lenScan = new Scanner(new File(queryFile));
			String firstline=null;
			firstline=lenScan.nextLine();
			int seqLength= firstline.length();
			lenScan.close();
			BTreeConvert data = new BTreeConvert(seqLength);
			if(displayMode == true){
				System.out.println("Searching Table");
				
				System.out.println("#####################");
				System.out.println("In searched sequence : frequency ");
			}
			String binaryString = "";
			int count=0;
			while(queryScan.hasNext()) {
				tempString = queryScan.nextLine();
				for(int index = 0; index < seqLength; index++) {
					
					if(tempString.charAt(index) == 'A') {
						binaryString += "00";
					}
					if(tempString.charAt(index) == 'C') {
						binaryString += "01";
					}
					if(tempString.charAt(index) == 'G') {
						binaryString += "10";
					}
					if(tempString.charAt(index) == 'T') {
						binaryString += "11";
					}
				}
			
			}
			
		
			long searchData;
			int track=0;
			int binaryLen= seqLength*2;
			int lengthSeq= 0;
			System.out.println("The binary length is "+binaryString.length());
			//TreeObject test =new TreeObject(36);
			
			//System.out.println("This "+data.getDNAString(3,36) );
			//System.out.println(b.bTreeSearch(b.root,test));
			while(track!=binaryString.length()) {
				lengthSeq=  track+binaryLen;
				searchData= Long.parseLong(binaryString.substring(track,lengthSeq),2);
				//System.out.println(searchData);	
				insearchedObject.setKey(searchData);
				insearchedObject.setFrequency(1);
				foundObject = b.bTreeSearch(b.root, insearchedObject);
				
				if(foundObject != null & displayMode == true){
					System.out.println(insearchedObject.getKey());	
					System.out.println(BTreeConvert.getDNAString(seqLength,searchData) + " : " + foundObject.getFrequency());
				
				}

				track+=binaryLen;	
			}
			
			if(displayMode == true){
				System.out.println("#####################");
			}
			queryScan.close(); 
		} 

		catch (Exception e) {
			// TODO Auto-generated catch block
			if(args.length > 5 || debugLevel != 0) {
				System.err.println("CHECK YOUR INPUT! Usage: <0/1 (no/with Cache)> <btree file> <query file> [<debug level>]");
			}
			e.printStackTrace();
		}


	}
	
}
