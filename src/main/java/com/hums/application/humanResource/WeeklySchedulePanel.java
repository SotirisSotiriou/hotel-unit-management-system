package com.hums.application.humanResource;



import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.hums.humanResourceManagementSystem.DailySchedule;
import com.hums.humanResourceManagementSystem.Employee;
import com.hums.humanResourceManagementSystem.HRMS_Registry;



public class WeeklySchedulePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTable employeesTable;
	private DefaultTableModel employeesTableModel;
	private JButton buttonShiftChange;

	/**
	 * Create the panel.
	 */
	public WeeklySchedulePanel() {
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
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
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
		);
		
		buttonShiftChange = new JButton("Change Shift to +8 hours");
		buttonShiftChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				HRMS_Registry.getInstance().getEmpList().moveSchedule();
				
				JOptionPane.showMessageDialog(null, "Shift changed successfully for all employees!");
				
				updateModel();
				
			}
		});
		panel.add(buttonShiftChange);
		
		employeesTable = new JTable();
		
		employeesTableModel = new DefaultTableModel(null, new String[] {
				"Employee", "Day", "Start Hour", "End Hour"
			}) {
				private static final long serialVersionUID = 1L;

				@Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
			};
			
		employeesTable.setModel(employeesTableModel);
			
		scrollPane.setViewportView(employeesTable);
		setLayout(groupLayout);

	}
	
	public void updateModel() {
		
		HRMS_Registry aReg = HRMS_Registry.getInstance();
		
		employeesTableModel.setRowCount(0);
		
		for (Employee employee : aReg.getEmpList().getEmployees()) {
			
			ArrayList<DailySchedule> empSchedule = employee.getSchedule().getWeekSchedule();
			
			for (DailySchedule dailySchedule : empSchedule) {
				
				employeesTableModel.addRow(new Object[]{employee.toString(), dailySchedule.getDay(), dailySchedule.getStart().getHour(), dailySchedule.getEnd().getHour()});
				
			}
			
			
		}
		
		employeesTableModel.fireTableDataChanged();
		
	}
	
}
