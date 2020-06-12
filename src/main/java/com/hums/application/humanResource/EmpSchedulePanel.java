package com.hums.application.humanResource;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.hums.humanResourceManagementSystem.DailySchedule;
import com.hums.humanResourceManagementSystem.Employee;
import com.hums.humanResourceManagementSystem.HRMS_Registry;
import com.hums.tools.data.FileHandling;

public class EmpSchedulePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static JLabel labelTitle;
	private static JTable scheduleTable;
	private static DefaultTableModel scheduleTableModel;
	private JButton buttonAddSchedule;
	private JButton buttonRemoveSchedule;
	private JButton buttonBack;
	private static Employee empToView = null;
	
	public EmpSchedulePanel() {
		
		JScrollPane scrollPane = new JScrollPane();

		labelTitle = new JLabel("title");
		labelTitle.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(labelTitle, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(labelTitle, GroupLayout.DEFAULT_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
		);
		
		buttonBack = new JButton("Back");
		panel.add(buttonBack);
		buttonBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				HR_Frame.showSchedulesPanel();
			}
			
		});
		
		buttonAddSchedule = new JButton("Add Schedule");
		buttonAddSchedule.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				HR_Frame.showAddSchedulePanel(empToView);
			}
			
		});
		panel.add(buttonAddSchedule);
		
		buttonRemoveSchedule = new JButton("Remove Schedule");
		buttonRemoveSchedule.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row= scheduleTable.getSelectedRow();
				if( scheduleTableModel.getRowCount()!=0 && row !=-1)
				{
					String day= (String)scheduleTableModel.getValueAt(row, 0);
					LocalTime start = (LocalTime) scheduleTableModel.getValueAt(row, 1);
					LocalTime end = (LocalTime) scheduleTableModel.getValueAt(row, 2);
					empToView.getSchedule().removeSchedule(empToView.getSchedule().searchSchedule(day, start, end));
					updateModel();
					FileHandling.exportToFile(HRMS_Registry.getInstance());
				}
			}
			
		});
		panel.add(buttonRemoveSchedule);
		
		scheduleTable = new JTable();
		
		scheduleTableModel = new DefaultTableModel(null, new String[] {
				"Day", "Start", "End"
			}) {
				private static final long serialVersionUID = 1L;

				@Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
			};
			
		scheduleTable.setModel(scheduleTableModel);
			
		scrollPane.setViewportView(scheduleTable);
		setLayout(groupLayout);
	}
	
	public static void updateModel() {
		scheduleTableModel.setRowCount(0);
		for(DailySchedule daily: empToView.getSchedule().getWeekSchedule())
		{
			scheduleTableModel.addRow(new Object[] {daily.getDay(), daily.getStart().toLocalTime(), daily.getEnd().toLocalTime()});
		}
		scheduleTableModel.fireTableDataChanged();
	}
	
	public static void setEmployeeToView(Employee emp) {
		empToView = emp;
		labelTitle.setText(emp.getLastname() + " " + emp.getFirstname() + " (" + emp.getType() + ")");
		labelTitle.setFont(new Font("Serif", Font.BOLD, 18));
	}
}
