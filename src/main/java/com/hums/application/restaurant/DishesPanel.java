package com.hums.application.restaurant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;


import com.hums.restaurantManagementSystem.Dish;
import com.hums.restaurantManagementSystem.IngredientQuantityPair;
import com.hums.restaurantManagementSystem.REMS_Registry;
import com.hums.tools.data.FileHandling;


public class DishesPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JScrollPane scrollPane;
	private DefaultComboBoxModel<String> dishesModel;
	private JComboBox<String> comboBoxDishes;
	private JTable ingredientsTable;
	private DefaultTableModel ingredientsTableModel;
	private JButton buttonDeleteDish;

	/**
	 * Create the panel.
	 */
	public DishesPanel() {
		
		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
		);
		
		ingredientsTable = new JTable();
		ingredientsTableModel = new DefaultTableModel(null, new String[] {
				"Ingredient Name", "Quantity"
			}) {
				private static final long serialVersionUID = 1L;

				@Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
			};
		ingredientsTable.setModel(ingredientsTableModel);
		
		
		scrollPane.setViewportView(ingredientsTable);
		
		comboBoxDishes = new JComboBox<String>();
		comboBoxDishes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				showDishIngredients();
				
			}
		});
		dishesModel = new DefaultComboBoxModel<String>(new String[] {""});
		comboBoxDishes.setModel(dishesModel);
		
		
		buttonDeleteDish = new JButton("Delete Dish");
		buttonDeleteDish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(dishesModel.getSize()!=0) {
					
					Dish dishToDelete = REMS_Registry.getInstance().getDishList().getDishByToString(comboBoxDishes.getSelectedItem().toString());
					
					REMS_Registry.getInstance().getDishList().getDishes().remove(dishToDelete);
					
					
					JOptionPane.showMessageDialog(null, "Dish deleted successfully!");
					
					//Write to file
					FileHandling.exportToFile(REMS_Registry.getInstance());
					
					updateDishesModel();
					
				}
				
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(196)
					.addComponent(comboBoxDishes, 0, 160, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonDeleteDish, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(183))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxDishes, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonDeleteDish))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
	}
	
	public void updateDishesModel() {
		
		
		ArrayList<Dish> dishes = REMS_Registry.getInstance().getDishList().getDishes();
		
		
		dishesModel.removeAllElements();
		
		for (Dish dish : dishes) {
			
			dishesModel.addElement(dish.toString());
			
		}
		
		showDishIngredients();
		
	}
	
	public void showDishIngredients() {
		
		if(dishesModel.getSize()!=0) {
			
			Dish dishToShow = REMS_Registry.getInstance().getDishList().getDishByToString(comboBoxDishes.getSelectedItem().toString());
			ArrayList<IngredientQuantityPair> ingredientsOfDish = dishToShow.getIngredientsList();
			
			ingredientsTableModel.setRowCount(0);
			
			for (IngredientQuantityPair iqp : ingredientsOfDish) {
				
				ingredientsTableModel.addRow(new Object[]{iqp.getIngredient().toString(),iqp.getQuantity()});
				
			}
			ingredientsTableModel.fireTableDataChanged();
			
		}
		
		
	}
}
