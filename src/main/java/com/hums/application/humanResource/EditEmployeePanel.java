package com.hums.application.humanResource;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

import com.hums.humanResourceManagementSystem.Employee;
import com.hums.humanResourceManagementSystem.HRMS_Registry;
import com.hums.tools.data.FileHandling;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class EditEmployeePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldOldLastName;
	private JTextField textFieldOldFirstName;
	private JTextField textFieldOldPhone;
	private JTextField textFieldOldEmail;
	private JTextField textFieldOldAddress;
	private JTextField textFieldOldSSN;
	private DefaultComboBoxModel<String> typeModel;
	private JComboBox<String> comboBoxType;
	private JButton buttonUpdateEmployee;
	private JTextField textFieldNewLastName;
	private JTextField textFieldNewFirstName;
	private JTextField textFieldNewPhone;
	private JTextField textFieldNewEmail;
	private JTextField textFieldNewAddress;
	private JTextField textFieldNewSSN;
	
	private Employee empToEdit = null;

	/**
	 * Create the panel.
	 */
	public EditEmployeePanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
		);
		
		buttonUpdateEmployee = new JButton("Update Employee");
		buttonUpdateEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String firstName = textFieldNewFirstName.getText();
				String lastName = textFieldNewLastName.getText();
				String phone = textFieldNewPhone.getText();
				String address = textFieldNewAddress.getText();
				String email = textFieldNewEmail.getText();
				String ssn = textFieldNewSSN.getText();
				empToEdit.setFirstname(firstName);
				empToEdit.setLastname(lastName);
				empToEdit.setPhone(phone);
				empToEdit.setAddress(address);
				empToEdit.setEmail(email);
				empToEdit.setSsn(ssn);
				EmployeesPanel.updateModel();
				FileHandling.exportToFile(HRMS_Registry.getInstance());
				JOptionPane.showMessageDialog(null, "Employee Info Changed");
			}
		});
		panel_1.add(buttonUpdateEmployee);
		
		JLabel lblNewLabel = new JLabel("OLD Last Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldOldLastName = new JTextField();
		textFieldOldLastName.setEditable(false);
		textFieldOldLastName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("OLD First Name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldOldFirstName = new JTextField();
		textFieldOldFirstName.setEditable(false);
		textFieldOldFirstName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("OLD Phone");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldOldPhone = new JTextField();
		textFieldOldPhone.setEditable(false);
		textFieldOldPhone.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("OLD Email");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldOldEmail = new JTextField();
		textFieldOldEmail.setEditable(false);
		textFieldOldEmail.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("OLD Address");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldOldAddress = new JTextField();
		textFieldOldAddress.setEditable(false);
		textFieldOldAddress.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("OLD SSN");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldOldSSN = new JTextField();
		textFieldOldSSN.setEditable(false);
		textFieldOldSSN.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("OLD Type");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		
		comboBoxType = new JComboBox<String>();
		comboBoxType.setEnabled(false);
		
		typeModel = new DefaultComboBoxModel<String>(new String[] {"Receptionist", "Event Manager", "Restaurant Manager", "Human Resource Manager"});
		comboBoxType.setModel(typeModel);
		
		JLabel lblNewSsn = new JLabel("NEW SSN");
		lblNewSsn.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLastName = new JLabel("NEW Last Name");
		lblNewLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewFirstName = new JLabel("NEW First Name");
		lblNewFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewPhone = new JLabel("NEW Phone");
		lblNewPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewEmail = new JLabel("NEW Email");
		lblNewEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewAddress = new JLabel("NEW Address");
		lblNewAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldNewLastName = new JTextField();
		textFieldNewLastName.setColumns(10);
		
		textFieldNewFirstName = new JTextField();
		textFieldNewFirstName.setColumns(10);
		
		textFieldNewPhone = new JTextField();
		textFieldNewPhone.setColumns(10);
		
		textFieldNewEmail = new JTextField();
		textFieldNewEmail.setColumns(10);
		
		textFieldNewAddress = new JTextField();
		textFieldNewAddress.setColumns(10);
		
		textFieldNewSSN = new JTextField();
		textFieldNewSSN.setColumns(10);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(comboBoxType, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textFieldOldLastName, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
						.addComponent(textFieldOldFirstName)
						.addComponent(textFieldOldPhone)
						.addComponent(textFieldOldEmail)
						.addComponent(textFieldOldAddress)
						.addComponent(textFieldOldSSN))
					.addGap(90)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNewPhone, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewFirstName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLastName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldNewLastName, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldNewPhone, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldNewFirstName, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNewSsn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewEmail, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewAddress))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldNewEmail, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldNewAddress, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldNewSSN, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLastName)
								.addComponent(textFieldNewLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewFirstName)
								.addComponent(textFieldNewFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldNewPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewPhone))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewEmail)
								.addComponent(textFieldNewEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewAddress)
								.addComponent(textFieldNewAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewSsn)
								.addComponent(textFieldNewSSN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(textFieldOldLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(textFieldOldFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(textFieldOldPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3)
								.addComponent(textFieldOldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_4)
								.addComponent(textFieldOldAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_5)
								.addComponent(textFieldOldSSN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_6)
								.addComponent(comboBoxType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(117, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
	}
	
	public void setPanelEmployee(Employee emp) {
		textFieldOldLastName.setText(emp.getLastname());
		textFieldOldFirstName.setText(emp.getFirstname());
		textFieldOldPhone.setText(emp.getPhone());
		textFieldOldEmail.setText(emp.getEmail());
		textFieldOldAddress.setText(emp.getAddress());
		textFieldOldSSN.setText(emp.getSsn());
		
		textFieldNewLastName.setText(emp.getLastname());
		textFieldNewFirstName.setText(emp.getFirstname());
		textFieldNewPhone.setText(emp.getPhone());
		textFieldNewEmail.setText(emp.getEmail());
		textFieldNewAddress.setText(emp.getAddress());
		textFieldNewSSN.setText(emp.getSsn());
		empToEdit = emp;
	}
}
