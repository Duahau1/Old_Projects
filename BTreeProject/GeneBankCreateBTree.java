import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/*
 * Create a BTree using a given gbk file
 */
public class GeneBankCreateBTree {

	public static void main(String[] args) throws IOException {

		boolean hasCache = false;
		int degree = 0;
		String gbkFile = null ;
		int seqLen =0;
		//int cacheSize=0;
		int debugLevel=0;
		if(args.length<1) {
			System.err.println("Usage:[0 | 1(no/with Cache)] [degree] [gbk file] [sequence length] [ | cache size] [ | debug level]\n");
			return;
		}

		if(args[0].equals("0"))
		{
			hasCache = false;
			degree = Integer.parseInt(args[1]);
			gbkFile = args[2];
			seqLen = Integer.parseInt(args[3]);
			debugLevel = Integer.parseInt(args[4]);

			if(degree == 0){
				degree =getOptimalDegree() ; 
			}

			if(gbkFile == null){
				System.err.println("Invalid gbk file.");
				return;
			}

			if(!(args[4].equals("0") || args[4].equals("1")))
			{
				System.err.println("<Debug Level>:[0|1]\n");

				if(args[4].equals("0"))
				{
					System.err.println("Debug = 1: Output to the \"dump \" file ");
				}
				return;
			}

			if(!(args.length == 5))
			{
				System.err.println("Usage:[0 | 1(no/with Cache)] [degree] [gbk file] [sequence length] [ | cache size] [ | debug level]\n");
				return;
			}
		}
		else if(!(args[0].equals("0") || args[0].equals("1")))
		{
			System.err.println("<0/1 (no/with Cache)>\n");
			return;
		}

		else if (Integer.parseInt(args[3]) < 1 || 31 < Integer.parseInt(args[3]))
		{
			System.err.println("<Sequence Length>: 1 <= k <= 31  \n");
			return;
		}

		String treefname = gbkFile+".btree.data."+seqLen+"."+degree;
		BTree tree = new BTree(degree, treefname);
		try {
			BTreeData input = new BTreeData(gbkFile,seqLen);
			while(input.hasNext()) {
				TreeObject object = new TreeObject(input.Next());
				tree.bTreeInsert(object);
			}
			if(debugLevel == 1) {
				File dumpFile = new File("dump");
				dumpFile.delete();
				dumpFile.createNewFile();
				FileWriter fw = new FileWriter(dumpFile);
				tree.inOrderTraversal(tree.root, seqLen, fw);
				fw.close();
			} 	
			tree.bTreeClose();

		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	/**
	 * To find the optimal degree of the BTree when the user puts in 0 in the degree spot
	 * @return the optimal degree
	 */
	
	private static int getOptimalDegree() {

        double optimal= 4096;
        int Pointer = 4;// 1 int is 4 bytes
        int Object = 12 ;
        int Metadata =12 ;
        optimal += Object;
        optimal -= Pointer;
        optimal -= Metadata;
        optimal /= (2 * (Object + Pointer));
        int retVal= (int) Math.floor(optimal);
        return retVal;
	}
}
