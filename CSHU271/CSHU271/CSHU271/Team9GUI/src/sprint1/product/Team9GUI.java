package Team9GUI.src.sprint1.product;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * The Team9GUI class creates and manages the the visual components of the
 * user account system created for CS-HU 271.
 * @author Kalli Plumer, Keaton Gillihan
 */
public class Team9GUI implements ActionListener {
	public JFrame signUpFrame, loginFrame, accountFrame;
	public JButton loginButton, loginLink, signUpButton, signUpLink, editButton;
	public JTextField userSignUp;
	public JTextField emailSignUp;
	public JTextField userLogIn;
	public JPasswordField passwordSignUp;
	public JPasswordField passwordLogIn;
	JLabel message;
	User user= new User(null, null, null);
	UserList list = new UserList();
/**
 * The main method that starts the Team9GUI program
 * @param args
 */
	public static void main(String[] args) {
		Team9GUI GUI = new Team9GUI();
	}
/**
 * The constructor for the Team9GUI
 */
	public Team9GUI() {
		JPanel signUpPanel= signUpPanel();
		signUpFrame = setupFrame("SIGN UP", signUpPanel,true,signUpButton);		
	}
	/**
	 * this method creates a login panel that contains everything that
	 * an existing user would need to login(a username field, a password field,
	 * and a login button).
	 * @return
	 */
	private JPanel loginPanel() {
		//Create Components for loginPanel
		JPanel login = setupPanel("LOG IN");
		JLabel userL = new JLabel("Username");
		userLogIn = new JTextField(15);
		JLabel passL = new JLabel("Password");
		passwordLogIn = new JPasswordField(15);
		loginButton = createButton("Log In");
		JLabel signUpOption = new JLabel("Don't have an account? ", SwingConstants.CENTER);
		signUpLink = createLink("Sign Up Here");
		//add Components to loginPanel
		login.add(userL);
		login.add(userLogIn);
		login.add(passL);
		login.add(passwordLogIn);
		login.add(Box.createRigidArea(new Dimension(2, 0)));
		login.add(loginButton);
		login.add(Box.createRigidArea(new Dimension(2, 0)));
		login.add(signUpOption);
		login.add(signUpLink);
		return login;
	}
	/**
	 * this method creates a sign panel that contains everything that
	 * an new user would need to sign up(a username field, a password field,
	 * an email field, and a login button).
	 * @return
	 */
	private JPanel signUpPanel() {
		//Create components for signUpPanel 
		JPanel signUp = setupPanel("SIGN UP");
		JLabel userSL = new JLabel("Username");
		userSignUp = new JTextField(15);
		JLabel emailSL = new JLabel("Email");
		emailSignUp = new JTextField(15);
		JLabel passwordSL = new JLabel("Password");
		passwordSignUp = new JPasswordField(15);
		signUpButton = createButton("Sign Up");
		JLabel loginOption = new JLabel("Already have an account? ", SwingConstants.CENTER);
		loginLink =createLink("Log In Here");
		//add components to signUpPanel
		signUp.add(userSL);
		signUp.add(userSignUp);
		signUp.add(emailSL);
		signUp.add(emailSignUp);
		signUp.add(passwordSL);
		signUp.add(passwordSignUp);
		signUp.add(Box.createRigidArea(new Dimension(2, 0)));
		signUp.add(signUpButton);
		signUp.add(Box.createRigidArea(new Dimension(2, 0)));
		signUp.add(loginOption);
		signUp.add(loginLink);
		return signUp;
	}
	/**
	 * This method creates a panel that displays a logged in users information
	 * @return
	 */
	private JPanel accountPanel() {
		//create components for accountPanel
		JPanel account = setupPanel("ACCOUNT INFORMATION");
		JLabel username = new JLabel("Username: " + user.getUsername());
		JLabel email = new JLabel("Email: " + user.getEmail());
		editButton = createButton("Edit Account");
		//add components to accountPanel
		account.add(username);
		account.add(email);
		account.add(Box.createRigidArea(new Dimension(2, 0)));
		account.add(editButton);
		account.add(Box.createRigidArea(new Dimension(2, 0)));
		return account;
	}	
	/**
	 * This method is used to create a button that looks like a link
	 * @param text
	 * @return
	 */
	private JButton createLink(String text) {
		JButton link = new JButton(text);
		link.setForeground(Color.BLUE);
		link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		link.setBorderPainted(false);
		link.setFocusPainted(false);
		link.setContentAreaFilled(false);
		link.addActionListener(this);
		return link;
	}
	/**
	 * This method is used to create a button that looks like a button
	 * @param text
	 * @return
	 */
	private JButton createButton(String text) {
		JButton button = new JButton(text);
		button.setFont(button.getFont().deriveFont(18.0f));
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.addActionListener(this);
		return button;
	}
	/**
	 * This method is used to create a frame with the appropriate specifications for this program 
	 * @param text, panel, visible, button
	 * @return
	 */
	private JFrame setupFrame(String text, JPanel panel,boolean visible, JButton button) {
		JFrame frame = new JFrame(text);
		frame.setMinimumSize(new Dimension(500, 500));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(panel);
		frame.setVisible(visible);
		frame.pack();
		frame.getRootPane().setDefaultButton(button);
		return frame;
	}
	/**
	 * This method is used to setup the panel so that it is ready for content to be added
	 * @param text
	 * @return
	 */
	private JPanel setupPanel(String text) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		panel.setLayout(new GridLayout(0, 1));
		JLabel title = new JLabel(text, SwingConstants.CENTER);
		title.setFont(title.getFont().deriveFont(24.0f));
		panel.add(title);
		return panel;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// the login button on the login page is clicked
		if (e.getSource().equals(loginButton)) {
			User check = list.findUser(userLogIn.getText(), new String(passwordLogIn.getPassword()));
			//any required fields are left empty
			if (userLogIn.getText().equals("") || passwordLogIn.getPassword().length == 0) {
				JOptionPane.showMessageDialog(loginFrame, "Please fill in all fields.");
			}
			//the login information is invalid
			else if (check == null) {
				JOptionPane.showMessageDialog(loginFrame, "The username and password do not match.");
			}
			//the login information is valid
			else {
				user = check;
				JOptionPane.showMessageDialog(loginFrame, "You have logged in!");
				loginFrame.setVisible(false);
				JPanel accountPanel= accountPanel();
				accountFrame = setupFrame("ACCOUNT INFORMATION", accountPanel,true,editButton);
			}
		}
		// the sign up button on the sign up page is clicked
		if (e.getSource().equals(signUpButton)) {
			//any required fields are left empty
			if (userSignUp.getText().equals("") || emailSignUp.getText().equals("")
					|| passwordSignUp.getPassword().length == 0) {
				JOptionPane.showMessageDialog(signUpFrame, "Please fill in all fields.");
			}
			//invalid email address
			else if (!user.checkEmail(emailSignUp.getText())) {
				JOptionPane.showMessageDialog(signUpFrame, "The email address provided was not valid.");
			}
			//used email address
			else if (list.doesEmailExist(emailSignUp.getText())) {
				JOptionPane.showMessageDialog(signUpFrame, "This email address already exists in our system.");
			}
			//used username
			else if (list.doesUserNameExist(userSignUp.getText())) {
				JOptionPane.showMessageDialog(signUpFrame, "This username already exists in our system.");
			}
			//invalid password
			else if (!user.checkPasswd(new String(passwordSignUp.getPassword()))) {
				JOptionPane.showMessageDialog(signUpFrame,
						"Your password must have at least 8 characters containing at least an uppercase letter, a lowercase letter, and a number.");
			}
			//Sign up information is valid
			else {
				list.addUser(userSignUp.getText(), (new String(passwordSignUp.getPassword())), emailSignUp.getText());
				JOptionPane.showMessageDialog(signUpFrame, "Your account has been created!");
				signUpFrame.dispose();
				JPanel loginPanel=loginPanel();
				loginFrame = setupFrame("LOG IN", loginPanel,true,loginButton);
			}
		}
		// the Log In Here link on the sign up page is clicked
		if (e.getSource().equals(loginLink)) {
			signUpFrame.dispose();
			JPanel loginPanel=loginPanel();
			loginFrame = setupFrame("LOG IN", loginPanel,true,loginButton);
		}
		// the Sign Up Here link on the log in page is clicked 
		if (e.getSource().equals(signUpLink)) {
			loginFrame.dispose();
			JPanel signUpPanel=signUpPanel();
			signUpFrame = setupFrame("SIGN UP", signUpPanel,true,loginButton);
		}
	}
}