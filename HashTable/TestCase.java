import org.testng.Assert;

/**
 * TestCase class for testing HashTable implementation. 
 * 
 * @author CS 321
 *
 */
public class TestCase
{
		// Objects in the HashTable 
		public static final HashObject<Character> A0 = new HashObject<Character>('A', 0);
		public static final HashObject<Character> A7 = new HashObject<Character>('A', 7);
		public static final HashObject<Character> A14 = new HashObject<Character>('A', 14);
		public static final HashObject<Character> A21 = new HashObject<Character>('A', 21);
		public static final HashObject<Character> A28 = new HashObject<Character>('A', 28);
		public static final HashObject<Character> A35 = new HashObject<Character>('A', 35);
		public static final HashObject<Character> A42 = new HashObject<Character>('A', 42);

		public static final HashObject<Character> B1 = new HashObject<Character>('B', 1);
		public static final HashObject<Character> B8 = new HashObject<Character>('B', 8);
		public static final HashObject<Character> B15 = new HashObject<Character>('B', 15);
		public static final HashObject<Character> B22 = new HashObject<Character>('B', 22);

		public static final HashObject<Character> C2 = new HashObject<Character>('C', 2);
		public static final HashObject<Character> C9 = new HashObject<Character>('C', 9);

		public static final HashObject<Character> D3 = new HashObject<Character>('D', 3);
		public static final HashObject<Character> D10 = new HashObject<Character>('D', 10);
		
		public static final HashObject<Character> E4 = new HashObject<Character>('E', 4);
		public static final HashObject<Character> F5 = new HashObject<Character>('F', 5);
		public static final HashObject<Character> G6 = new HashObject<Character>('G', 6);
		public static final HashObject<Character> H7 = new HashObject<Character>('H', 7);		
		public static final HashObject<Character> I8 = new HashObject<Character>('I', 8);
		
		public static final HashObject<Character> E11 = new HashObject<Character>('E', 11);
		public static final HashObject<Character> F12 = new HashObject<Character>('F', 12);
		public static final HashObject<Character> G13 = new HashObject<Character>('G', 13);
		public static final HashObject<Character> H14 = new HashObject<Character>('H', 14);		
		public static final HashObject<Character> I15 = new HashObject<Character>('I', 15);
										
		public static final HashObject<Character> J9 = new HashObject<Character>('J', 9);
		public static final HashObject<Character> K10 = new HashObject<Character>('K', 10);
		public static final HashObject<Character> L11 = new HashObject<Character>('L', 11);
		public static final HashObject<Character> M12 = new HashObject<Character>('M', 12);		
		public static final HashObject<Character> N13 = new HashObject<Character>('N', 13);
												
		public static final HashObject<Character> J16 = new HashObject<Character>('J', 16);
		public static final HashObject<Character> K17 = new HashObject<Character>('K', 17);
		public static final HashObject<Character> L18 = new HashObject<Character>('L', 18);
		public static final HashObject<Character> M19 = new HashObject<Character>('M', 19);		
		public static final HashObject<Character> N20 = new HashObject<Character>('N', 20);
		
		public static final HashObject<Character> Y0 = new HashObject<Character>('Y', 0);		
		public static final HashObject<Character> Z12 = new HashObject<Character>('Z', 12);

												
		/**
		 * Constructs new HashTable with HashObject<Character> objects for testing. 
		 * Uses default values.
		 *
		 * @param hashType - String representing type of hashing.  
		 * @return new HashTable<Character> 
		 */
		public static HashTable<Character> newHashTable(String hashType)
		{
			// HashTable object to run tests on 
			HashTable<Character> newHashTable = null;
			
			if(hashType.equals("linear"))
			{
				newHashTable = new HashTable<Character>(); 
			}
			else if(hashType.equals("double"))
			{
				newHashTable = new HashTable<Character>(13, 0.75f, OpenAddressType.double_hash);
			}
			else
			{
				newHashTable = new HashTable<Character>(13, 0.75f, OpenAddressType.quadratic);
			}
	
			return newHashTable; 
		}
		
		/**
		 * Constructs new HashTable with Character objects for testing. 
		 * Uses default alpha, OpenAddressType
		 * 
		 * @param capacity - size of HashTable 
		 * @return - new HashTable<Character> 
		 */
		public static HashTable<Character> newHashTable(int capacity)
		{
			// HashTable object to run tests on 
			HashTable<Character> newHashTable = new HashTable<Character>(capacity); 
		
			return newHashTable; 
		}
		
		/**
		 * Constructs new HashTable with Character objects for testing. 
		 * Uses default OpenAddressType
		 * 
		 * @param capacity - size of HashTable 
		 * @param alpha - load factor of HashTable  
		 * @return - new HashTable<Character> 
		 */
		public static HashTable<Character> newHashTable(int capacity, float alpha)
		{
			// HashTable object to run tests on 
			HashTable<Character> newHashTable = new HashTable<Character>(capacity, alpha); 
		
			return newHashTable; 
		}
		
