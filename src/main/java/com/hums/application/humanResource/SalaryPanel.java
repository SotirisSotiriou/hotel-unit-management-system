package com.hums.application.humanResource;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.hums.humanResourceManagementSystem.Employee;
import com.hums.humanResourceManagementSystem.HRMS_Registry;
import com.hums.tools.data.FileHandling;

public class SalaryPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private static JTable employeesTable;
	private static DefaultTableModel employeesTableModel;
	private JButton buttonChangeSalary;
	
	public SalaryPanel() {
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
		
		
		buttonChangeSalary = new JButton("Change Salary");
		buttonChangeSalary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = employeesTable.getSelectedRow();
				if(employeesTableModel.getRowCount()!=0 && row != -1) {
					String lastName =(String) employeesTableModel.getValueAt(row, 0);
					String firstName =(String) employeesTableModel.getValueAt(row, 1);
					Employee emp = HRMS_Registry.getInstance().getEmpList().searchEmployee(firstName, lastName);
					String priceText = null;
					Double price = null;
					do {
						priceText = JOptionPane.showInputDialog(null, "Set new price");
						if(priceText==null) {
							
							break;
							
						}
						if(isNumeric(priceText) && Double.parseDouble(priceText) > 0) {
							price = Double.parseDouble(priceText);
							emp.getSalary().setPrice(price);
						}
						else {
							JOptionPane.showMessageDialog(null, "Please type a valid number");
						}						
					} while(!isNumeric(priceText));
					updateModel();
					FileHandling.exportToFile(HRMS_Registry.getInstance());
				}
			}
		});
		panel.add(buttonChangeSalary);
		
		employeesTable = new JTable();
		
		employeesTableModel = new DefaultTableModel(null, new String[] {
				"Employee Name", "Type", "Salary"
			}) {
				private static final long serialVersionUID = 1L;

				@Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
			};
			
		employeesTable.setModel(employeesTableModel);
		updateModel();
			
		scrollPane.setViewportView(employeesTable);
		setLayout(groupLayout);

	}
	
	public static void updateModel() {
		HRMS_Registry reg= HRMS_Registry.getInstance();
		
		employeesTableModel.setRowCount(0);
		for(Employee employee: reg.getEmpList().getEmployees())
		{
			employeesTableModel.addRow(new Object[] {employee.toString(),
													employee.getType(),employee.getSalary().getPrice()});
		}
		employeesTableModel.fireTableDataChanged();
		
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        @SuppressWarnings("unused")
			double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
