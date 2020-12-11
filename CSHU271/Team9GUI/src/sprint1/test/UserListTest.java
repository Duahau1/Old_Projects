package Team9GUI.src.sprint1.test;

import junit.framework.TestCase;
import Team9GUI.src.sprint1.product.User;
import Team9GUI.src.sprint1.product.UserList;
/**
 * @author Tuan
 * Test class for UserList class
 */
public class UserListTest extends TestCase {

	private UserList userList;
	private User newUser = new User("Tuan","Bronco2020","tuan@boisestate.edu");
	
	protected void setUp() throws Exception {
		super.setUp();
		userList = new UserList();
		userList.addUser("Tuan","Bronco2020","tuan@boisestate.edu");
	}

	public void testExistingUserAccount() {
		assertTrue(userList.doesUserNameExist("Tuan"));
		assertTrue(userList.doesEmailExist("tuan@boisestate.edu"));
		User user = userList.findUser("Tuan", "Bronco2020");
		assertTrue(newUser.matchUserName(user.getUsername()));
		assertTrue(newUser.matchEmail(user.getEmail()));
		assertTrue(newUser.matchPassword(user.getPassword()));
	}

	public void testNonExistantUserAccount() {
		assertFalse(userList.doesUserNameExist("Van"));
		assertFalse(userList.doesEmailExist("Van@boisestate.edu"));
		assertNull(userList.findUser("Tuan", "Idaho2020"));
	}

}