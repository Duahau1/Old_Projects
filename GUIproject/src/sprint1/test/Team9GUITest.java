package src.sprint1.test;

import org.junit.Test;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import src.sprint1.product.Team9GUI;

/**
 * @author Tuan
 * Test class for Team9GUI. The user has to manually close each pop-up message
 * (by clicking OK) after each test case.
 */
public class Team9GUITest extends JFCTestCase  {
	private Team9GUI team9GUI = null;
	private TestHelper helper = null;

	protected void setUp() throws Exception {
		super.setUp();
		helper = new JFCTestHelper();
		team9GUI = new Team9GUI();
	}

	protected void tearDown() throws Exception {
		team9GUI = null;
		JFCTestHelper.cleanUp(this);
		super.tearDown();
	}

	private void emptySignUpFields() {
		team9GUI.signUpButton.doClick();
	}

	private void invalidEmail() {
		team9GUI.userSignUp.setText("name");
		team9GUI.emailSignUp.setText("namegmail.com");
		team9GUI.passwordSignUp.setText("Bronco2020");
		team9GUI.signUpButton.doClick();
	}

	private void shortPassword() {
		team9GUI.userSignUp.setText("name");
		team9GUI.emailSignUp.setText("name@gmail.com");
		team9GUI.passwordSignUp.setText("Bsu2020");
		team9GUI.signUpButton.doClick();
	}

	private void noUppercasePassword() {
		team9GUI.userSignUp.setText("name");
		team9GUI.emailSignUp.setText("name@gmail.com");
		team9GUI.passwordSignUp.setText("bronco2020");
		team9GUI.signUpButton.doClick();
	}

	private void noLowercasePassword() {
		team9GUI.userSignUp.setText("name");
		team9GUI.emailSignUp.setText("name@gmail.com");
		team9GUI.passwordSignUp.setText("BRONCO2020");
		team9GUI.signUpButton.doClick();
	}

	private void noDigitPassword() {
		team9GUI.userSignUp.setText("name");
		team9GUI.emailSignUp.setText("name@gmail.com");
		team9GUI.passwordSignUp.setText("BoiseState");
		team9GUI.signUpButton.doClick();
	}

	private void validSignUp() {
		team9GUI.userSignUp.setText("name");
		team9GUI.emailSignUp.setText("name@gmail.com");
		team9GUI.passwordSignUp.setText("Bronco2020");
		team9GUI.signUpButton.doClick();
	}

	private void usedUserName() {
		team9GUI.signUpLink.doClick();
		team9GUI.userSignUp.setText("name");
		team9GUI.emailSignUp.setText("name2@gmail.com");
		team9GUI.passwordSignUp.setText("Bronco2020");
		team9GUI.signUpButton.doClick();
	}

	private void usedEmail() {
		team9GUI.userSignUp.setText("name2");
		team9GUI.emailSignUp.setText("name@gmail.com");
		team9GUI.passwordSignUp.setText("Bronco2020");
		team9GUI.signUpButton.doClick();
	}

	// For the first time, expected message "Please fill in all fields." 
	//as the answer field for the security question is empty
	private void accountNoEdit() {
		team9GUI.editButton.doClick();
		team9GUI.doneButton.doClick();
	}
	//Answer the security question without touching any other fields
	//Expected message "Your account has been updated"
	private void answerEdit() {
		team9GUI.editButton.doClick();
		team9GUI.answerfield.setText("Boise");
		team9GUI.doneButton.doClick();
	}	

	// expected message "Please fill in all fields." 
	//as the answer field for the security question is empty
	private void accountNoEdit() {
		team9GUI.editButton.doClick();
		team9GUI.answerfield.setText("");
		team9GUI.doneButton.doClick();
	}

	//forget username empty email
	//expect message "Please enter a valid email address"
	private void forgetUEmptyEmail() {
		team9GUI.forgetUsernameButton.doClick();
		team9GUI.confirmEmailButton.doClick();
	}
	//forget username invalid email
	//expect message "Please enter a valid email address"
	private void forgetUInvalidEmail() {
		team9GUI.forgetUsernameButton.doClick();
		team9GUI.emailfieldU.setText("KKKKKKK&1");
		team9GUI.confirmEmailButton.doClick();
	}
	//forget username incorrect email
	//expect message "Your email is incorrect"
	private void forgetUIncorrectEmail() {
		team9GUI.forgetUsernameButton.doClick();
		team9GUI.emailSignUp.setText("bronco@gmail.com");
		team9GUI.emailfieldU.setText("bsu@gmail.com");
		team9GUI.confirmEmailButton.doClick();
	}
	//forget username right email
	//expect message "Here is your username:(username)"
	private void forgetURightEmail() {
		team9GUI.forgetUsernameButton.doClick();
		team9GUI.emailSignUp.setText("bsu1@gmail.com");
		team9GUI.emailfieldU.setText("bsu1@gmail.com");
		team9GUI.confirmEmailButton.doClick();
	}

