package com.hums.application.room;

import java.awt.Color;
import java.awt.FlowLayout;
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



public class NewCustomerPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textFieldLastName;
	private JTextField textFieldFirstName;
	private JTextField textFieldPhone;
	private JLabel labelLastName;
	private JLabel labelFirstName;
	private JLabel labelPhone;
	private JPanel content;
	private JPanel footer;
	private JButton buttonAddCustomer;

	/**
	 * Create the panel.
	 */
	public NewCustomerPanel() {
		
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
		
		labelLastName = new JLabel("Last Name");
		labelLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		
		labelFirstName = new JLabel("First Name");
		labelFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setColumns(10);
		
		labelPhone = new JLabel("Phone");
		labelPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		GroupLayout gl_content = new GroupLayout(content);
		gl_content.setHorizontalGroup(
			gl_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_content.createParallelGroup(Alignment.LEADING, false)
						.addComponent(labelLastName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(labelFirstName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(labelPhone, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textFieldPhone)
						.addComponent(textFieldFirstName)
						.addComponent(textFieldLastName, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
					.addContainerGap(369, Short.MAX_VALUE))
		);
		gl_content.setVerticalGroup(
			gl_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelLastName)
						.addComponent(textFieldLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelFirstName)
						.addComponent(textFieldFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelPhone)
						.addComponent(textFieldPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(272, Short.MAX_VALUE))
		);
		content.setLayout(gl_content);
		footer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		buttonAddCustomer = new JButton("Add Customer");
		buttonAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String lastname = textFieldLastName.getText();
				String firstname = textFieldFirstName.getText();
				String phone = textFieldPhone.getText();
				
				if(lastname.length()==0 || firstname.length()==0 || !phone.matches("\\d") && !phone.matches("[0-9]{10}") ) {
					
					JOptionPane.showMessageDialog(null, "Enter all fields\nPhone must have 10 digits", "Error", JOptionPane.ERROR_MESSAGE);
					
				}else {
					Customer aCustomer = new Customer(lastname,firstname,phone);
					
					if(RMS_Registry.getInstance().getCustomerList().addCustomer(aCustomer)) {
						JOptionPane.showMessageDialog(null, "New customer created successfully");
						
						//Write to File
						FileHandling.exportToFile(RMS_Registry.getInstance());
						
						clearFields();
					}
				}
				
				
				
			}
		});
		footer.add(buttonAddCustomer);
		setLayout(groupLayout);

	}
	
	public void clearFields() {
		
		textFieldLastName.setText("");
		textFieldFirstName.setText("");
		textFieldPhone.setText("");
		
	}
}
