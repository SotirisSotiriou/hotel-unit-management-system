package com.hums.application.restaurant;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.hums.restaurantManagementSystem.Dish;
import com.hums.restaurantManagementSystem.Dish.MealType;
import com.hums.tools.data.FileHandling;
import com.hums.restaurantManagementSystem.Ingredient;
import com.hums.restaurantManagementSystem.IngredientQuantityPair;
import com.hums.restaurantManagementSystem.REMS_Registry;



public class CreateDishPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textFieldDishName;
	private JButton buttonAddDish;
	private JPanel panel_1;
	private JButton buttonAddIngredient;
	private JButton buttonSetDishName;
	private JList<String> ingredientsList;
	private JScrollPane scrollPaneIngredientsList;
	private JPanel panel;
	private JPanel panel_2;
	private JScrollPane scrollPaneIngredientQuantity;
	private JTable ingredientQuantityTable;
	private JSpinner spinnerQuantity;
	private final ButtonGroup buttonGroupMealType = new ButtonGroup();
	private JRadioButton rbBreakfast;
	private JRadioButton rbLaunch;
	private JRadioButton rbDinner;
	private DefaultListModel<String> ingredientsListModel;
	private DefaultTableModel ingredientQuantityTableModel;
	private JCheckBox buttonSaturday;
	private JCheckBox buttonSunday;
	private JCheckBox buttonFriday;
	private JCheckBox buttonThursday;
	private JCheckBox buttonMonday;
	private JCheckBox buttonTuesday;
	private JCheckBox buttonWednesday;

	/**
	 * Create the panel.
	 */
	public CreateDishPanel() {
		
		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
		);
		
		buttonAddDish = new JButton("Add Dish");
		buttonAddDish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				ArrayList<Integer> menu = new ArrayList<Integer>(7);
				
				for (int i = 0; i < 7; i++)
					  menu.add(0);
				
				if(buttonMonday.isSelected())
					menu.set(0, 1);
				if(buttonTuesday.isSelected())
					menu.set(1, 1);
				if(buttonWednesday.isSelected())
					menu.set(2, 1);
				if(buttonThursday.isSelected())
					menu.set(3, 1);
				if(buttonFriday.isSelected())
					menu.set(4, 1);
				if(buttonSaturday.isSelected())
					menu.set(5, 1);
				if(buttonSunday.isSelected())
					menu.set(6, 1);
				
				MealType meal;
				
				if(rbBreakfast.isSelected())
					meal = MealType.Breakfast;
				else if (rbLaunch.isSelected())
					meal = MealType.Launch;
				else
					meal = MealType.Dinner;
				
				ArrayList<IngredientQuantityPair> ingredients = getIngredientQuantityPair();
				
				Dish newDish = new Dish(textFieldDishName.getText(), ingredients, meal);
				newDish.changeMenu(menu);
				
				REMS_Registry.getInstance().getDishList().addDish(newDish);
				
				
				
				JOptionPane.showMessageDialog(null, "New dish created successfully!");
				
				//Write to file
				FileHandling.exportToFile(REMS_Registry.getInstance());
				
				resetAllFields();
				
			}
		});
		panel_1.add(buttonAddDish);
		
		scrollPaneIngredientsList = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("Ingredient Quantity");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		buttonAddIngredient = new JButton("Add");
		buttonAddIngredient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( ingredientsList.getSelectedValue() != null  ) {
					
					String ingredient = ingredientsList.getSelectedValue();
					
					Double ingredientQuantity = Double.parseDouble( String.valueOf(spinnerQuantity.getValue()) );
					
					ingredientsListModel.removeElement( ingredientsList.getSelectedValue() );
					
					ingredientQuantityTableModel.addRow(new Object[] {ingredient,ingredientQuantity});
					
					if(buttonSetDishName.isEnabled())
						buttonAddDish.setEnabled(false);
					else
						buttonAddDish.setEnabled(true);
					
					
				}else {
					JOptionPane.showMessageDialog(null, "Select ingredient First!","Warning",JOptionPane.WARNING_MESSAGE);
				}
				
				
				
				
				
				
			}
		});
		
		panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		scrollPaneIngredientQuantity = new JScrollPane();
		
		spinnerQuantity = new JSpinner();
		
		spinnerQuantity.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		((DefaultEditor) spinnerQuantity.getEditor()).getTextField().setEnabled(false);
		
		JPanel panel_3 = new JPanel();
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneIngredientsList, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(7)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(86)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(spinnerQuantity, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(buttonAddIngredient, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneIngredientQuantity, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollPaneIngredientsList, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
								.addComponent(scrollPaneIngredientQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(53, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(spinnerQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonAddIngredient))
							.addGap(31))))
		);
		
		buttonMonday = new JCheckBox("Monday");
		
		buttonTuesday = new JCheckBox("Tuesday");
		
		buttonWednesday = new JCheckBox("Wednesday");
		
		buttonThursday = new JCheckBox("Thursday");
		
		buttonFriday = new JCheckBox("Friday");
		
		buttonSaturday = new JCheckBox("Saturday");
		
		buttonSunday = new JCheckBox("Sunday");
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(117)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
						.addComponent(buttonTuesday)
						.addComponent(buttonWednesday, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(buttonThursday)
						.addComponent(buttonFriday)
						.addComponent(buttonSaturday)
						.addComponent(buttonSunday)
						.addComponent(buttonMonday))
					.addContainerGap(125, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(buttonMonday)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonTuesday)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonWednesday)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonThursday)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonFriday)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonSaturday)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonSunday)
					.addContainerGap(10, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		ingredientQuantityTable = new JTable();
		
		ingredientQuantityTableModel = new DefaultTableModel( null, new String[] {
				"Ingredient Name", "Quantity"
		}) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		ingredientQuantityTable.setModel(ingredientQuantityTableModel);
		
		
		
		
		scrollPaneIngredientQuantity.setViewportView(ingredientQuantityTable);
		
		JLabel lblNewLabel = new JLabel("Dish Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		textFieldDishName = new JTextField();
		textFieldDishName.setColumns(10);
		
		buttonSetDishName = new JButton("Set Dish Info");
		buttonSetDishName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(textFieldDishName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Dish name cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else {
					
					textFieldDishName.setEditable(false);
					rbBreakfast.setEnabled(false);
					rbLaunch.setEnabled(false);
					rbDinner.setEnabled(false);
					buttonSetDishName.setEnabled(false);
					ingredientsList.setEnabled(true);
					
					if(ingredientQuantityTableModel.getRowCount()!=0) {
						buttonAddDish.setEnabled(true);
					}else {
						buttonAddDish.setEnabled(false);
					}
					
					if(ingredientsListModel.getSize()!=0) {	
						buttonAddIngredient.setEnabled(true);
					}else {
						buttonAddIngredient.setEnabled(false);
					}
					
					
				}
			
				
				
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("Meal Type");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		rbBreakfast = new JRadioButton("Breakfast");
		buttonGroupMealType.add(rbBreakfast);
		
		rbLaunch = new JRadioButton("Launch");
		buttonGroupMealType.add(rbLaunch);
		rbLaunch.setSelected(true);
		
		rbDinner = new JRadioButton("Dinner");
		buttonGroupMealType.add(rbDinner);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
							.addGroup(gl_panel_2.createSequentialGroup()
								.addGap(30)
								.addComponent(rbBreakfast)
								.addGap(27)
								.addComponent(rbLaunch)
								.addGap(28)
								.addComponent(rbDinner)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(74)
							.addComponent(textFieldDishName, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
							.addGap(80))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(110)
							.addComponent(buttonSetDishName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(113)))
					.addGap(20))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldDishName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addComponent(lblNewLabel_2)
					.addGap(7)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(rbDinner)
						.addComponent(rbLaunch)
						.addComponent(rbBreakfast))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addComponent(buttonSetDishName)
					.addGap(28))
		);
		panel_2.setLayout(gl_panel_2);
		
		ingredientsList = new JList<String>();
		ingredientsListModel = new DefaultListModel<String>();
		ingredientsList.setModel(ingredientsListModel);
		
		
		
		
		scrollPaneIngredientsList.setViewportView(ingredientsList);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
	
	public void resetAllFields() {
		
		textFieldDishName.setText("");
		textFieldDishName.setEditable(true);
		buttonSetDishName.setEnabled(true);
		rbBreakfast.setEnabled(true);
		rbLaunch.setEnabled(true);
		rbDinner.setEnabled(true);
		
		ingredientsList.setEnabled(false);
		ingredientQuantityTableModel.setRowCount(0);
		
		buttonAddDish.setEnabled(false);
		buttonAddIngredient.setEnabled(false);
		
		updateModel();
		
		
	}
	
	public void updateModel() {
		
		REMS_Registry aReg = REMS_Registry.getInstance();
		
		ingredientsListModel.clear();
		ingredientQuantityTableModel.setRowCount(0);
		
		for (Ingredient ingredient : aReg.getStorage().getIngredients()) {
			
			ingredientsListModel.addElement(ingredient.toString());
			
			
		}
		
	}
	
	public ArrayList<IngredientQuantityPair> getIngredientQuantityPair() {
		
		
		ArrayList<IngredientQuantityPair> dishIngredients = new ArrayList<IngredientQuantityPair>(); 
		
		if(ingredientQuantityTable.getRowCount()!=0) {
			
			for (int row = 0; row < ingredientQuantityTable.getRowCount(); row++) {
				
				Ingredient ingredient = REMS_Registry.getInstance().getStorage().getIngredientByToString( (String) ingredientQuantityTable.getValueAt(row, 0) );
				
				Double quantity = Double.parseDouble(String.valueOf(ingredientQuantityTable.getValueAt(row, 1)) ) ;
				
				dishIngredients.add( new IngredientQuantityPair(ingredient,quantity) );
				
				
			}
			
			
			
			
		}
		
		return dishIngredients;
		
	}
}
