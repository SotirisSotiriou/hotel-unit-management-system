package com.hums.application.humanResource;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.hums.humanResourceManagementSystem.DailySchedule;
import com.hums.humanResourceManagementSystem.Employee;
import com.hums.humanResourceManagementSystem.HRMS_Registry;
import com.hums.tools.data.FileHandling;

import java.awt.Font;
import java.awt.FlowLayout;

public class AddSchedulePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	
	private static Employee employee;
	private JButton buttonAdd;
	private JButton buttonBack;
	private JComboBox<String> comboBoxDays;
	private DefaultComboBoxModel<String> daysModel;
	private JComboBox<String> comboBoxStartHours;
	private DefaultComboBoxModel<String> startHoursModel;
	private JComboBox<String> comboBoxStartMinutes;
	private DefaultComboBoxModel<String> startMinutesModel;
	private JComboBox<String> comboBoxEndHours;
	private DefaultComboBoxModel<String> endHoursModel;
	private JComboBox<String> comboBoxEndMinutes;
	private DefaultComboBoxModel<String> endMinutesModel;
	
	public AddSchedulePanel() {
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
			);

		JLabel labelDay = new JLabel("Select Day");
		labelDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		comboBoxDays = new JComboBox<String>();
		daysModel = new DefaultComboBoxModel<String>(new String[] {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"});
		comboBoxDays.setModel(daysModel);
		
		JLabel labelStart = new JLabel("Start");
		labelStart.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel labelStartHour = new JLabel("Hour");
		
		comboBoxStartHours = new JComboBox<String>();
		startHoursModel = new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
																		 "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
																		 "22", "23"});
		comboBoxStartHours.setModel(startHoursModel);
		
		JLabel labelDot1 = new JLabel(":");
		
		JLabel labelStartMinute = new JLabel("Minute");
		
		comboBoxStartMinutes = new JComboBox<String>();
		startMinutesModel = new DefaultComboBoxModel<String>(new String[] {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"});
		comboBoxStartMinutes.setModel(startMinutesModel);
		
		JLabel labelEnd = new JLabel("End");
		labelEnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel labelEndHour = new JLabel("Hour");
		
		JLabel labelEndMinute = new JLabel("Minute");
		
		comboBoxEndHours = new JComboBox<String>();
		endHoursModel = new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
																		 "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
																		 "22", "23"});
		comboBoxEndHours.setModel(endHoursModel);
		
		JLabel labelDot2 = new JLabel(":");
		
		comboBoxEndMinutes = new JComboBox<String>();
		endMinutesModel = new DefaultComboBoxModel<String>(new String[] {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"});
		comboBoxEndMinutes.setModel(endMinutesModel);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(40)
					.addComponent(labelDay, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(31)
					.addComponent(comboBoxDays, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(56)
					.addComponent(labelStart))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addComponent(labelStartHour)
					.addGap(50)
					.addComponent(labelStartMinute))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addComponent(comboBoxStartHours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(labelDot1)
					.addGap(18)
					.addComponent(comboBoxStartMinutes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(62)
					.addComponent(labelEnd))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addComponent(labelEndHour)
					.addGap(52)
					.addComponent(labelEndMinute))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addComponent(comboBoxEndHours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(labelDot2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(comboBoxEndMinutes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addComponent(labelDay)
					.addGap(6)
					.addComponent(comboBoxDays, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addComponent(labelStart)
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(labelStartHour)
						.addComponent(labelStartMinute))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBoxStartHours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(labelDot1))
						.addComponent(comboBoxStartMinutes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(labelEnd)
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(labelEndHour)
						.addComponent(labelEndMinute))
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBoxEndHours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(labelDot2))
						.addComponent(comboBoxEndMinutes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		panel.setLayout(gl_panel);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		buttonBack = new JButton("Back");
		panel_1.add(buttonBack);
		buttonBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				HR_Frame.showEmpSchedulePanel(employee);
				EmpSchedulePanel.updateModel();
			}
			
		});
		
		buttonAdd = new JButton("Add");
		panel_1.add(buttonAdd);
		setLayout(groupLayout);
		
		
		buttonAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String day = (String) comboBoxDays.getSelectedItem();
				String startHourText = (String) comboBoxStartHours.getSelectedItem();
				String startMinuteText = (String) comboBoxStartMinutes.getSelectedItem();
				String endHourText = (String) comboBoxEndHours.getSelectedItem();
				String endMinuteText = (String) comboBoxEndMinutes.getSelectedItem();
				
				int startHour = Integer.parseInt(startHourText);
				int startMinute = Integer.parseInt(startMinuteText);
				int endHour = Integer.parseInt(endHourText);
				int endMinute = Integer.parseInt(endMinuteText);
				
				DailySchedule ds = null;
				
				if(day.equals("MONDAY")) {
					ds = new DailySchedule(LocalDateTime.of(LocalDate.of(2020, 6, 1), LocalTime.of(startHour, startMinute)), LocalDateTime.of(LocalDate.of(2020, 6, 1), LocalTime.of(endHour, endMinute)));
					
				}
				else if(day.equals("TUESDAY")) {
					ds = new DailySchedule(LocalDateTime.of(LocalDate.of(2020, 6, 2), LocalTime.of(startHour, startMinute)), LocalDateTime.of(LocalDate.of(2020, 6, 2), LocalTime.of(endHour, endMinute)));
					
				}
				else if(day.equals("WEDNESDAY")) {
					ds = new DailySchedule(LocalDateTime.of(LocalDate.of(2020, 6, 3), LocalTime.of(startHour, startMinute)), LocalDateTime.of(LocalDate.of(2020, 6, 3), LocalTime.of(endHour, endMinute)));
					
				}
				else if(day.equals("THURSDAY")) {
					ds = new DailySchedule(LocalDateTime.of(LocalDate.of(2020, 6, 4), LocalTime.of(startHour, startMinute)), LocalDateTime.of(LocalDate.of(2020, 6, 4), LocalTime.of(endHour, endMinute)));
					
				}
				else if(day.equals("FRIDAY")) {
					ds = new DailySchedule(LocalDateTime.of(LocalDate.of(2020, 6, 5), LocalTime.of(startHour, startMinute)), LocalDateTime.of(LocalDate.of(2020, 6, 5), LocalTime.of(endHour, endMinute)));
					
				}
				else if(day.equals("SATURDAY")) {
					ds = new DailySchedule(LocalDateTime.of(LocalDate.of(2020, 6, 6), LocalTime.of(startHour, startMinute)), LocalDateTime.of(LocalDate.of(2020, 6, 6), LocalTime.of(endHour, endMinute)));
					
				}
				else if(day.equals("SUNDAY")) {
					ds = new DailySchedule(LocalDateTime.of(LocalDate.of(2020, 6, 7), LocalTime.of(startHour, startMinute)), LocalDateTime.of(LocalDate.of(2020, 6, 7), LocalTime.of(endHour, endMinute)));
				}
				
				if(!ds.equals(null)) employee.getSchedule().setSchedule(ds);
				
				JOptionPane.showMessageDialog(null, "New schedule added");
				
				FileHandling.exportToFile(HRMS_Registry.getInstance());
				
				comboBoxDays.setSelectedItem("Monday");
				comboBoxStartHours.setSelectedItem("00");
				comboBoxStartMinutes.setSelectedItem("00");
				comboBoxEndHours.setSelectedItem("00");
				comboBoxEndMinutes.setSelectedItem("00");
			}
			
		});	
	}
	
	public static void setEmployee(Employee emp) {
		employee = emp;
	}
}
