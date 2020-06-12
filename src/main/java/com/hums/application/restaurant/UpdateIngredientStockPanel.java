package com.hums.application.restaurant;

import java.awt.Color;
import java.awt.Font;
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

public class UpdateIngredientStockPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textFieldName;
	private JPanel panel_1;
	private JButton buttonUpdateIngredientStock;
	private JPanel content;
	private JSpinner spinnerInitialQuantity;
	private JSpinner spinnerMaxQuantity;
	private JSpinner spinnerMinQuantity;
	private JSpinner spinnerNewQuantity;
	private JSpinner spinnerNewMinQuantity;
	private JSpinner spinnerNewMaxQuantity;
	private JButton buttonNewQuantityStep;
	private JButton buttonNewMinQuantityStep;
	private JButton buttonNewMaxQuantityStep;
	
	private Ingredient ingredientToUpdate;
	private SpinnerNumberModel spinnerNewQuantityModel;
	private SpinnerNumberModel spinnerNewMinQuantityModel;
	private SpinnerNumberModel spinnerNewMaxQuantityModel;

	/**
	 * Create the panel.
	 */
	public UpdateIngredientStockPanel() {
		
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
		textFieldName.setEnabled(false);
		textFieldName.setColumns(10);
		
		spinnerMinQuantity = new JSpinner();
		spinnerMinQuantity.setEnabled(false);
		spinnerMinQuantity.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		
		spinnerMaxQuantity = new JSpinner();
		spinnerMaxQuantity.setEnabled(false);
		spinnerMaxQuantity.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		
		JLabel lblNewLabel_1 = new JLabel("Initial Quantity");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spinnerInitialQuantity = new JSpinner();
		spinnerInitialQuantity.setEnabled(false);
		spinnerInitialQuantity.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		
		JLabel lblNewQuantity = new JLabel("New Quantity");
		lblNewQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spinnerNewQuantity = new JSpinner();
		spinnerNewQuantityModel = new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1));
		spinnerNewQuantity.setModel(spinnerNewQuantityModel);
		
		buttonNewQuantityStep = new JButton("+/- 1");
		buttonNewQuantityStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(buttonNewQuantityStep.getText().equals("+/- 1")) {
					
					buttonNewQuantityStep.setText("+/- 10");
					spinnerNewQuantityModel.setStepSize(10);
					
					
				}else if( buttonNewQuantityStep.getText().equals("+/- 10") ) {
					
					buttonNewQuantityStep.setText("+/- 100");
					spinnerNewQuantityModel.setStepSize(100);
					
				}else {
					
					buttonNewQuantityStep.setText("+/- 1");
					spinnerNewQuantityModel.setStepSize(1);
					
				}
				
				
			}
		});
		
		JLabel label_2 = new JLabel("Min Storage Quantity");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spinnerNewMinQuantity = new JSpinner();
		spinnerNewMinQuantityModel = new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1));
		spinnerNewMinQuantity.setModel(spinnerNewMinQuantityModel);
		
		buttonNewMinQuantityStep = new JButton("+/- 1");
		buttonNewMinQuantityStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(buttonNewMinQuantityStep.getText().equals("+/- 1")) {
					
					buttonNewMinQuantityStep.setText("+/- 10");
					spinnerNewMinQuantityModel.setStepSize(10);
					
					
				}else if( buttonNewMinQuantityStep.getText().equals("+/- 10") ) {
					
					buttonNewMinQuantityStep.setText("+/- 100");
					spinnerNewMinQuantityModel.setStepSize(100);
					
				}else {
					
					buttonNewMinQuantityStep.setText("+/- 1");
					spinnerNewMinQuantityModel.setStepSize(1);
					
				}
				
				
			}
		});
		
		JLabel label_3 = new JLabel("Max Storage Quantity");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spinnerNewMaxQuantity = new JSpinner();
		spinnerNewMaxQuantityModel = new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1));
		spinnerNewMaxQuantity.setModel(spinnerNewMaxQuantityModel);
		
		buttonNewMaxQuantityStep = new JButton("+/- 1");
		buttonNewMaxQuantityStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(buttonNewMaxQuantityStep.getText().equals("+/- 1")) {
					
					buttonNewMaxQuantityStep.setText("+/- 10");
					spinnerNewMaxQuantityModel.setStepSize(10);
					
					
				}else if( buttonNewMaxQuantityStep.getText().equals("+/- 10") ) {
					
					buttonNewMaxQuantityStep.setText("+/- 100");
					spinnerNewMaxQuantityModel.setStepSize(100);
					
				}else {
					
					buttonNewMaxQuantityStep.setText("+/- 1");
					spinnerNewMaxQuantityModel.setStepSize(1);
					
				}
				
				
			}
		});
		
		JLabel lblNewLabel_4 = new JLabel("Update Stock");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_content = new GroupLayout(content);
		gl_content.setHorizontalGroup(
			gl_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_content.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4)
						.addGroup(gl_content.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_content.createSequentialGroup()
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addComponent(spinnerNewMinQuantity, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(buttonNewMinQuantityStep, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_content.createSequentialGroup()
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addComponent(spinnerNewMaxQuantity, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(buttonNewMaxQuantityStep, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_content.createSequentialGroup()
								.addComponent(lblNewQuantity, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addComponent(spinnerNewQuantity, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(buttonNewQuantityStep, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_content.createSequentialGroup()
							.addGroup(gl_content.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_content.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_content.createParallelGroup(Alignment.LEADING, false)
									.addComponent(spinnerMinQuantity, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
									.addComponent(spinnerInitialQuantity)
									.addComponent(spinnerMaxQuantity)))))
					.addGap(336))
		);
		gl_content.setVerticalGroup(
			gl_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_content.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_content.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(spinnerInitialQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(spinnerMinQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3)
								.addComponent(spinnerMaxQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(36)
							.addComponent(lblNewLabel_4)
							.addGap(18)
							.addGroup(gl_content.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_content.createSequentialGroup()
									.addGap(4)
									.addComponent(lblNewQuantity))
								.addGroup(gl_content.createSequentialGroup()
									.addGap(1)
									.addComponent(spinnerNewQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_content.createSequentialGroup()
							.addGap(187)
							.addComponent(buttonNewQuantityStep)))
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_content.createSequentialGroup()
							.addGap(4)
							.addComponent(label_2))
						.addGroup(gl_content.createSequentialGroup()
							.addGap(1)
							.addComponent(spinnerNewMinQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(buttonNewMinQuantityStep))
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_content.createSequentialGroup()
							.addGap(3)
							.addComponent(label_3))
						.addComponent(spinnerNewMaxQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonNewMaxQuantityStep))
					.addContainerGap(100, Short.MAX_VALUE))
		);
		content.setLayout(gl_content);
		
		buttonUpdateIngredientStock = new JButton("Update Ingredient Stock");
		buttonUpdateIngredientStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ingredientToUpdate.setQuantity(Double.parseDouble(spinnerNewQuantity.getValue().toString()));
				ingredientToUpdate.setMin_capacity(Double.parseDouble(spinnerNewMinQuantity.getValue().toString()));
				ingredientToUpdate.setMax_capacity(Double.parseDouble(spinnerNewMaxQuantity.getValue().toString()));
				
				JOptionPane.showMessageDialog(null, "Ingredient stock updated successfully!");
				
				//Write to File
				FileHandling.exportToFile(REMS_Registry.getInstance());
				
				
			}
		});
		panel_1.add(buttonUpdateIngredientStock);
		setLayout(groupLayout);

	}
	
	public void setIngredientToUpdate(Ingredient ingredientToUpdate) {
		
		this.ingredientToUpdate = ingredientToUpdate;
		
		textFieldName.setText( ingredientToUpdate.getName() );
		
		spinnerInitialQuantity.setValue(ingredientToUpdate.getQuantity());
		spinnerMinQuantity.setValue(ingredientToUpdate.getMin_capacity());
		spinnerMaxQuantity.setValue(ingredientToUpdate.getMax_capacity());
		
		spinnerNewQuantity.setValue(ingredientToUpdate.getQuantity());
		spinnerNewMinQuantity.setValue(ingredientToUpdate.getMin_capacity());
		spinnerNewMaxQuantity.setValue(ingredientToUpdate.getMax_capacity());
		
		
	}
}
