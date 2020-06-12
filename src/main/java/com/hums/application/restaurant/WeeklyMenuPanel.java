package com.hums.application.restaurant;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.hums.restaurantManagementSystem.Dish;
import com.hums.restaurantManagementSystem.REMS_Registry;

public class WeeklyMenuPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTable weeklyTable;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton buttonMonday;
	private JButton buttonTuesday;
	private JButton buttonWednesday;
	private JButton buttonThursday;
	private JButton buttonFriday;
	private JButton buttonSaturday;
	private JButton buttonSunday;
	private DefaultTableModel weeklyTableModel;

	/**
	 * Create the panel.
	 */
	public WeeklyMenuPanel() {
		
		scrollPane = new JScrollPane();
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		weeklyTableModel = new DefaultTableModel( null, new String[] {
				"Dish", "Day", "Meal Type"
		}) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		weeklyTable = new JTable();
		weeklyTable.setModel(weeklyTableModel);
		scrollPane.setViewportView(weeklyTable);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		buttonMonday = new JButton("Monday");
		buttonMonday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				updateModelDay(0);
				
			}
		});
		panel.add(buttonMonday);
		
		buttonTuesday = new JButton("Tuesday");
		buttonTuesday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				updateModelDay(1);
				
			}
		});
		panel.add(buttonTuesday);
		
		buttonWednesday = new JButton("Wednesday");
		buttonWednesday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateModelDay(2);
				
			}
		});
		panel.add(buttonWednesday);
		
		buttonThursday = new JButton("Thursday");
		buttonThursday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateModelDay(3);
				
			}
		});
		panel.add(buttonThursday);
		
		buttonFriday = new JButton("Friday");
		buttonFriday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateModelDay(4);
				
			}
		});
		panel.add(buttonFriday);
		
		buttonSaturday = new JButton("Saturday");
		buttonSaturday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateModelDay(5);
				
			}
		});
		panel.add(buttonSaturday);
		
		buttonSunday = new JButton("Sunday");
		buttonSunday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateModelDay(6);
				
			}
		});
		panel.add(buttonSunday);
		setLayout(groupLayout);

	}
	
	public void updateModel() {
		
		weeklyTableModel.setRowCount(0);
		
		ArrayList<Dish> dishes = REMS_Registry.getInstance().getDishList().getDishes();
		
		for (Dish dish : dishes) {
			
			
			ArrayList<Integer> dishMenu = dish.getMenu();
			
			for (int i = 0; i < dishMenu.size(); i++) {
				
				if(dishMenu.get(i) == 1) {
					
					if (i == 0) 
						weeklyTableModel.addRow(new Object[] { dish.getName(), "Monday", dish.getMealType() });
					if (i == 1)
						weeklyTableModel.addRow(new Object[] { dish.getName(), "Tuesday", dish.getMealType() });
					if (i == 2)
						weeklyTableModel.addRow(new Object[] { dish.getName(), "Wednesday", dish.getMealType() });
					if (i == 3)
						weeklyTableModel.addRow(new Object[] { dish.getName(), "Thursday", dish.getMealType() });
					if (i == 4)
						weeklyTableModel.addRow(new Object[] { dish.getName(), "Friday", dish.getMealType() });
					if (i == 5)
						weeklyTableModel.addRow(new Object[] { dish.getName(), "Saturday", dish.getMealType() });
					if (i == 6)
						weeklyTableModel.addRow(new Object[] { dish.getName(), "Sunday", dish.getMealType() });
					
				}
					
					
			}

		}
		
		weeklyTableModel.fireTableDataChanged();
		
	}
	
	public void updateModelDay(int day) {
		
		weeklyTableModel.setRowCount(0);
		
		ArrayList<Dish> dishes = REMS_Registry.getInstance().getDishList().getDishes();
		
		for (Dish dish : dishes) {
			
			ArrayList<Integer> dishMenu = dish.getMenu();
				
			if(dishMenu.get(day) == 1) {
					
				if (day == 0) 
					weeklyTableModel.addRow(new Object[] { dish.getName(), "Monday", dish.getMealType() });
				else if (day == 1)
					weeklyTableModel.addRow(new Object[] { dish.getName(), "Tuesday", dish.getMealType() });
				else if (day == 2)
					weeklyTableModel.addRow(new Object[] { dish.getName(), "Wednesday", dish.getMealType() });
				else if (day == 3)
					weeklyTableModel.addRow(new Object[] { dish.getName(), "Thursday", dish.getMealType() });
				else if (day == 4)
					weeklyTableModel.addRow(new Object[] { dish.getName(), "Friday", dish.getMealType() });
				else if (day == 5)
					weeklyTableModel.addRow(new Object[] { dish.getName(), "Saturday", dish.getMealType() });
				else 
					weeklyTableModel.addRow(new Object[] { dish.getName(), "Sunday", dish.getMealType() });
				
				
			}
			
			
			
		}
		
		weeklyTableModel.fireTableDataChanged();
		
	}
	
}
