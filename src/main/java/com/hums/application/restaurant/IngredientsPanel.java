package com.hums.application.restaurant;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.hums.restaurantManagementSystem.Ingredient;
import com.hums.restaurantManagementSystem.REMS_Registry;
import com.hums.tools.data.FileHandling;

public class IngredientsPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton buttonDeleteIngredient;
	private JPanel panel_1;
	private JTable ingredientsTable;
	private DefaultTableModel ingredientTableModel;
	private JButton buttonUpdateStock;

	/**
	 * Create the panel.
	 */
	public IngredientsPanel() {
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
		);
		
		ingredientsTable = new JTable();
		ingredientTableModel = new DefaultTableModel( null, new String[] {
				"ID", "Ingredient Name", "Storage Quantity", "Min Quantity", "Max Quantity"
		}) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		ingredientsTable.setModel(ingredientTableModel);
		scrollPane.setViewportView(ingredientsTable);
		
		buttonDeleteIngredient = new JButton("Delete Ingredient");
		buttonDeleteIngredient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = ingredientsTable.getSelectedRow();
				if(ingredientTableModel.getRowCount()!=0 && row != -1) {
					int id = (int) ingredientTableModel.getValueAt(row, 0);
					
					REMS_Registry.getInstance().getStorage().deleteIngredientByID(id);
					
					//Write to file
					FileHandling.exportToFile(REMS_Registry.getInstance());
					
					updateModel();
					
				}
				
			}
		});
		panel_1.add(buttonDeleteIngredient);
		
		buttonUpdateStock = new JButton("Update Ingredient Stock");
		buttonUpdateStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int row = ingredientsTable.getSelectedRow();
				if(ingredientTableModel.getRowCount()!=0 && row != -1) {
					int id = (int) ingredientTableModel.getValueAt(row, 0);
					
					Ingredient ingredientToUpdate = REMS_Registry.getInstance().getStorage().getIngredientByID(id);
					
					REMS_Frame.showUpdateIngredientStockPanel(ingredientToUpdate);
					
				}
				
				
			}
		});
		panel_1.add(buttonUpdateStock);
		setLayout(groupLayout);

	}
	
	public void updateModel() {
		REMS_Registry aReg = REMS_Registry.getInstance();
		
		ingredientTableModel.setRowCount(0);
		
		for (Ingredient ingredient : aReg.getStorage().getIngredients()) {
			
			ingredientTableModel.addRow(new Object[]{ingredient.getId(), ingredient.getName(), ingredient.getQuantity(),ingredient.getMin_capacity(),ingredient.getMax_capacity()});
			
			
		}
			
		ingredientTableModel.fireTableDataChanged();
		
		
	}

}
