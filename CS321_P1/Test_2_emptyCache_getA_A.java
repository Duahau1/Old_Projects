import java.util.NoSuchElementException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Testing for ICache interface implementation: 
 * Tests for Change Scenario: empty cache -> get(A) -> A
 * 
 * @author CS 321
 */
public class Test_2_emptyCache_getA_A
{
	// Cache running tests on
	ICache<Integer> cache;
	
	//****** Constants used in tests *****************
			// An element in cache 
			private static final Integer VALID_ELEMENT = TestCase.A; 
			// An element not in cache 
			private static final Integer ELEMENT = TestCase.J; 
			// Another element not in cache - used for negative testing 
			private static final Integer INVALID_ELEMENT = TestCase.Z;
			// Hit rate of the cache
			private static final double HIT_RATE = 0.0;
			// Miss rate of the cache
			private static final double MISS_RATE = 1.0;  
			
			//********************Before Each Test Method********************
			/**
			 * Sets up cache for testing.
			 * @param cacheSize - int value
			 */
			@BeforeMethod
			@Parameters("cacheSize")		
			public void initialize(String cacheSize)
			{
				// parse parameter 
				int size = Integer.parseInt(cacheSize);
				// create an empty cache
				cache = TestCase.newCache(size);
				// run change scenario
				cache.get(TestCase.A); 
			}
			
			//******************* Tests ***************************
			/**
			 * Test: get(target) - retrieve target from cache
			 * Expected Result: No exceptions
			 */
			@Test()
			public void testGet_validElement()
			{
				TestCase.get(cache, VALID_ELEMENT, VALID_ELEMENT);
			}
			
			/**
			 * Test: get(target) - try to retrieve target from cache
			 * Expected Result: No exceptions
			 */
			@Test()
			public void testGet_invalidElement()
			{
				TestCase.get(cache, INVALID_ELEMENT, null);
			}

			/**
			 * Test: clear() - remove all entries from cache 
			 * Expected Result: No exceptions
			 */
			@Test()
			public void testClear()
			{
				TestCase.clear(cache);
			}

			/**
			 * Test: add(element) - adds element to top of cache 
			 * Expected Result: No exceptions
			 */
			@Test()
			public void testAdd()
			{
				TestCase.add(cache, ELEMENT);
			}
			
			/**
			 * Test: removeLast() - remove last entry in cache 
			 * Expected Result: No exceptions
			 */
			@Test()
			public void testRemoveLast()
			{
				TestCase.removeLast(cache);
			}

			/**
			 * Test: remove(target) - remove target element from cache 
			 * Expected Result: No exception
			 */
			@Test()
			public void testRemove_validElement()
			{
				TestCase.remove(cache, VALID_ELEMENT);
			}

			/**
			 * Test: remove(target) - try to remove target element from cache 
			 * Expected Result: NoSuchElementException
			 */
			@Test(expectedExceptions = NoSuchElementException.class)
			public void testRemove_invalidElement()
			{
				TestCase.remove(cache, INVALID_ELEMENT);
			}

			/**
			 * Test: write(target) - write target to cache  
			 * Expected Result: No exception 
			 */
			@Test()
			public void testWrite_validElement()
			{
				TestCase.write(cache, VALID_ELEMENT);
			}

			/**
			 * Test: write(target) - try to write target to cache  
			 * Expected Result: NoSuchElementException 
			 */
			@Test(expectedExceptions = NoSuchElementException.class)
			public void testWrite_invalidElement()
			{
				TestCase.write(cache, INVALID_ELEMENT);
			}

			/**
			 * Test: getHitRate() - number of hits for the cache  
			 * Expected Result: HIT_RATE (0.0)
			 */
			@Test
			public void testGetHitRate()
			{
				TestCase.getHitRate(cache, HIT_RATE);
			}
			
			/**
			 * Test: getMissRate() - 1 - hit rate for the cache  
			 * Expected Result: MISS_RATE (1.0)
			 */
			@Test
			public void testGetMissRate()
			{
				TestCase.getMissRate(cache, MISS_RATE);
			}

			/**
			 * Test: isEmpty() - whether cache is empty 
			 * Expected Result: false
			 */
			@Test
			public void testIsEmpty()
			{
				TestCase.isEmpty(cache, false);
			}

}
