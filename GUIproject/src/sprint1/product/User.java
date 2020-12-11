package src.sprint1.product;

import java.io.Serializable;

/**
 * @authors Van, Tuan
 * Represents a user (an account). Each account
 * has a username, password, and email.
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username,passwd;
	private String email;
	private String answer;
	private int count;

	/**
	 * Constructor: Builds a user using the given parameters.
	 * @param username
	 * @param passwd
	 * @param email
	 */
	public User(String username,String passwd, String email) {
		this.username=username;
		this.passwd=passwd;
		this.email=email;
		answer="";
		count=0;
	}
	/**
	 * Returns the answer of the security question
	 * @return answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer
	 * Sets the answer of the security question
	 */
	public void setAnswer(String answer) {
		this.answer=answer;
	}	
	/**
	 * Returns the username of this user
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 * Sets the username of this user
	 */
	public void setUserName(String username) {
		this.username=username;
	}

	/**
	 * Returns the password of this user
	 * @return passwd
	 */
	public String getPassword() {
		return passwd;
	}

	/**
	 * @param passwd
	 * Sets the password of this user
	 */
	public void setPasswd(String passwd) {
		this.passwd= passwd;
	}

	/**
	 * @return email
	 * Returns the email of this user
	 */
	public String getEmail(){
		return email;
	}

	/**
	 * @param email
	 * Sets the email of this user
	 */
	public void setEmail(String email) {
		this.email=email;
	}

	/**
	 * @param email
	 * @return a boolean
	 * Checks if an email is in the format local-part@domain
	 */
	public boolean checkEmail(String email) {
		boolean valid=false;
		for(int i=0;i< email.length();i++) {
			if (email.charAt(i)=='@') {
				valid=true;
				count++;
			}

		}
		int out=count;
		count=0;
		return (out==1 && valid);
	}

	/**
	 * @param passwd
	 * @return a boolean
	 * Checks if a password has at least 8 characters containing
	 * at least an uppercase letter, a lowercase letter, and a number
	 */
	public boolean checkPasswd(String passwd) {
		boolean upperCase=false;
		boolean lowerCase=false;
		boolean digit=false;

		char c=' ';
		for (int i=0; i<passwd.length();i++) {
			c=passwd.charAt(i);
			if(Character.isUpperCase(c)) {
				upperCase=true;
			}
			if(Character.isLowerCase(c)) {
				lowerCase=true;
			}
			if(Character.isDigit(c)) {
				digit=true;
			}
		}
		return (upperCase && lowerCase && digit && passwd.length()>=8);
	}

	/**
	 * @param userName
	 * @param password
	 * @return a boolean
	 * Checks if a username and password match with those of the user (a valid login)
	 */
	public boolean isValidLogin(String userName, String password) {
		return matchUserName(userName) && matchPassword(password);
	}

	/**
	 * @param userName
	 * @return a boolean
	 * Checks if a username matches with the user's username
	 */
	public boolean matchUserName(String userName) {
		return userName != null && userName.equals(this.username);
	}
	/**
	 * @param answer
	 * @return a boolean
	 * Checks if the given answer matches with the user's answer
	 */
	public boolean matchAnswer(String answer) {
		return answer!=null && answer.equals(this.answer);
	}
	
	/**
	 * @param password
	 * @return a boolean
	 * Checks if a password matches with the user's password
	 */
	public boolean matchPassword(String password) {
		return checkPasswd(passwd) && password.equals(this.passwd);
	}

	/**
	 * @param email
	 * @returna boolean
	 * Checks if an email matches with the user's email
	 */
	public boolean matchEmail(String email) {
		return checkEmail(email) && email.equals(this.email);
	}
}
