
public class BTreeTester {

	public static void main(String[] args) {
		BTree testTree;
		int size = 10;
		int degree = 4;
		int numTest = 0;
		int testPassed = 0;
		System.out.println("Test: Creating an empty btree");
		try {
			testTree = new BTree(degree, size);
			testPassed++;
			System.out.println(" Test passed " + '\n');
		} catch (Exception e) {
			System.out.println("Test failed" + '\n');
		}
		
		//Test if the tree is empty
		System.out.println(" test empty: is empty");
		if(testTree.isEmpty) {
			System.out.println(" test passed" + '\n');
		}
		else {
			System.out.println("Test Failed" + '\n');
		}
		
		//Test insert 1
		numTest++;
		try{
			if(testTreeInsert(testTree, 1, "Insert 1")){
				System.out.println("Test Passed " + '\n');
				testPassed++;
			}
			else {
				System.out.println("Test Failed " + '\n');
			}
		}catch(Exception e) {
			System.out.println("Test Failed" + '\n');
		}
		
		//Test insert 2 after 1
		numTest++;
		try{
			if(testTreeInsert(testTree, 2, "Insert 2")){
				if(testTreeInsert.getNode())
				System.out.println("Test Passed " + '\n');
				testPassed++;
			}
			else {
				System.out.println("Test Failed " + '\n');
			}
		}catch(Exception e) {
			System.out.println("Test Failed" + '\n');
		}
		
		//test
		
	}

	/**
	 * inserts an object from the tree with the specified key
	 * 
	 * @param practiceNode
	 * @param key
	 * @param testName
	 * @return
	 */
	static boolean testTreeInsert(BTree testTree, long key, String testName) {
		TreeObject practiceObject = new TreeObject(key);
		System.out.println("Test Insert : " + testName);
		try {
			testTree.addNode(practiceObject);
			return true;

		} catch (Exception e) {
			System.out.println("node is full");
			return false;
		}
	}

	/**
	 * removes an object from the btree
	 * 
	 * @param practiceNode
	 * @param key
	 * @param testName
	 * @return
	 */
	static boolean testTreeRemove(BTree testTree, long key, String testName) {
		TreeObject practiceObject = new TreeObject(key);
		System.out.println("Test remove : " + testName);

		if (testTree.removeNode(practiceObject) != null) {
			return true;
		} else {
			System.out.println("Object not found");
			return false;
		}
	}
}
