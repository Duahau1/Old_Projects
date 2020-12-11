package src.sprint1.test;

import junit.framework.TestCase;
import src.sprint1.product.User;

/**
 * @author Tuan, Van
 * Test class for User class
 */
public class UserTest extends TestCase {

	private User user;
	
	public void testNewAccount() {
		user = new User("Tuan", "Bronco2020","tuan@boisestate.edu");
		assertNotNull(user);
		assertTrue(user.matchUserName("Tuan"));
		assertTrue(user.checkPasswd("Bronco2020"));
		assertTrue(user.checkEmail("tuan@boisestate.edu"));
		assertTrue(user.matchEmail("tuan@boisestate.edu"));
		assertTrue(user.isValidLogin("Tuan", "Bronco2020"));
		
		assertFalse(user.matchUserName("Van"));
		assertFalse(user.checkPasswd("Bsu20"));
		assertFalse(user.checkPasswd("boisestate"));
		assertFalse(user.checkPasswd("BOISESTATE"));
		assertFalse(user.checkPasswd("12345678"));
		assertFalse(user.checkEmail("tuanboisestate.edu"));
		assertFalse(user.matchEmail("van@boisestate.edu"));
		assertFalse(user.isValidLogin("Tuan", "Bronco2022"));
		assertFalse(user.isValidLogin("Van", "Bronco2020"));
	}
		public void testSecurityQuestion(){
			
			//no edit
			user = new User("Tuan", "Bronco2020","tuan@boisestate.edu");
			assertFalse(user.matchAnswer("Boise"));
			
			//modify the answer (wrong answer)
			user.setAnswer("Meridian");
			assertFalse(user.matchAnswer("Boise"));
			
			//modify the answer (right answer)
			user.setAnswer("Boise");
			assertTrue(user.matchAnswer("Boise"));
		
				
		}

}
