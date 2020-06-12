package com.hums.application.event;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateTimeChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateTimeChangeEvent;
import com.hums.eventManagementSystem.Condition;
import com.hums.eventManagementSystem.EMS_Registry;
import com.hums.eventManagementSystem.EventReservation;
import com.hums.eventManagementSystem.Info;
import com.hums.tools.data.FileHandling;

public class NewEventPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textFieldEventType;
	private JTextField textFieldLastname;
	private JTextField textFieldFirstname;
	private JTextField textFieldPhone;
	private JTextArea textAreaNotes;
	private JScrollPane scrollPaneNotes;
	private DateTimePicker dateTimePickerStart;
	private DateTimePicker dateTimePickerEnd;
	private JPanel content;
	private JPanel footer;
	private JButton buttonNewEvent;
	private DatePickerSettings dateSettingsStart;
	private TimePickerSettings timeSettingsStart;
	private TimePickerSettings timeSettingsEnd;
	private DatePickerSettings dateSettingsEnd;
	private JSpinner spinnerCapacity;
	private SpinnerNumberModel spinnerCapacityModel;

	/**
	 * Create the panel.
	 */
	public NewEventPanel() {
		
		content = new JPanel();
		content.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		footer = new JPanel();
		footer.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(footer, GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
				.addComponent(content, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(content, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(footer, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
		);
		footer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		buttonNewEvent = new JButton("New Event");
		buttonNewEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String notes = "";
				String eventType = "";
				String lastname = "";
				String firstname = "";
				String phone = "";
				int capacity=0;
				
				LocalDate start = dateTimePickerStart.datePicker.getDate();
				LocalDate end = dateTimePickerEnd.datePicker.getDate();
				
				
				LocalTime bufferStartTime = dateTimePickerStart.timePicker.getTime();
				LocalTime startTime = LocalTime.of(bufferStartTime.getHour(), bufferStartTime.getMinute());
				
				LocalTime bufferEndTime = dateTimePickerEnd.timePicker.getTime();
				LocalTime endTime = LocalTime.of(bufferEndTime.getHour(), bufferEndTime.getMinute());
				
				boolean lastnameEmpty = true;
				boolean firstnameEmpty = true;
				boolean eventTypeEmpty = true;
				boolean phoneEmpty = true;
				boolean dateCorrect = false;
				
				
				
				notes = textAreaNotes.getText();
				
				eventType = textFieldEventType.getText();
				
				firstname = textFieldFirstname.getText();
				lastname = textFieldLastname.getText();
				phone = textFieldPhone.getText();
				
				capacity = Integer.parseInt( spinnerCapacity.getValue().toString() );
				
				String errorMessage = "Input error for:\n";
				
				
				
				
				
				if(!eventType.equals("")) {
					eventTypeEmpty=false;
				}else{
					errorMessage += "Event Type\n";
				}
				
				if(!firstname.equals("")) {
					firstnameEmpty = false;
				}else {
					errorMessage += "First Name\n";
				}
				
				if(!lastname.equals("")) {
					lastnameEmpty = false;
				}else {
					errorMessage += "Last Name\n";
				}
				
				
				if( phone.matches("[0-9]{10}") ) {
					phoneEmpty = false;
				} else {
					errorMessage += "Phone must be 10 digits";
				}
				
				if( (end.isAfter(start) || end.isEqual(start)) && endTime.isAfter(startTime) ) {
					
					dateCorrect = true;
					
				}else {
					errorMessage += "Date(End time must be after Start time)\n"; 
				}
				
				if( !errorMessage.equals("Input error for:\n")) {
					JOptionPane.showMessageDialog(null, errorMessage, "Input Error",JOptionPane.ERROR_MESSAGE);
				}
				
				if(dateCorrect && !phoneEmpty && !firstnameEmpty && !lastnameEmpty && !eventTypeEmpty) {
					
					
					Info info = new Info(firstname,lastname,phone,notes,capacity);
					
					EventReservation aReservation = new EventReservation(LocalDateTime.of(start, startTime), 
																			LocalDateTime.of(end, endTime),
																			eventType,
																			info,
																			Condition.FUTURE);
					
					if(EMS_Registry.getInstance().getEventReservationList().addReservation(aReservation)) {
						JOptionPane.showMessageDialog(null, "New reservation added!");
						//Write to file
						FileHandling.exportToFile(EMS_Registry.getInstance());
					}else {
						JOptionPane.showMessageDialog(null, "Error adding reservation!\nPossible causes: No hall available","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			
				
			}
		});
		footer.add(buttonNewEvent);
		
		JLabel lblNewLabel = new JLabel("Start Time");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_1 = new JLabel("End Time");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		dateSettingsEnd = new DatePickerSettings();
		timeSettingsEnd = new TimePickerSettings();
        dateSettingsEnd.setAllowEmptyDates(false);
        timeSettingsEnd.setAllowEmptyTimes(false);
        
        timeSettingsEnd.initialTime = LocalTime.of(20, 30);
        timeSettingsEnd.maximumVisibleMenuRows = 16;
        
		dateTimePickerEnd = new DateTimePicker(dateSettingsEnd, timeSettingsEnd);
		dateSettingsEnd.setDateRangeLimits(LocalDate.now(), null);
		
		dateSettingsStart = new DatePickerSettings();
        timeSettingsStart = new TimePickerSettings();
        dateSettingsStart.setAllowEmptyDates(false);
        timeSettingsStart.setAllowEmptyTimes(false);
        
        timeSettingsStart.initialTime = LocalTime.of(19, 00);
        timeSettingsStart.maximumVisibleMenuRows = 16;
        
		dateTimePickerStart = new DateTimePicker(dateSettingsStart, timeSettingsStart);
		
		
		dateSettingsStart.setDateRangeLimits(LocalDate.now(), null);
		
		dateTimePickerStart.addDateTimeChangeListener(new DateTimeChangeListener() {
			public void dateOrTimeChanged(DateTimeChangeEvent arg0) {
				
				dateSettingsEnd.setDateRangeLimits(dateTimePickerStart.datePicker.getDate(), null);
				dateTimePickerEnd.datePicker.setDate(dateTimePickerStart.datePicker.getDate());
				
				
			}
		});
		dateTimePickerStart.datePicker.setDateToToday();
		
		JLabel lblNewLabel_2 = new JLabel("Event Type");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldEventType = new JTextField();
		textFieldEventType.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Host's Info");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_4 = new JLabel("Event's Info");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_5 = new JLabel("Last Name");
		
		textFieldLastname = new JTextField();
		textFieldLastname.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("First Name");
		
		textFieldFirstname = new JTextField();
		textFieldFirstname.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Phone");
		
		textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Notes");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		
		scrollPaneNotes = new JScrollPane();
		
		JLabel lblNewLabel_9 = new JLabel("Capacity");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spinnerCapacity = new JSpinner();
		
		spinnerCapacityModel = new SpinnerNumberModel(1, 1, 99999, 1);
		spinnerCapacity.setModel(spinnerCapacityModel);
		((DefaultEditor) spinnerCapacity.getEditor()).getTextField().setEditable(false);
		
		JButton buttonSpinnerStep1 = new JButton("+/- 1");
		buttonSpinnerStep1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				spinnerCapacityModel.setStepSize(1);
				
			}
		});
		
		JButton buttonSpinnerStep10 = new JButton("+/- 10");
		buttonSpinnerStep10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				spinnerCapacityModel.setStepSize(10);
				
			}
		});
		
		GroupLayout gl_content = new GroupLayout(content);
		gl_content.setHorizontalGroup(
			gl_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_content.createParallelGroup(Alignment.LEADING, false)
						.addGroup(Alignment.TRAILING, gl_content.createSequentialGroup()
							.addGroup(gl_content.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNewLabel_9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_content.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_content.createSequentialGroup()
									.addGroup(gl_content.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblNewLabel_4)
										.addComponent(dateTimePickerEnd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(dateTimePickerStart, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(textFieldEventType, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
									.addGap(111)
									.addGroup(gl_content.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_content.createSequentialGroup()
											.addComponent(textFieldPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblNewLabel_7))
										.addGroup(gl_content.createSequentialGroup()
											.addComponent(textFieldFirstname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblNewLabel_6))
										.addGroup(gl_content.createSequentialGroup()
											.addComponent(textFieldLastname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblNewLabel_5))
										.addComponent(lblNewLabel_3)))
								.addGroup(gl_content.createSequentialGroup()
									.addComponent(spinnerCapacity, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(buttonSpinnerStep1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(buttonSpinnerStep10))))
						.addGroup(gl_content.createSequentialGroup()
							.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPaneNotes, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)))
					.addGap(73))
		);
		gl_content.setVerticalGroup(
			gl_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_content.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_content.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addGap(18)
							.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(dateTimePickerStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(dateTimePickerEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(textFieldEventType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_content.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addGap(18)
							.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldLastname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5))
							.addGap(18)
							.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldFirstname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_6))
							.addGap(18)
							.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_7))))
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_9)
						.addComponent(spinnerCapacity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonSpinnerStep1)
						.addComponent(buttonSpinnerStep10))
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_8)
						.addComponent(scrollPaneNotes, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		
		textAreaNotes = new JTextArea();
		textAreaNotes.setLineWrap(true);
		textAreaNotes.setWrapStyleWord(true);
		scrollPaneNotes.setViewportView(textAreaNotes);
		content.setLayout(gl_content);
		setLayout(groupLayout);
		
		dateTimePickerStart.datePicker.getComponentDateTextField().setEditable(false);
		dateTimePickerStart.timePicker.getComponentTimeTextField().setEditable(false);
		
		dateTimePickerEnd.datePicker.getComponentDateTextField().setEditable(false);
		dateTimePickerEnd.timePicker.getComponentTimeTextField().setEditable(false);

	}
	
	public void resetFields() {
		
		textAreaNotes.setText("");
		spinnerCapacity.setValue(1);
		
		textFieldEventType.setText("");
		textFieldFirstname.setText("");
		textFieldLastname.setText("");
		textFieldPhone.setText("");
		
		
	}
}
