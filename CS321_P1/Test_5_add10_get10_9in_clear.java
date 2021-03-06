import java.util.NoSuchElementException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Testing for ICache interface implementation: 
 * Tests for Change Scenario: add 10 -> get 10 (9 in) ->clear
 * 
 * @author CS 321
 */

public class Test_5_add10_get10_9in_clear
{
	// Cache running tests on
	ICache<Integer> cache;
	
	//****** Constants used in tests *****************
			// An element not in cache 
			private static final Integer ELEMENT = TestCase.P; 
			// Another element not in cache - used for negative testing 
			private static final Integer INVALID_ELEMENT = TestCase.Z;
			// Hit rate of the cache
			private static final double HIT_RATE = 0.9;
			// Miss rate of the cache
			private static final double MISS_RATE = 0.1;  
			
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
				
				// add elements to cache
				cache.add(TestCase.A); 
				cache.add(TestCase.B); 
				cache.add(TestCase.C); 
				cache.add(TestCase.D); 
				cache.add(TestCase.E); 
				cache.add(TestCase.F); 
				cache.add(TestCase.G); 
				cache.add(TestCase.H); 
				cache.add(TestCase.I);
				cache.add(TestCase.Y); 
				
				// 10 gets - 9 in
				cache.get(TestCase.A); 
				cache.get(TestCase.B); 
				cache.get(TestCase.C); 
				cache.get(TestCase.D); 
				cache.get(TestCase.E); 
				cache.get(TestCase.F); 
				cache.get(TestCase.G); 
				cache.get(TestCase.H); 
				cache.get(TestCase.I);
				cache.get(TestCase.J); 
				
				// empty cache
				cache.clear(); 

			}
			
			//******************* Tests ***************************
			/**
			 * Test: get(target) - try to retrieve target from cache
			 * Expected Result: No exceptions
			 */
			@Test()
			public void testGet()
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
			 * Test: removeLast() - try to remove last entry in cache 
			 * Expected Result: IllegalStateException
			 */
			@Test(expectedExceptions = IllegalStateException.class)
			public void testRemoveLast()
			{
				TestCase.removeLast(cache);
			}

			/**
			 * Test: remove(target) - try to remove target element from cache 
			 * Expected Result: NoSuchElementException
			 */
			@Test(expectedExceptions = NoSuchElementException.class)
			public void testRemove()
			{
				TestCase.remove(cache, INVALID_ELEMENT);
			}

			/**
			 * Test: write(target) - try to write target to cache  
			 * Expected Result: NoSuchElementException 
			 */
			@Test(expectedExceptions = NoSuchElementException.class)
			public void testWrite()
			{
				TestCase.write(cache, INVALID_ELEMENT);
			}

			/**
			 * Test: getHitRate() - number of hits for the cache  
			 * Expected Result: HIT_RATE (0.5)
			 */
			@Test
			public void testGetHitRate()
			{
				TestCase.getHitRate(cache, HIT_RATE);
			}
			
			/**
			 * Test: getMissRate() - 1 - hit rate for the cache  
			 * Expected Result: MISS_RATE (0.5)
			 */
			@Test
			public void testGetMissRate()
			{
				TestCase.getMissRate(cache, MISS_RATE);
			}

			/**
			 * Test: isEmpty() - whether cache is empty 
			 * Expected Result: true
			 */
			@Test
			public void testIsEmpty()
			{
				TestCase.isEmpty(cache, true);
			}

}