		/**
		 * Constructs new HashTable with HashObject<Character> objects for testing.
		 *  
		 * @param capacity - size of HashTable 
		 * @param alpha - load factor of HashTable  
		 * @param type - type of Open Addressing probing of HashTable  
		 * @return - new HashTable<Character> 
		 */
		public static HashTable<Character> newHashTable(int capacity, float alpha, OpenAddressType type)
		{
			// HashTable object to run tests on 
			HashTable<Character> newHashTable = new HashTable<Character>(capacity, alpha, type); 
		
			return newHashTable; 
		}		
		
		/**
		 * Tests put method. 
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param key - key for given Character
		 * @param value - Character value 
		 */
		public static void put(HashTable<Character> hashTable, Character value, int key)
		{
			hashTable.put(value, key); 
		}
		
		/**
		 * Tests remove method. 
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param key - key for given Character
		 * @param expectedValue - Character value expect to remove  
		 */
		public static void remove(HashTable<Character> hashTable, Character expectedValue, int key)
		{
			Character result = hashTable.remove(expectedValue, key);
			Assert.assertEquals(result, expectedValue); 
		}
		
		/**
		 * Test contains method. 
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param key - key for given Character
		 * @param value - Character value 
		 * @param expectedResult - whether expect to find key/value pair
		 */
		public static void contains(HashTable<Character> hashTable, Character value, int key, boolean expectedResult)
		{
			boolean result = hashTable.contains(value, key);
			Assert.assertEquals(result, expectedResult);
		}


		/**
		 * Tests clear method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 */
		public static void clear(HashTable<Character> hashTable)
		{
			hashTable.clear(); 
		}

		/**
		 * Tests getHash method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param key - key for given Character
		 * @param i - int value, attempts to put an object 
		 * @param expectedHash - what expect hash to be for given key, number of attempts 
		 */
		public static void getHash(HashTable<Character> hashTable, int key, int i, int expectedHash)
		{
			int hash = hashTable.getHash(key, i);
			Assert.assertEquals(hash, expectedHash);
		}

		/**
		 * Tests getType method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param expectedType - expected OpenAddressType 
		 */
		public static void getType(HashTable<Character> hashTable, OpenAddressType expectedType)
		{
			OpenAddressType type = hashTable.getType();
			Assert.assertEquals(type, expectedType);
		}

		/**
		 * Tests getCapacity method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param expectedCapacity - expected capacity 
		 */
		public static void getCapacity(HashTable<Character> hashTable, int expectedCapacity)
		{
			int capacity = hashTable.getCapacity();
			Assert.assertEquals(capacity, expectedCapacity);
		}
		
		/**
		 * Tests getLoadFactor method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param expectedLoadFactor - expected load factor 
		 */
		public static void getLoadFactor(HashTable<Character> hashTable, float expectedLoadFactor)
		{
			float loadFactor = hashTable.getLoadFactor();
			Assert.assertEquals(loadFactor, expectedLoadFactor);
		}	
		
		/**
		 * Tests size method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param expectedSize - expected size (number of objects in table)  
		 */
		public static void size(HashTable<Character> hashTable, int expectedSize)
		{
			int size = hashTable.size();
			Assert.assertEquals(size, expectedSize);
		}		
		
		/**
		 * Tests isEmpty method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param exceptedResult - whether is empty or not 
		 */
		public static void isEmpty(HashTable<Character> hashTable, boolean expectedResult)
		{
			boolean result = hashTable.isEmpty(); 
			Assert.assertEquals(result, expectedResult);
		}	

		/**
		 * Tests getNumProbes method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param expectedNumProbes - expected number of probes 
		 */
		public static void getNumProbes(HashTable<Character> hashTable, int expectedNumProbes)
		{
			int numProbes = hashTable.getNumProbes();
			Assert.assertEquals(numProbes, expectedNumProbes);
		}		
		
		/**
		 * Tests getMaxSize method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param expectedMaxSize - expected maximum size  
		 */
		public static void getMaxSize(HashTable<Character> hashTable, int expectedMaxSize)
		{
			int maxSize = hashTable.getMaxSize();
			Assert.assertEquals(maxSize, expectedMaxSize);
		}		
		
		/**
		 * Tests getFrequency method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param value - Character value 
		 * @param key - key for given Character
		 * @param expectedFrequency - expected frequency for given key/value pair 
		 */
		public static void getFrequency(HashTable<Character> hashTable, Character value, int key, int expectedFrequency)
		{
			int frequency = hashTable.getFrequency(value, key);
			Assert.assertEquals(frequency, expectedFrequency);
		}		
		
		/**
		 * Tests getNumDuplicates method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param expectedNumDuplicates - expected number of duplicates 
		 */
		public static void getNumDuplicates(HashTable<Character> hashTable, int expectedNumDuplicates)
		{
			int maxSize = hashTable.getNumDuplicates();
			Assert.assertEquals(maxSize, expectedNumDuplicates);
		}		
		
}
