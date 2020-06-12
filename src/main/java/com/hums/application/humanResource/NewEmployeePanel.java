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

import com.hums.humanResourceManagementSystem.EmpType;
import com.hums.humanResourceManagementSystem.Employee;
import com.hums.humanResourceManagementSystem.HRMS_Registry;
import com.hums.tools.data.FileHandling;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class NewEmployeePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textFieldLastName;
	private JTextField textFieldFirstName;
	private JTextField textFieldPhone;
	private JTextField textFieldEmail;
	private JTextField textFieldAddress;
	private JTextField textFieldSSN;
	private DefaultComboBoxModel<String> typeModel;
	private JComboBox<String> comboBoxType;
	private JButton buttonAddEmployee;

	/**
	 * Create the panel.
	 */
	public NewEmployeePanel() {
		
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
		
		buttonAddEmployee = new JButton("Add Employee");
		panel_1.add(buttonAddEmployee);
		buttonAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = textFieldFirstName.getText();
				String lastName =  textFieldLastName.getText();
				String phone = textFieldPhone.getText();
				String email = textFieldEmail.getText();
				String address = textFieldAddress.getText();
				String ssn = textFieldSSN.getText();
				String type = typeModel.getSelectedItem().toString();
				EmpType emtype = null;
				if(type.equals("Receptionist"))
					emtype = EmpType.RECEPTIONIST;
				else if(type.equals("Human Resource Manager"))
					emtype = EmpType.HUMAN_RESOURCE_MANAGER;
				else if(type.equals("Restaurant Manager"))
					emtype = EmpType.RESTAURANT_MANAGER;
				else
					emtype = EmpType.EVENT_MANAGER;
				Employee emp = new Employee(firstName,lastName,email,phone,
											address,ssn,emtype);
				
				
				if(firstName.equals("") || lastName.equals("") || phone.equals("") || email.equals("") 
						|| address.equals("")|| ssn.equals("")) {
					
					JOptionPane.showMessageDialog(null, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
					
				}else {
					
					HRMS_Registry.getInstance().getEmpList().addEmployee(emp);
					EmployeesPanel.updateModel();
					JOptionPane.showMessageDialog(null, "Employee added successfully");
					FileHandling.exportToFile(HRMS_Registry.getInstance());
					
					textFieldFirstName.setText("");
					textFieldLastName.setText("");
					textFieldPhone.setText("");
					textFieldEmail.setText("");
					textFieldAddress.setText("");
					textFieldSSN.setText("");
					typeModel.setSelectedItem("Receptionist");
					
					
				}
				
				
				
				
			}
		});
		
		
		JLabel lblNewLabel = new JLabel("Last Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Phone");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("SSN");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldSSN = new JTextField();
		textFieldSSN.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Type");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		
		comboBoxType = new JComboBox<String>();
		
		typeModel = new DefaultComboBoxModel<String>(new String[] {"Receptionist", "Event Manager", "Restaurant Manager", "Human Resource Manager"});
		comboBoxType.setModel(typeModel);
		
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
						.addComponent(textFieldLastName, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
						.addComponent(textFieldFirstName)
						.addComponent(textFieldPhone)
						.addComponent(textFieldEmail)
						.addComponent(textFieldAddress)
						.addComponent(textFieldSSN))
					.addContainerGap(384, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textFieldLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textFieldFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textFieldPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(textFieldAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(textFieldSSN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6)
						.addComponent(comboBoxType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(121, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
}
