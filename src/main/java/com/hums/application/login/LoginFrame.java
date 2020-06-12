package com.hums.application.login;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.hums.application.event.EMS_Frame;
import com.hums.application.humanResource.HR_Frame;
import com.hums.application.restaurant.REMS_Frame;
import com.hums.application.room.RMS_Frame;
import com.hums.tools.data.FileHandling;
import com.hums.tools.login.UserList;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPaneRegister;
	private JTextField registerFieldFirstName;
	private JTextField registerFieldLastName;
	private JTextField registerFieldUsername;
	private JTextField registerFieldEmail;
	private JPasswordField registerFieldPassword;
	private JLabel registerLabelFirstname;
	private JLabel registerLabelLastname;
	private JLabel registerLabelUsername;
	private JLabel registerLabelEmail;
	private JLabel registerLabelPassword;
	private JLabel registerLabelConfirm;
	private JComboBox<String> comboBoxUserType;
	private JButton registerButtonRegister;
	private JPasswordField registerFieldConfirm;
	private JButton registerButtonPasswordInfo;
	private JProgressBar registerBarConfirm;
	private JProgressBar registerBarStrength;
	private JProgressBar registerBarEmail;
	private JLabel registerFirstnameErrorMessage;
	private JLabel registerLastnameErrorMessage;
	private JLabel registerUsernameErrorMessage;
	private JLabel registerEmailErrorMessage;
	private JLabel registerPasswordErrorMessage;
	private JLabel registerConfirmErrorMessage;
		
	private JPanel contentPaneLogin;
	private JLabel loginLabelUsername;
	private JLabel loginLabelPassword;
	private JTextField loginUsernameField;
	private JPasswordField loginPasswordField;
	private JButton loginButtonLogIn;
	private JLabel loginLabelNotAUser;
	private JLabel loginLabelForgotPassword;
	private JLabel loginUsernameErrorMessage;
	private JLabel loginPasswordErrorMessage;
	private JPanel cards;
	private CardLayout cl;
	private JLabel registerLabelAlreadyAUser;
	private JLabel registerLabelUserType;
	
	private static final Font defaultErrorMessageFont = new Font("Tahoma", Font.PLAIN, 10);

	private UserList uList;
	
	public LoginFrame() {
		this.uList = (UserList) FileHandling.importFromFile("users.ser");
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(200, 200);
		setSize(300, 350);
		
		contentPaneRegister = new JPanel();
		contentPaneRegister.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneLogin = new JPanel();
		contentPaneLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneLogin.setLayout(null);
		
		
		contentPaneRegister.setLayout(null);
		this.setVisible(true);
		
		//Creating cards, each card is a panel, only one card is active at a time
		cards = new JPanel(new CardLayout());
		cl = (CardLayout)(cards.getLayout());
		
		cards.add(contentPaneRegister,"register");
		cards.add(contentPaneLogin,"login");
		
		//login card is shown when the frame starts
		cl.show(cards, "login");
		this.setTitle("Login");
		setContentPane(cards);
		
		//initializing components and listeners
		initComponentsRegister();
		initComponentsLogin();
		createEventsRegister();
		createEventsLogin();
		
		this.repaint();
		
	}
	
	private void initComponentsLogin() {
				
		loginLabelUsername = new JLabel("Username");
		loginLabelUsername.setBounds(80, 40, 61, 20);
		contentPaneLogin.add(loginLabelUsername);
		
		loginLabelPassword = new JLabel("Password");
		loginLabelPassword.setBounds(80, 110, 61, 20);
		contentPaneLogin.add(loginLabelPassword);
		
		loginUsernameField = new JTextField();
		loginUsernameField.setBounds(80, 60, 135, 20);
		contentPaneLogin.add(loginUsernameField);
		loginUsernameField.setColumns(10);
		
		loginPasswordField = new JPasswordField();
		loginPasswordField.setBounds(80, 130, 135, 20);
		contentPaneLogin.add(loginPasswordField);
		
		loginUsernameErrorMessage = new JLabel();
		loginUsernameErrorMessage.setHorizontalAlignment(SwingConstants.LEFT);
		loginUsernameErrorMessage.setBounds(80, 80, 160, 20);
		loginUsernameErrorMessage.setForeground(Color.red);
		loginUsernameErrorMessage.setFont(defaultErrorMessageFont);
		contentPaneLogin.add(loginUsernameErrorMessage);
		loginUsernameErrorMessage.setVisible(false);
		
		loginPasswordErrorMessage = new JLabel();
		loginPasswordErrorMessage.setHorizontalAlignment(SwingConstants.LEFT);
		loginPasswordErrorMessage.setBounds(80, 150, 160, 20);
		loginPasswordErrorMessage.setForeground(Color.red);
		loginPasswordErrorMessage.setFont(defaultErrorMessageFont);
		contentPaneLogin.add(loginPasswordErrorMessage);
		loginPasswordErrorMessage.setVisible(false);
		
		loginButtonLogIn = new JButton("Log in");
		loginButtonLogIn.setBounds(80, 180, 135, 23);
		contentPaneLogin.add(loginButtonLogIn);
		loginButtonLogIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = loginUsernameField.getText();
				String password = String.valueOf(loginPasswordField.getPassword());
				
				loginUsernameErrorMessage.setVisible(false);
				loginPasswordErrorMessage.setVisible(false);
				
				int loginResult = uList.login(username, password);
				if(username.isEmpty()) {
					loginUsernameErrorMessage.setText("Please type your username");
					loginUsernameErrorMessage.setVisible(true);
				}
				else {					
					switch(loginResult) {
					case -1:
						loginUsernameErrorMessage.setText("User not exists");
						loginUsernameErrorMessage.setVisible(true);
						break;
					case 0:
						loginPasswordErrorMessage.setText("Wrong password");
						loginPasswordErrorMessage.setVisible(true);
						break;
					case 1:
						JOptionPane.showMessageDialog(null, "Receptionist logged in successfully!");
						new RMS_Frame();
						dispose();
						break;
					case 2:
						JOptionPane.showMessageDialog(null, "Human Resource Manager logged in successfully!");
						new HR_Frame();
						dispose();
						break;
					case 3:
						JOptionPane.showMessageDialog(null, "Event Manager logged in successfully!");
						new EMS_Frame();
						dispose();
						break;
					case 4:
						JOptionPane.showMessageDialog(null, "Restaurant Manager logged in successfully!");
						new REMS_Frame();
						dispose();
						break;
					}
				}
			}
			
		});
		
		loginLabelNotAUser = new JLabel("Not a user? Sign Up!");
		loginLabelNotAUser.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabelNotAUser.setFont(new Font("Tahoma", Font.PLAIN, 10));
		loginLabelNotAUser.setBounds(81, 210, 135, 20);
		contentPaneLogin.add(loginLabelNotAUser);
		
		loginLabelForgotPassword = new JLabel("Forgot Password?");
		loginLabelForgotPassword.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabelForgotPassword.setFont(new Font("Tahoma", Font.PLAIN, 10));
		loginLabelForgotPassword.setBounds(81, 240, 135, 20);
		contentPaneLogin.add(loginLabelForgotPassword);
		
	}
	
	private void initComponentsRegister() {
		
		registerLabelFirstname = new JLabel("FirstName:");
		registerLabelFirstname.setHorizontalAlignment(SwingConstants.RIGHT);
		registerLabelFirstname.setBounds(11, 60, 84, 20);
		contentPaneRegister.add(registerLabelFirstname);
		
		registerFieldFirstName = new JTextField();
		registerFieldFirstName.setBounds(101, 60, 135, 20);
		contentPaneRegister.add(registerFieldFirstName);
		registerFieldFirstName.setColumns(10);
		
		registerFirstnameErrorMessage = new JLabel();
		registerFirstnameErrorMessage.setHorizontalAlignment(SwingConstants.LEFT);
		registerFirstnameErrorMessage.setBounds(101, 85, 180, 20);
		registerFirstnameErrorMessage.setForeground(Color.red);
		registerFirstnameErrorMessage.setFont(defaultErrorMessageFont);
		contentPaneRegister.add(registerFirstnameErrorMessage);
		registerFirstnameErrorMessage.setVisible(false);
		
		registerLabelLastname = new JLabel("LastName:");
		registerLabelLastname.setHorizontalAlignment(SwingConstants.RIGHT);
		registerLabelLastname.setBounds(11, 110, 84, 20);
		contentPaneRegister.add(registerLabelLastname);
		
		registerFieldLastName = new JTextField();
		registerFieldLastName.setBounds(101, 110, 135, 20);
		contentPaneRegister.add(registerFieldLastName);
		registerFieldLastName.setColumns(10);
		
		registerLastnameErrorMessage = new JLabel();
		registerLastnameErrorMessage.setHorizontalAlignment(SwingConstants.LEFT);
		registerLastnameErrorMessage.setBounds(101, 135, 180, 20);
		registerLastnameErrorMessage.setForeground(Color.red);
		registerLastnameErrorMessage.setFont(defaultErrorMessageFont);
		contentPaneRegister.add(registerLastnameErrorMessage);
		registerLastnameErrorMessage.setVisible(false);
		
		registerLabelUsername = new JLabel("Username: ");
		registerLabelUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		registerLabelUsername.setBounds(11, 160, 84, 20);
		contentPaneRegister.add(registerLabelUsername);
		
		registerFieldUsername = new JTextField();
		registerFieldUsername.setBounds(101, 160, 135, 20);
		contentPaneRegister.add(registerFieldUsername);
		registerFieldUsername.setColumns(10);
		
		registerUsernameErrorMessage = new JLabel();
		registerUsernameErrorMessage.setHorizontalAlignment(SwingConstants.LEFT);
		registerUsernameErrorMessage.setBounds(101, 185, 180, 20);
		registerUsernameErrorMessage.setForeground(Color.red);
		registerUsernameErrorMessage.setFont(defaultErrorMessageFont);
		contentPaneRegister.add(registerUsernameErrorMessage);
		registerUsernameErrorMessage.setVisible(false);
		
		registerLabelEmail = new JLabel("Email:");
		registerLabelEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		registerLabelEmail.setBounds(10, 210, 85, 20);
		contentPaneRegister.add(registerLabelEmail);
		
		registerFieldEmail = new JTextField();
		registerFieldEmail.setBounds(101, 210, 135, 20);
		contentPaneRegister.add(registerFieldEmail);
		registerFieldEmail.setColumns(10);
		
		registerEmailErrorMessage = new JLabel("");
		registerEmailErrorMessage.setHorizontalAlignment(SwingConstants.LEFT);
		registerEmailErrorMessage.setBounds(101, 235, 180, 20);
		registerEmailErrorMessage.setForeground(Color.red);
		registerEmailErrorMessage.setFont(defaultErrorMessageFont);
		contentPaneRegister.add(registerEmailErrorMessage);
		registerEmailErrorMessage.setVisible(false);
		
		registerLabelPassword = new JLabel("Password:");
		registerLabelPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		registerLabelPassword.setBounds(11, 260, 84, 20);
		contentPaneRegister.add(registerLabelPassword);
		
		registerFieldPassword = new JPasswordField();
		registerFieldPassword.setBounds(101, 260, 135, 20);
		contentPaneRegister.add(registerFieldPassword);
		
		registerPasswordErrorMessage = new JLabel("");
		registerPasswordErrorMessage.setHorizontalAlignment(SwingConstants.LEFT);
		registerPasswordErrorMessage.setBounds(101, 285, 180, 20);
		registerPasswordErrorMessage.setForeground(Color.red);
		registerPasswordErrorMessage.setFont(defaultErrorMessageFont);
		contentPaneRegister.add(registerPasswordErrorMessage);
		registerPasswordErrorMessage.setVisible(false);
		
		registerLabelConfirm = new JLabel("Confirm:");
		registerLabelConfirm.setHorizontalAlignment(SwingConstants.RIGHT);
		registerLabelConfirm.setBounds(11, 310, 84, 20);
		contentPaneRegister.add(registerLabelConfirm);
		
		registerConfirmErrorMessage = new JLabel("");
		registerConfirmErrorMessage.setHorizontalAlignment(SwingConstants.LEFT);
		registerConfirmErrorMessage.setBounds(101, 335, 180, 20);
		registerConfirmErrorMessage.setForeground(Color.red);
		registerConfirmErrorMessage.setFont(defaultErrorMessageFont);
		contentPaneRegister.add(registerConfirmErrorMessage);
		
		comboBoxUserType = new JComboBox<String>();
		comboBoxUserType.setModel(new DefaultComboBoxModel<String>(new String[] {"Receptionist", "HR Manager", "Restaurant Manager", "Event Manager"}));
		comboBoxUserType.setBounds(101, 22, 135, 20);
		contentPaneRegister.add(comboBoxUserType);
		
		registerButtonRegister = new JButton("Register");
		registerButtonRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String registerFirstName = registerFieldFirstName.getText();
				String registerLastName = registerFieldLastName.getText();
				String registerUsername = registerFieldUsername.getText();
				String registerEmail = registerFieldEmail.getText();
				String registerPassword = String.valueOf(registerFieldPassword.getPassword());
				String registerConfirm = String.valueOf(registerFieldConfirm.getPassword());
				String registerComboBoxChoice = String.valueOf(comboBoxUserType.getSelectedItem());
				
				boolean emailExists = uList.emailExists(registerEmail);
				boolean usernameExists = uList.usernameExists(registerUsername);
				boolean passwordConfirm = registerPassword.equals(registerConfirm);
				
				boolean emailFieldEmpty = registerEmail.equals("");
				boolean passwordFieldEmpty = registerPassword.equals("");
				boolean firstnameFieldEmpty = registerFirstName.equals("");
				boolean lastnameFieldEmpty = registerLastName.equals("");
				boolean usernameFieldEmpty = registerUsername.equals("");
				
				if(registerBarEmail.getValue()==10 && 
						registerBarStrength.getValue()==10 && 
						registerBarConfirm.getValue()==100 && 
						!emailExists && !usernameExists && passwordConfirm &&
						!firstnameFieldEmpty && !lastnameFieldEmpty && !emailFieldEmpty && !passwordFieldEmpty) {
					
					//all fields are in correct form, add register handling here
					registerFirstnameErrorMessage.setVisible(false);
					registerLastnameErrorMessage.setVisible(false);
					registerUsernameErrorMessage.setVisible(false);
					registerEmailErrorMessage.setVisible(false);
					registerPasswordErrorMessage.setVisible(false);
					registerConfirmErrorMessage.setVisible(false);
					JOptionPane.showMessageDialog(null, "Registered successfully!");
					uList.register(registerFirstName, registerLastName, registerUsername, registerPassword, registerEmail, registerComboBoxChoice);
					FileHandling.exportToFile(uList);
					cl.show(cards, "login");
					setSize(300, 350);
					setTitle("Login");
				}else {
					
					//some fields are in incorrect form, show dialog message to warn
					
					//For the first name
					if(firstnameFieldEmpty) {
						registerFirstnameErrorMessage.setText("Please type your firstname");
						registerFirstnameErrorMessage.setVisible(true);
					}
					else registerFirstnameErrorMessage.setVisible(false);
					
					//For the last name
					if(lastnameFieldEmpty) {
						registerLastnameErrorMessage.setText("Please type your lastname");
						registerLastnameErrorMessage.setVisible(true);
					}
					else registerLastnameErrorMessage.setVisible(false);
					
					//For the user name
					if(usernameFieldEmpty) {
						registerUsernameErrorMessage.setText("Please type a username");
						registerUsernameErrorMessage.setVisible(true);
					}
					else if(usernameExists) {
						registerUsernameErrorMessage.setText("Username already exists");
						registerUsernameErrorMessage.setVisible(true);
					}
					else registerUsernameErrorMessage.setVisible(false);
					
					//For the email
					if(emailFieldEmpty) {
						registerEmailErrorMessage.setText("Please type an email address");
						registerEmailErrorMessage.setVisible(true);
					}
					else if(registerBarEmail.getValue() < 10) {
						registerEmailErrorMessage.setText("Please type a valid email address");
						registerEmailErrorMessage.setVisible(true);
					}
					else if(emailExists) {
						registerEmailErrorMessage.setText("Email already exists");
						registerEmailErrorMessage.setVisible(true);
					}
					else registerEmailErrorMessage.setVisible(false);
					
					//For the password
					if(passwordFieldEmpty) {
						registerPasswordErrorMessage.setText("Please type a password");
						registerPasswordErrorMessage.setVisible(true);
					}
					else if(registerBarStrength.getValue() < 10) {
						registerPasswordErrorMessage.setText("Please type a valid password");
						registerPasswordErrorMessage.setVisible(true);
					}
					else registerPasswordErrorMessage.setVisible(false);
					
					//For the password confirm
					if(registerBarConfirm.getValue() < 100) {
						registerConfirmErrorMessage.setText("Please retype the password here");
						registerConfirmErrorMessage.setVisible(true);
					}
					else registerConfirmErrorMessage.setVisible(false);
				}
			}
		});
		registerButtonRegister.setBounds(147, 366, 89, 20);
		contentPaneRegister.add(registerButtonRegister);
		
		registerLabelUserType = new JLabel("User Type:");
		registerLabelUserType.setHorizontalAlignment(SwingConstants.RIGHT);
		registerLabelUserType.setBounds(11, 22, 84, 20);
		contentPaneRegister.add(registerLabelUserType);
		
		registerFieldConfirm = new JPasswordField();
		registerFieldConfirm.setBounds(101, 310, 135, 20);
		contentPaneRegister.add(registerFieldConfirm);
		
		registerBarConfirm = new JProgressBar();
		registerBarConfirm.setForeground(java.awt.Color.GREEN);
		registerBarConfirm.setBackground(java.awt.Color.RED);
		registerBarConfirm.setMaximum(100);
		registerBarConfirm.setVisible(false);
		registerBarConfirm.setBounds(101, 330, 135, 5);
		contentPaneRegister.add(registerBarConfirm);
		
		registerBarStrength = new JProgressBar();
		registerBarStrength.setBackground(java.awt.Color.WHITE);
		registerBarStrength.setMaximum(10);
		registerBarStrength.setBounds(101, 280, 135, 5);
		contentPaneRegister.add(registerBarStrength);
		
		registerBarEmail = new JProgressBar();
		registerBarEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		registerBarEmail.setBackground(Color.RED);
		registerBarEmail.setForeground(Color.GREEN);
		registerBarEmail.setMaximum(10);
		registerBarEmail.setBounds(101, 230, 135, 5);
		contentPaneRegister.add(registerBarEmail);
		
		registerButtonPasswordInfo = new JButton("?");
		registerButtonPasswordInfo.setFocusable(false);
		registerButtonPasswordInfo.setFocusTraversalKeysEnabled(false);
		registerButtonPasswordInfo.setBounds(237, 260, 20, 20);
		contentPaneRegister.add(registerButtonPasswordInfo);
		
		registerLabelAlreadyAUser = new JLabel("Already a User? Sign in!");
		registerLabelAlreadyAUser.setHorizontalAlignment(SwingConstants.RIGHT);
		registerLabelAlreadyAUser.setFont(new Font("Tahoma", Font.PLAIN, 10));
		registerLabelAlreadyAUser.setBounds(101, 397, 135, 14);
		contentPaneRegister.add(registerLabelAlreadyAUser);
		
		registerBarStrength.setVisible(false);
		registerBarEmail.setVisible(false);
		
	}
	
	private void createEventsLogin() {
		loginLabelNotAUser.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				//when you click the text to go from login to register, clear the fields of register form
				//and show the register card (panel)
				
				registerFieldFirstName.setText("");
				registerFieldLastName.setText("");
				registerFieldUsername.setText("");
				registerFieldEmail.setText("");
				registerFieldPassword.setText("");
				registerFieldConfirm.setText("");
				registerBarEmail.setValue(0);
				registerBarStrength.setValue(0);
				registerBarConfirm.setValue(0);
				registerBarEmail.setVisible(false);
				registerBarStrength.setVisible(false);
				registerBarConfirm.setVisible(false);
				
				cl.show(cards, "register");
				setTitle("Register");
				setSize(300, 460);
			}
			
			//change color to link when you hover
			public void mouseEntered(MouseEvent e) {
				loginLabelNotAUser.setText("<html><a href=\\\"url\\\">Not a user? Sign Up!</a></html>");
			}
			
			//change color to default when you exit
			public void mouseExited(MouseEvent e) {
				loginLabelNotAUser.setText("Not a user? Sign Up!");
			}
		});
		
		
		loginLabelForgotPassword.addMouseListener(new MouseAdapter() {
			//change color to link when you hover
			public void mouseEntered(MouseEvent arg0) {
				loginLabelForgotPassword.setText("<html><a href=\\\"\\\">Forgot Password?</a></html>");
			}
			//change color to default when you exit
			public void mouseExited(MouseEvent e) {
				loginLabelForgotPassword.setText("Forgot Password?");
			}
			//when you click the 'forgot password' button, an input dialog appears to submit email to get password
			public void mouseClicked(MouseEvent e) {
				String email;
				email=JOptionPane.showInputDialog("Write your email to get password");
				
				if (email!=null && !email.equals("")) {
					boolean emailExists = uList.emailExists(email);
					if(emailExists)
						JOptionPane.showMessageDialog(null,"Password has been sent to your email");
					else
						JOptionPane.showMessageDialog(null, "This email does not belong to a user.", "Email not found", JOptionPane.ERROR_MESSAGE);
				}
					
				else if(email!=null)
					JOptionPane.showMessageDialog(null,"Please enter your email","Error: Enter email", JOptionPane.ERROR_MESSAGE, null);
				
			}
		});
	}

	private void createEventsRegister() {
		
		registerFieldEmail.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				
				//email must match the following pattern
				//if it matches, set bar value to 10
				
				if(registerFieldEmail.getText().length()<5)
					registerBarEmail.setVisible(false);
				else
					registerBarEmail.setVisible(true);
				
				if(registerFieldEmail.getText().matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")) {
					registerBarEmail.setValue(10);
				}
				else registerBarEmail.setValue(0);
				
			}
		});
		
		registerFieldPassword.addKeyListener(new KeyAdapter() {
			
			//check if password is strong enough
			//password must be 8 characters, at least 1 letter and 1 digit
			//if password is over 8 characters, but doesn't have at least 1 letter or digit,
				//stop the bar counter to 8 and make the bar orange until you enter the remaining
			
			//if password matches the pattern, set bar value to 10 (8+2)
			//bar must be at 10 to be able to pass the check at register button listener
			
			public void keyReleased(KeyEvent e) {
				
				String password = String.valueOf(registerFieldPassword.getPassword());
				int passwordLength = password.length();
				
				registerBarStrength.setForeground(Color.RED);
				
				if(registerBarStrength.getValue()<8) registerBarStrength.setForeground(java.awt.Color.RED);
				
				if(password.length()>=8 &&!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$")) {
					registerBarStrength.setForeground(java.awt.Color.ORANGE);
					passwordLength=8;
				}
				
				if( password.matches("^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$") ) {
					registerBarStrength.setForeground(java.awt.Color.GREEN);
					passwordLength+=2;
				}
				
				registerBarStrength.setValue(passwordLength);
				
				//when you change the password, update the confirm password field to check if they still match
				
				if(Arrays.equals(registerFieldPassword.getPassword(), registerFieldConfirm.getPassword())) 
					registerBarConfirm.setValue(100);
				else 
					registerBarConfirm.setValue(1);
				
				if(registerBarStrength.getValue()==0)
					registerBarStrength.setVisible(false);
				else registerBarStrength.setVisible(true);
				
				
			}
		});
		
		registerFieldConfirm.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				
				//when you type the password in confirm field, it checks if the password from confirm
				//equals with the password on password field
				
				if(registerBarConfirm.getValue()>=1)
					registerBarConfirm.setVisible(true);
				
				if(registerFieldConfirm.getPassword().length==0)
					registerBarConfirm.setVisible(false);
				
				if(Arrays.equals(registerFieldPassword.getPassword(), registerFieldConfirm.getPassword()))
					registerBarConfirm.setValue(100);
				else 
					registerBarConfirm.setValue(1);

			}
		});
		
		registerButtonPasswordInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JOptionPane.showMessageDialog(null,"Password must have:\n8 characters,\nAt least 1 digit,\nAt least 1 letter");
				
			}
		});
		
		registerLabelAlreadyAUser.addMouseListener(new MouseAdapter() {
			
			//change the color to blue when you hove
			public void mouseEntered(MouseEvent arg0) {
				registerLabelAlreadyAUser.setText("<html><a href=\\\"\\\">Already a User? Sign in!</a></html>");
			}
			//change the color to default when you exit
			public void mouseExited(MouseEvent e) {
				registerLabelAlreadyAUser.setText("Already a User? Sign in!");
			}
			//clear login fields and change card (panel) to login
			public void mouseClicked(MouseEvent e) {
				loginUsernameField.setText("");
				loginPasswordField.setText("");
				cl.show(cards,"login");
				setTitle("Login");
				setSize(300, 350);
			}
		});
		
	}
}