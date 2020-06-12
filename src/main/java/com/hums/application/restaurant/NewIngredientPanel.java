package com.hums.application.restaurant;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import com.hums.restaurantManagementSystem.Ingredient;
import com.hums.restaurantManagementSystem.REMS_Registry;
import com.hums.tools.data.FileHandling;

public class NewIngredientPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textFieldName;
	private JPanel panel_1;
	private JButton btnNewButton;
	private JPanel content;
	private JSpinner spinnerInitialQuantity;
	private JSpinner spinnerMaxQuantity;
	private JSpinner spinnerMinQuantity;
	private JButton buttonInitialQuantityStep;
	private SpinnerNumberModel spinnerInitialQuantityModel;
	private JLabel lblNewLabel_4;
	private SpinnerNumberModel spinnerMinQuantityModel;
	private JButton buttonMinQuantityStep;
	private JButton buttonMaxQuantityStep;
	private SpinnerNumberModel spinnerMaxQuantityModel;
	
	public NewIngredientPanel() {
		
		content = new JPanel();
		content.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
				.addComponent(content, GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(content, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		JLabel lblNewLabel = new JLabel("Ingredient Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_2 = new JLabel("Min Storage Quantity");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_3 = new JLabel("Max Storage Quantity");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		
		spinnerMinQuantity = new JSpinner();
		spinnerMinQuantityModel = new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1));
		spinnerMinQuantity.setModel(spinnerMinQuantityModel);
		
		buttonMinQuantityStep = new JButton("+/- 1");
		buttonMinQuantityStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(buttonMinQuantityStep.getText().equals("+/- 1")) {
					
					buttonMinQuantityStep.setText("+/- 10");
					spinnerMinQuantityModel.setStepSize(10);
					
					
				}else if( buttonMinQuantityStep.getText().equals("+/- 10") ) {
					
					buttonMinQuantityStep.setText("+/- 100");
					spinnerMinQuantityModel.setStepSize(100);
					
				}else {
					
					buttonMinQuantityStep.setText("+/- 1");
					spinnerMinQuantityModel.setStepSize(1);
					
				}
				
			}
		});
		
		spinnerMaxQuantity = new JSpinner();
		spinnerMaxQuantityModel = new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1));
		spinnerMaxQuantity.setModel(spinnerMaxQuantityModel);
		
		buttonMaxQuantityStep = new JButton("+/- 1");
		buttonMaxQuantityStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(buttonMaxQuantityStep.getText().equals("+/- 1")) {
					
					buttonMaxQuantityStep.setText("+/- 10");
					spinnerMaxQuantityModel.setStepSize(10);
					
					
				}else if( buttonMaxQuantityStep.getText().equals("+/- 10") ) {
					
					buttonMaxQuantityStep.setText("+/- 100");
					spinnerMaxQuantityModel.setStepSize(100);
					
				}else {
					
					buttonMaxQuantityStep.setText("+/- 1");
					spinnerMaxQuantityModel.setStepSize(1);
					
				}
				
				
			}
		});
		
		JButton btnNewButton_4 = new JButton("+/- 10");
		
		JLabel lblNewLabel_1 = new JLabel("Initial Quantity");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spinnerInitialQuantity = new JSpinner();
		spinnerInitialQuantityModel = new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1));
		spinnerInitialQuantity.setModel(spinnerInitialQuantityModel);
		
		buttonInitialQuantityStep = new JButton("+/- 1");
		buttonInitialQuantityStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(buttonInitialQuantityStep.getText().equals("+/- 1")) {
					
					buttonInitialQuantityStep.setText("+/- 10");
					spinnerInitialQuantityModel.setStepSize(10);
					
					
				}else if( buttonInitialQuantityStep.getText().equals("+/- 10") ) {
					
					buttonInitialQuantityStep.setText("+/- 100");
					spinnerInitialQuantityModel.setStepSize(100);
					
				}else {
					
					buttonInitialQuantityStep.setText("+/- 1");
					spinnerInitialQuantityModel.setStepSize(1);
					
				}
				
				
				
				
			}
		});
		
		lblNewLabel_4 = new JLabel("(1/10/100)");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_content = new GroupLayout(content);
		gl_content.setHorizontalGroup(
			gl_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_content.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.TRAILING))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_content.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_content.createSequentialGroup()
							.addGroup(gl_content.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(spinnerMaxQuantity, Alignment.LEADING)
								.addComponent(spinnerMinQuantity, Alignment.LEADING)
								.addComponent(spinnerInitialQuantity, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_content.createParallelGroup(Alignment.LEADING, false)
								.addComponent(buttonMaxQuantityStep, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(buttonMinQuantityStep, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(buttonInitialQuantityStep, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
								.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(10)
							.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE))
						.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
					.addGap(300))
		);
		gl_content.setVerticalGroup(
			gl_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(spinnerInitialQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonInitialQuantityStep))
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(spinnerMinQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonMinQuantityStep))
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(spinnerMaxQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_4)
						.addComponent(buttonMaxQuantityStep))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_4)
					.addContainerGap(207, Short.MAX_VALUE))
		);
		content.setLayout(gl_content);
		
		btnNewButton = new JButton("Add Ingredient");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldName.getText().length()!=0) {
					Ingredient anIngredient = new Ingredient(textFieldName.getText(),
															Double.parseDouble(spinnerMinQuantity.getValue().toString()),
															Double.parseDouble(spinnerMaxQuantity.getValue().toString()));
					anIngredient.setQuantity( Double.parseDouble(spinnerInitialQuantity.getValue().toString()) );
					REMS_Registry.getInstance().getStorage().addIngredient(anIngredient);
					
					JOptionPane.showMessageDialog(null, "New Ingredient added successfully!");
					
					//Write to file
					FileHandling.exportToFile(REMS_Registry.getInstance());
					
					clearFields();
				}
				else {
					JOptionPane.showMessageDialog(null, "Ingredient name cannot be empty!!","Warning",JOptionPane.WARNING_MESSAGE);
				}
				
				
			}
		});
		panel_1.add(btnNewButton);
		setLayout(groupLayout);

	}
	
	public void clearFields() {
		
		textFieldName.setText("");
		spinnerInitialQuantity.setValue(0);
		spinnerMinQuantity.setValue(0);
		spinnerMaxQuantity.setValue(0);
		
		
	}
}
