package com.hums.application.login;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
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

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPaneRegister;
	private JTextField registerFieldFirstName;
	private JTextField registerFieldLastName;
	private JTextField registerFieldEmail;
	private JPasswordField registerFieldPassword;
	private JLabel registerLabelFirstname;
	private JLabel registerLabelLastname;
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
	
	private JPanel contentPaneLogin;
	private JLabel loginLabelUsername;
	private JLabel loginLabelPassword;
	private JTextField loginUsernameField;
	private JPasswordField loginPasswordField;
	private JButton loginButtonLogIn;
	private JLabel loginLabelNotAUser;
	private JLabel loginLabelForgotPassword;
	private JPanel cards;
	private CardLayout cl;
	private JLabel registerLabelAlreadyAUser;
	private JLabel registerLabelUserType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public LoginFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 350);
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
		setContentPane(cards);
		
		//initializing components and listeners
		initComponentsRegister();
		initComponentsLogin();
		createEventsRegister();
		createEventsLogin();
		
		
		
		
	}
	
	private void initComponentsLogin() {
		
		loginLabelUsername = new JLabel("Username");
		loginLabelUsername.setBounds(81, 56, 61, 14);
		contentPaneLogin.add(loginLabelUsername);
		
		loginLabelPassword = new JLabel("Password");
		loginLabelPassword.setBounds(81, 106, 61, 14);
		contentPaneLogin.add(loginLabelPassword);
		
		loginUsernameField = new JTextField();
		loginUsernameField.setBounds(81, 76, 135, 20);
		contentPaneLogin.add(loginUsernameField);
		loginUsernameField.setColumns(10);
		
		loginPasswordField = new JPasswordField();
		loginPasswordField.setBounds(81, 126, 135, 20);
		contentPaneLogin.add(loginPasswordField);
		
		loginButtonLogIn = new JButton("Log in");
		loginButtonLogIn.setBounds(81, 157, 135, 23);
		contentPaneLogin.add(loginButtonLogIn);
		
		loginLabelNotAUser = new JLabel("Not a user? Sign Up!");
		loginLabelNotAUser.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabelNotAUser.setFont(new Font("Tahoma", Font.PLAIN, 10));
		loginLabelNotAUser.setBounds(81, 191, 135, 14);
		contentPaneLogin.add(loginLabelNotAUser);
		
		loginLabelForgotPassword = new JLabel("Forgot Password?");
		loginLabelForgotPassword.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabelForgotPassword.setFont(new Font("Tahoma", Font.PLAIN, 10));
		loginLabelForgotPassword.setBounds(81, 216, 135, 14);
		contentPaneLogin.add(loginLabelForgotPassword);
		
	}
	
	private void initComponentsRegister() {
		
		registerLabelFirstname = new JLabel("FirstName:");
		registerLabelFirstname.setHorizontalAlignment(SwingConstants.RIGHT);
		registerLabelFirstname.setBounds(11, 62, 84, 20);
		contentPaneRegister.add(registerLabelFirstname);
		
		registerFieldFirstName = new JTextField();
		registerFieldFirstName.setBounds(101, 62, 135, 20);
		contentPaneRegister.add(registerFieldFirstName);
		registerFieldFirstName.setColumns(10);
		
		registerLabelLastname = new JLabel("LastName:");
		registerLabelLastname.setHorizontalAlignment(SwingConstants.RIGHT);
		registerLabelLastname.setBounds(11, 102, 84, 20);
		contentPaneRegister.add(registerLabelLastname);
		
		registerFieldLastName = new JTextField();
		registerFieldLastName.setBounds(101, 102, 135, 20);
		contentPaneRegister.add(registerFieldLastName);
		registerFieldLastName.setColumns(10);
		
		registerLabelEmail = new JLabel("Email:");
		registerLabelEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		registerLabelEmail.setBounds(10, 142, 85, 20);
		contentPaneRegister.add(registerLabelEmail);
		
		registerFieldEmail = new JTextField();
		registerFieldEmail.setBounds(101, 142, 135, 20);
		contentPaneRegister.add(registerFieldEmail);
		registerFieldEmail.setColumns(10);
		
		registerLabelPassword = new JLabel("Password:");
		registerLabelPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		registerLabelPassword.setBounds(10, 182, 85, 20);
		contentPaneRegister.add(registerLabelPassword);
		
		registerFieldPassword = new JPasswordField();
		registerFieldPassword.setBounds(101, 182, 135, 20);
		contentPaneRegister.add(registerFieldPassword);
		
		registerLabelConfirm = new JLabel("Confirm:");
		registerLabelConfirm.setHorizontalAlignment(SwingConstants.RIGHT);
		registerLabelConfirm.setBounds(11, 222, 84, 20);
		contentPaneRegister.add(registerLabelConfirm);
		
		comboBoxUserType = new JComboBox<String>();
		comboBoxUserType.setModel(new DefaultComboBoxModel<String>(new String[] {"Receptionist", "HR Manager", "Waiter", "Restaurant Manager", "Event Manager"}));
		comboBoxUserType.setBounds(101, 22, 135, 20);
		contentPaneRegister.add(comboBoxUserType);
		
		registerButtonRegister = new JButton("Register");
		registerButtonRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String registerFirstName = registerFieldFirstName.getText();
				String registerLastName = registerFieldLastName.getText();
				String registerEmail = registerFieldEmail.getText();
				String registerPassword = String.valueOf(registerFieldPassword.getPassword());
				
				if(registerBarEmail.getValue()==10 && 
						registerBarStrength.getValue()==10 && 
						registerBarConfirm.getValue()==100) {
					
					//all fields are in correct form, add register handling here
					
				}else {
					
					//some fields are in incorrect form, show dialog message to warn
					
				}
				
			}
		});
		registerButtonRegister.setBounds(147, 252, 89, 20);
		contentPaneRegister.add(registerButtonRegister);
		
		registerLabelUserType = new JLabel("User Type:");
		registerLabelUserType.setHorizontalAlignment(SwingConstants.RIGHT);
		registerLabelUserType.setBounds(11, 22, 84, 20);
		contentPaneRegister.add(registerLabelUserType);
		
		registerFieldConfirm = new JPasswordField();
		registerFieldConfirm.setBounds(101, 222, 135, 20);
		contentPaneRegister.add(registerFieldConfirm);
		
		registerBarConfirm = new JProgressBar();
		registerBarConfirm.setForeground(java.awt.Color.GREEN);
		registerBarConfirm.setBackground(java.awt.Color.RED);
		registerBarConfirm.setMaximum(100);
		registerBarConfirm.setVisible(false);
		registerBarConfirm.setBounds(101, 242, 135, 5);
		contentPaneRegister.add(registerBarConfirm);
		
		registerBarStrength = new JProgressBar();
		registerBarStrength.setBackground(java.awt.Color.WHITE);
		registerBarStrength.setMaximum(10);
		registerBarStrength.setBounds(101, 202, 135, 5);
		contentPaneRegister.add(registerBarStrength);
		
		registerBarEmail = new JProgressBar();
		registerBarEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		registerBarEmail.setBackground(Color.RED);
		registerBarEmail.setForeground(Color.GREEN);
		registerBarEmail.setMaximum(10);
		registerBarEmail.setBounds(101, 162, 135, 5);
		contentPaneRegister.add(registerBarEmail);
		
		registerButtonPasswordInfo = new JButton("?");
		registerButtonPasswordInfo.setFocusable(false);
		registerButtonPasswordInfo.setFocusTraversalKeysEnabled(false);
		registerButtonPasswordInfo.setBounds(235, 182, 20, 20);
		contentPaneRegister.add(registerButtonPasswordInfo);
		
		registerLabelAlreadyAUser = new JLabel("Already a User? Sign in!");
		registerLabelAlreadyAUser.setHorizontalAlignment(SwingConstants.RIGHT);
		registerLabelAlreadyAUser.setFont(new Font("Tahoma", Font.PLAIN, 10));
		registerLabelAlreadyAUser.setBounds(101, 278, 135, 14);
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
					//add method that searches emails from users and check if it exists
					JOptionPane.showMessageDialog(null,"Password has been sent to your email");
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
			}
		});
		
	}
}