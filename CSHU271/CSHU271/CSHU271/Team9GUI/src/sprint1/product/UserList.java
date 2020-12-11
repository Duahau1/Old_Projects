package Team9GUI.src.sprint1.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author Tuan
 *A list of all users and their information
 */
public class UserList{

private ArrayList<User> userList;
    
    /**
     * Constructor: Builds a userList
     */
    public UserList() {
    	userList = new ArrayList<User>();
    	File temp = new File("user9.txt");
    	if(temp.exists()) {
    		readUsers();
    	}
    }
    
    /**
     * @param userName
     * @param password
     * @param email
     * Adds a new user to the list if that user doesn't already exist
     * (their username or email doesn't already exist)
     */
    public void addUser(String userName, String password, String email){
    	if (!doesUserNameExist(userName) && !doesEmailExist(email)) 
    		userList.add(new User(userName,password,email));
    		writeUsers();
    }
    
    /**
     * @param userName
     * @return a boolean
     * Checks if a username already exists
     */
    public boolean doesUserNameExist(String userName){
    	for (User user: userList) {
    		if(user.matchUserName(userName)) {   
    			return true;   
    		}	
    	}
       return false;
    }
    
    /**
     * @param userName
     * @param password
     * @return a User object
     * Searches the list and find the user that has the matching username and password
     */
    public User findUser(String userName, String password){
    	for (User user: userList) {
    		if(user.isValidLogin(userName, password)) {   
    			return user;   
    		}	
    	}
       return null;
    }
    
    /**
     * @param email
     * @return a boolean
     * Checks if an email already exists
     */
    public boolean doesEmailExist(String email){
    	for (User user: userList)
    		if(user.matchEmail(email))   
    			return true;   
       return false;
    }
    /**
     * write userList to a file so it can be retrieved
     */
    private void writeUsers() {
    	try {
    		FileOutputStream users=new FileOutputStream("user9.txt");
    		ObjectOutputStream write = new ObjectOutputStream(users);
    		write.writeObject(userList);
			write.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    /**
     * retrieves userList from file
     */
    private void readUsers() {
    	try {
			FileInputStream  users= new FileInputStream("user9.txt");
			ObjectInputStream read= new ObjectInputStream(users);
			userList= (ArrayList<User>) read.readObject();
			read.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}