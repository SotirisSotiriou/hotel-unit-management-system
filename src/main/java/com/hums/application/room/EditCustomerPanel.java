package com.hums.application.room;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.hums.roomManagementSystem.Customer;
import com.hums.roomManagementSystem.RMS_Registry;
import com.hums.tools.data.FileHandling;

public class EditCustomerPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNewLastName;
	private JTextField textFieldNewFirstName;
	private JTextField textFieldNewPhone;
	private JLabel labelLastName;
	private JLabel labelFirstName;
	private JLabel labelPhone;
	private JPanel content;
	private JPanel footer;
	private JButton buttonUpdateCustomer;
	private JTextField textFieldOldLastName;
	private JTextField textFieldOldFirstName;
	private JTextField textFieldOldPhone;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	
	private Customer customerToEdit;

	/**
	 * Create the panel.
	 */
	public EditCustomerPanel() {
		
		content = new JPanel();
		content.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		footer = new JPanel();
		footer.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(footer, GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
				.addComponent(content, GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(content, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(footer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		labelLastName = new JLabel("NEW Last Name");
		labelLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldNewLastName = new JTextField();
		textFieldNewLastName.setColumns(10);
		
		labelFirstName = new JLabel("NEW First Name");
		labelFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldNewFirstName = new JTextField();
		textFieldNewFirstName.setColumns(10);
		
		labelPhone = new JLabel("NEW Phone");
		labelPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldNewPhone = new JTextField();
		textFieldNewPhone.setColumns(10);
		
		textFieldOldLastName = new JTextField();
		textFieldOldLastName.setEditable(false);
		textFieldOldLastName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("OLD Last Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_1 = new JLabel("OLD First Name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_2 = new JLabel("OLD Phone");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldOldFirstName = new JTextField();
		textFieldOldFirstName.setEditable(false);
		textFieldOldFirstName.setColumns(10);
		
		textFieldOldPhone = new JTextField();
		textFieldOldPhone.setEditable(false);
		textFieldOldPhone.setColumns(10);
		
		lblNewLabel_3 = new JLabel("NEW Customer Info");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblNewLabel_4 = new JLabel("OLD Customer Info");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_content = new GroupLayout(content);
		gl_content.setHorizontalGroup(
			gl_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_content.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel_4)
						.addGroup(gl_content.createSequentialGroup()
							.addGroup(gl_content.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_content.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textFieldOldPhone, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldOldFirstName, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldOldLastName, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblNewLabel_3)
						.addGroup(gl_content.createSequentialGroup()
							.addGroup(gl_content.createParallelGroup(Alignment.LEADING, false)
								.addComponent(labelFirstName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(labelLastName, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
								.addComponent(labelPhone, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_content.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textFieldNewPhone, 198, 198, 198)
								.addComponent(textFieldNewFirstName, 198, 198, 198)
								.addComponent(textFieldNewLastName, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))))
					.addGap(231))
		);
		gl_content.setVerticalGroup(
			gl_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_4)
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldOldLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textFieldOldFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textFieldOldPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel_3)
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelLastName)
						.addComponent(textFieldNewLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelFirstName)
						.addComponent(textFieldNewFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelPhone)
						.addComponent(textFieldNewPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		content.setLayout(gl_content);
		footer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		buttonUpdateCustomer = new JButton("Update Customer");
		buttonUpdateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String lastname = textFieldNewLastName.getText();
				String firstname = textFieldNewFirstName.getText();
				String phone = textFieldNewPhone.getText();
				
				if(lastname.length()==0 || firstname.length()==0 || !phone.matches("\\d") && !phone.matches("[0-9]{10}") ) {
					
					JOptionPane.showMessageDialog(null, "Enter all fields\nPhone must have 10 digits", "Error", JOptionPane.ERROR_MESSAGE);
					
				}else {
					
					
					
					RMS_Registry.getInstance().getCustomerList().changeCustomerInfo(customerToEdit, lastname, firstname, phone);
					JOptionPane.showMessageDialog(null, "Customer info updated successfully!\nRedirecting to Customers panel...");
					
					//Write to File
					FileHandling.exportToFile(RMS_Registry.getInstance());
					
					RMS_Frame.showCard("customers");
				}
				
				
				
			}
		});
		footer.add(buttonUpdateCustomer);
		setLayout(groupLayout);

	}
	
	public void setCustomerToEdit(Customer aCustomer) {
		
		customerToEdit = aCustomer;
		
		textFieldOldLastName.setText(customerToEdit.getLastName());
		textFieldOldFirstName.setText(customerToEdit.getFirstName());
		textFieldOldPhone.setText(customerToEdit.getPhone());
		
		textFieldNewLastName.setText(customerToEdit.getLastName());
		textFieldNewFirstName.setText(customerToEdit.getFirstName());
		textFieldNewPhone.setText(customerToEdit.getPhone());
		
		
	}
	
}
