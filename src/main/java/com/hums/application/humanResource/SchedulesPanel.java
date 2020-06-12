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

public class SchedulesPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private static JTable employeesTable;
	private static DefaultTableModel employeesTableModel;
	private JButton buttonMoveSchedule;
	private JButton buttonViewEmpSchedule;
	
	public SchedulesPanel() {
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
		
		buttonMoveSchedule = new JButton("Move Schedule");
		buttonMoveSchedule.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				HRMS_Registry.getInstance().getEmpList().moveSchedule();
				updateModel();
				FileHandling.exportToFile(HRMS_Registry.getInstance());
				JOptionPane.showMessageDialog(null, "Schedule moved");
			}
			
		});
		panel.add(buttonMoveSchedule);
		
		buttonViewEmpSchedule = new JButton("View Schedule");
		buttonViewEmpSchedule.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int row= employeesTable.getSelectedRow();
				if( employeesTableModel.getRowCount()!=0 && row !=-1)
				{
					String lastname= (String)employeesTableModel.getValueAt(row, 0);
					String firstname=(String)employeesTableModel.getValueAt(row, 1);
					Employee empToView= HRMS_Registry.getInstance().getEmpList().searchEmployee(firstname, lastname);
					HR_Frame.showEmpSchedulePanel(empToView);
					EmpSchedulePanel.updateModel();
				}
			}
			
		});
		panel.add(buttonViewEmpSchedule);
		
		employeesTable = new JTable();
		
		employeesTableModel = new DefaultTableModel(null, new String[] {
				"Last Name", "First Name", "Type"
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
			employeesTableModel.addRow(new Object[] {employee.getLastname(),employee.getFirstname(),
													employee.getType()});
		}
		employeesTableModel.fireTableDataChanged();
		
	}
}
