package Team9GUI.src.sprint1.test;
import org.junit.Test;

import Team9GUI.src.sprint1.product.Team9GUI;
import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
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