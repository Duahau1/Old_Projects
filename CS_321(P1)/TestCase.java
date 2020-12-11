import org.testng.Assert;

/**
 * TestCase class for any implementation of the 
 * ICache interface. 
 * 
 * @author CS 321
 *
 */
public class TestCase
{
		// Elements that can be in the list
		public static final Integer A = new Integer(1);
		public static final Integer B = new Integer(2);
		public static final Integer C = new Integer(3);
		public static final Integer D = new Integer(4);
		public static final Integer E = new Integer(5);
		public static final Integer F = new Integer(6);
		public static final Integer G = new Integer(7);
		public static final Integer H = new Integer(8);
		public static final Integer I = new Integer(9);
		public static final Integer J = new Integer(10);
		
		public static final Integer P = new Integer(17);
		public static final Integer R = new Integer(18);
		public static final Integer S = new Integer(19);
		public static final Integer T = new Integer(20);
		public static final Integer U = new Integer(21);
		public static final Integer V = new Integer(22);
		public static final Integer W = new Integer(23);
		public static final Integer X = new Integer(24);
		public static final Integer Y = new Integer(25);
		public static final Integer Z = new Integer(26);
		
		// Marginal of allowable difference between double values
		private static final double EPSILON = 0.001; 
						
		/**
		 * Constructs new cache for testing. 
		 * 
		 * @return new cache 
		 */
		public static ICache<Integer> newCache(int size)
		{
			ICache<Integer> newCache = new Cache<Integer>(size);
		
			return newCache; 
		}
		
		/**
		 * Tests get method
		 * @param cache - implementation of ICache interface
		 * @param target - Integer object
		 * @param expectedResult - Integer object
		 */
		public static void get(ICache<Integer> cache, Integer target, Integer expectedResult)
		{
			Integer result = cache.get(target); 
			Assert.assertEquals(result, expectedResult);
		}

		/**
		 * Tests clear method
		 * @param cache - implementation of ICache interface
		 */
		public static void clear(ICache<Integer> cache)
		{
			cache.clear(); 
		}

		/**
		 * Tests add method
		 * @param cache - implementation of ICache interface
		 * @param element - Integer object
		 */
		public static void add(ICache<Integer> cache, Integer element)
		{
			cache.add(element); 
		}

		/**
		 * Tests removeLast method
		 * @param cache - implementation of ICache interface
		 */
		public static void removeLast(ICache<Integer> cache)
		{
			cache.removeLast(); 
		}

		/**
		 * Tests remove method
		 * @param cache - implementation of ICache interface
		 * @param target - Integer object
		 */
		public static void remove(ICache<Integer> cache, Integer target)
		{
			cache.remove(target); 
		}

		/**
		 * Tests write method
		 * @param cache - implementation of ICache interface
		 * @param element - Integer object
		 */
		public static void write(ICache<Integer> cache, Integer element)
		{
			cache.write(element); 
		}

		/**
		 * Tests getHitRate method
		 * @param cache - implementation of ICache interface
		 * @param rate - double value
		 */
		public static void getHitRate(ICache<Integer> cache, double rate)
		{
			double result = cache.getHitRate(); 
			Assert.assertTrue(Math.abs(rate - result) < EPSILON);
		}	

		/**
		 * Tests getMissRate method
		 * @param cache - implementation of ICache interface
		 * @param rate - double value
		 */
		public static void getMissRate(ICache<Integer> cache, double rate)
		{
			double result = cache.getMissRate(); 
			Assert.assertTrue(Math.abs(rate - result) < EPSILON);
		}	

		/**
		 * Tests isEmpty method
		 * @param cache - implementation of ICache interface
		 * @param excepted - boolean value
		 */
		public static void isEmpty(ICache<Integer> cache, boolean expectedResult)
		{
			boolean result = cache.isEmpty(); 
			Assert.assertEquals(result, expectedResult);
		}	

}