	//For the first time, forget password empty email or empty answer field
	//expect message "Please fill in all field"
	private void forgetPEmptyEmail() {
		team9GUI.forgetPasswordButton.doClick();
		team9GUI.continueButton.doClick();
	}
	//forget password invalid email
	//expect message "Please enter a valid email address"
	private void forgetPInvalidEmail() {
		team9GUI.emailFieldP.setText("KKKKKKK&1");
		team9GUI.answerFieldP.setText("boi");
		team9GUI.continueButton.doClick();
	}
	//forget password incorrect email
	//expect message "Your email is incorrect"
	private void forgetPIncorrectEmail() {
		team9GUI.emailSignUp.setText("bronco@gmail.com");
		team9GUI.emailfieldP.setText("bsu@gmail.com");
		team9GUI.answerFieldP.setText("boi");
		team9GUI.continueButton.doClick();
	}
	// forget password empty email or empty answer 
	//expected message "Please fill in all field"
	private void forgetPEmptyEmail{
		team9GUI.emailfieldP.setText("");
		team9GUI.answerFieldP.setText("bios");
		team9GUI.continueButton.doClick();
	}
	
	//forget password empty email or empty answer
	//Expected message "Please fill in all field"
	private void forgetPRightEmail() {
		team9GUI.emailSignUp.setText("bsu1@gmail.com");
		team9GUI.emailfieldP.setText("bsu1@gmail.com");
		team9GUI.answerFieldP.setText("");
		team9GUI.continueButton.doClick();
	}

	//security question correct email and incorrect answer 
	//Expected message "Your answer is incorrect"
	private void IncorrectAnswer() {
		team9GUI.emailSignUp.setText("bsu1@gmail.com");
		team9GUI.emailfieldP.setText("bsu1@gmail.com");
		team9GUI.answerField.setText("Boise1");
		team9GUI.answerFieldP.setText("Bose");
		team9GUI.continueButton.doClick();			
	}
	//security question correct email and correct answer
	//Expected message "Your answer is correct"
	private void correctAnswer() {
		team9GUI.emailSignUp.setText("bsu1@gmail.com");
		team9GUI.emailfieldP.setText("bsu1@gmail.com");
		team9GUI.answerField.setText("Boise2");
		team9GUI.answerFieldP.setText("Boise2");
		team9GUI.continueButton.doClick();			
	}
	
	
	//change password field no digit password and match
	//Expected message "Please enter a valid password"
	private void noDigitNewPassword() {
		team9GUI.newPassword.setText("Boisestate");
		team9GUI.changePassword.doClick();
	}
	
	//change password field no uppser case password and match
		//Expected message "Please enter a valid password"
		private void noUpperCaseNewPassword() {
			team9GUI.newPassword.setText("boisestate1");
			team9GUI.changePassword.doClick();
		
		}
		//change password field no lower case password and match
		//Expected message "Please enter a valid password"
		private void noLowerCaseNewPassword() {
			team9GUI.newPassword.setText("BOISESTATE1");
			team9GUI.changePassword.doClick();
		
		}	
		//change password field short password and match
		//Expected message "Please enter a valid password"
				private void shortNewPassword() {
					team9GUI.newPassword.setText("Boise1");
					team9GUI.changePassword.doClick();
				
				}		
		//change password valid password
		// Expected message "Your password has been changed"
				private void validNewPassword() {
					team9GUI.newPassword.setText("Boisestate123");
					team9GUI.changePassword.doClick();
				}

	private void emptyLoginFields() {
		team9GUI.loginLink.doClick();
		team9GUI.loginButton.doClick();
	}

	private void nameAndPasswordNotMatch() {
		team9GUI.userLogIn.setText("name");
		team9GUI.passwordLogIn.setText("Idaho2020");
		team9GUI.loginButton.doClick();
	}

	private void validLogin() {
		team9GUI.userLogIn.setText("name");
		team9GUI.passwordLogIn.setText("Bronco2020");
		team9GUI.loginButton.doClick();
	}

	@Test
	public void test() {
		new Team9GUI();
		try {
			emptySignUpFields();
			Thread.sleep(1000);

			invalidEmail();
			Thread.sleep(1000);

			shortPassword();
			Thread.sleep(1000);

			noUppercasePassword();
			Thread.sleep(1000);

			noLowercasePassword();
			Thread.sleep(1000);

			noDigitPassword();
			Thread.sleep(1000);

			validSignUp();
			Thread.sleep(1000);

			usedUserName();
			Thread.sleep(1000);

			usedEmail();
			Thread.sleep(1000);

			emptyLoginFields();
			Thread.sleep(1000);

			nameAndPasswordNotMatch();
			Thread.sleep(1000);

			validLogin();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
