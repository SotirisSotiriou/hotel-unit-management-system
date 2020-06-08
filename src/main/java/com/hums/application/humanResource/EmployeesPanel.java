package com.hums.application.humanResource;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.hums.humanResourceManagementSystem.*;
public class EmployeesPanel extends JPanel {
	private JTable employeesTable;
	private DefaultTableModel employeesTableModel;
	private JButton buttonDeleteEmployee;
	private JButton buttonEditEmployee;

	/**
	 * Create the panel.
	 */
	public EmployeesPanel() {
		
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
		
		buttonEditEmployee = new JButton("Edit Employee");
		buttonEditEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				HR_Frame.showEditEmployeePanel();
				
			}
		});
		panel.add(buttonEditEmployee);
		
		buttonDeleteEmployee = new JButton("Delete Employee");
		panel.add(buttonDeleteEmployee);
		
		employeesTable = new JTable();
		
		employeesTableModel = new DefaultTableModel(null, new String[] {
				"Last Name", "First Name", "Phone", "Email", "Address", "SSN", "Type", "Salary"
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
		HRMS_Registry reg= HRMS_Registry.getInstance();
		
		employeesTableModel.setRowCount(0);
		for(Employee employee: reg.getEmpList().getEmployees())
		{
			employeesTableModel.addRow(new Object[] {employee.getLastname(),employee.getFirstname(),
					employee.getPhone(),employee.getEmail(),employee.getAddress(),
					employee.getAddress(),employee.getSsn(),employee.getType(),employee.getSalary()});
		}
		employeesTableModel.fireTableDataChanged();
		
	}

}
