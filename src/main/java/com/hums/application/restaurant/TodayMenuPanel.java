package com.hums.application.restaurant;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hums.restaurantManagementSystem.Dish;
import com.hums.restaurantManagementSystem.REMS_Registry;

public class TodayMenuPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTable todaysTable;
	private DefaultTableModel todaysTableModel;

	/**
	 * Create the panel.
	 */
	public TodayMenuPanel() {
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
		);
		
		todaysTableModel = new DefaultTableModel( null, new String[] {
				"Dish", "Meal Type"
		}) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		todaysTable = new JTable();
		todaysTable.setModel(todaysTableModel);
		scrollPane.setViewportView(todaysTable);
		setLayout(groupLayout);

	}
	
	
	public void updateModel() {
		
		todaysTableModel.setRowCount(0);
		
		int todayValue = LocalDate.now().getDayOfWeek().getValue() - 1;
		
		ArrayList<Dish> dishes = REMS_Registry.getInstance().getDishList().getDishes();
		
		for (Dish dish : dishes) {
			
			
			ArrayList<Integer> dishMenu = dish.getMenu();
			
			if(dishMenu.get(todayValue) == 1)
				
				todaysTableModel.addRow(new Object[] { dish.getName(), dish.getMealType() });
			
		}
		
		todaysTableModel.fireTableDataChanged();
		
	}
	

}
