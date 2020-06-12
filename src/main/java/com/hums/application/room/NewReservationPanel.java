package com.hums.application.room;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.hums.roomManagementSystem.Customer;
import com.hums.roomManagementSystem.RMS_Registry;
import com.hums.roomManagementSystem.Room;
import com.hums.roomManagementSystem.RoomReservation;
import com.hums.tools.data.FileHandling;

public class NewReservationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final ButtonGroup roomTypeGroup = new ButtonGroup();
	private final ButtonGroup bedsGroup = new ButtonGroup();
	private JRadioButton rbTriple;
	private JRadioButton rbDouble;
	private JRadioButton rbSingle;
	private JComboBox<String> comboBoxCustomers;
	private DatePicker checkInDatePicker;
	private DatePicker checkOutDatePicker;
	private JRadioButton rbRegular;
	private JRadioButton rbPenthouse;
	private JRadioButton rbVIP;
	private JCheckBox checkboxBreakfast;
	private JCheckBox checkboxLaunch;
	private JCheckBox checkboxDinner;
	private JLabel labelBreakfastCost;
	private JLabel labelLaunchCost;
	private JLabel labelDinnerCost;
	private JList<String> roomList;
	private JLabel lblNewLabel_3;
	private JLabel labelSelectCustomer;
	private JButton buttonSubmitReservation;
	private JPanel footer;
	private JPanel content;
	private JLabel labelCheckIn;
	private JLabel labelRoomType;
	private JLabel labelMeals;
	private JLabel labelBeds;
	private JLabel labelCheckOut;
	private JScrollPane scrollPaneRoomList;
	private DefaultComboBoxModel<String> customersModel;
	private DatePickerSettings dateSettingsCheckIn;
	private DatePickerSettings dateSettingsCheckOut;
	private DefaultListModel<String> roomsListModel;
	private JTextField textFieldCustomerFilter;
	private JButton buttonFilterClear;
	private JScrollPane scrollPaneNotes;
	private JTextArea textAreaNotes;

	/**
	 * Create the panel.
	 */
	public NewReservationPanel() {
		
		footer = new JPanel();
		footer.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		content = new JPanel();
		content.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(footer, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
				.addComponent(content, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(content, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(footer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		footer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		buttonSubmitReservation = new JButton("Submit Reservation");
		buttonSubmitReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Customer aCustomer = null;
				
				if(comboBoxCustomers.getItemCount()!=0) {
					String customerString = comboBoxCustomers.getSelectedItem().toString();
					aCustomer = RMS_Registry.getInstance().getCustomerList().getCustomerByToString(customerString);
				}
				
				
				
				String roomNumberAsString = roomList.getSelectedValue();
				
				Room aRoom = null;
				
				if(roomNumberAsString==null) {
					
					
					int roomNumber = Integer.parseInt(roomsListModel.getElementAt(0));
					
					aRoom = RMS_Registry.getInstance().getRoomList().getRoomByNumber(roomNumber);
					
					
					
					
					
				}else {
					
					aRoom = RMS_Registry.getInstance().getRoomList().getRoomByNumber(Integer.parseInt(roomList.getSelectedValue()));
					
				}
				
				boolean breakfast = true;
				boolean launch = true;
				boolean dinner = true;
				
				if(!checkboxBreakfast.isSelected())
					breakfast = false;
				if(!checkboxLaunch.isSelected())
					launch=false;
				if(!checkboxDinner.isSelected())
					dinner = false;
				
				String notes = textAreaNotes.getText();
				
				RoomReservation reservation = new RoomReservation(aCustomer,checkInDatePicker.getDate(), checkOutDatePicker.getDate(),aRoom, breakfast, launch, dinner, notes);
				
				RMS_Registry.getInstance().getReservationList().addReservation(reservation);
				
				//Write to File
				FileHandling.exportToFile(RMS_Registry.getInstance());
				
				updateAvailableRoomsList();
				
			}
		});
		footer.add(buttonSubmitReservation);
		
		labelCheckOut = new JLabel("Check out");
		labelCheckOut.setHorizontalAlignment(SwingConstants.RIGHT);
		
		labelRoomType = new JLabel("Room Type");
		labelRoomType.setHorizontalAlignment(SwingConstants.RIGHT);
		
		labelMeals = new JLabel("Meals");
		labelMeals.setHorizontalAlignment(SwingConstants.RIGHT);
		
		labelCheckIn = new JLabel("Check In");
		labelCheckIn.setHorizontalAlignment(SwingConstants.RIGHT);
		
		checkboxDinner = new JCheckBox("Dinner");
		
		checkboxLaunch = new JCheckBox("Launch");
		
		checkboxBreakfast = new JCheckBox("Breakfast");
		
		dateSettingsCheckOut = new DatePickerSettings();
		dateSettingsCheckOut.setAllowEmptyDates(false);
		checkOutDatePicker = new DatePicker(dateSettingsCheckOut);
		checkOutDatePicker.setDate(LocalDate.now().plusDays(1));
		checkOutDatePicker.addDateChangeListener(new DateChangeListener() {
			public void dateChanged(DateChangeEvent arg0) {
				
				updateAvailableRoomsList();
				
			}
		});
		checkOutDatePicker.getComponentDateTextField().setEditable(false);
		
		
		
		dateSettingsCheckIn = new DatePickerSettings();
		
		dateSettingsCheckIn.setAllowEmptyDates(false);
		checkInDatePicker = new DatePicker(dateSettingsCheckIn);
		dateSettingsCheckIn.setDateRangeLimits(LocalDate.now(), null);
		
		dateSettingsCheckOut.setDateRangeLimits(checkInDatePicker.getDate().plusDays(1), null);
		checkInDatePicker.addDateChangeListener(new DateChangeListener() {
			public void dateChanged(DateChangeEvent arg0) {
				
				
				dateSettingsCheckOut.setDateRangeLimits(checkInDatePicker.getDate().plusDays(1), null);
				checkOutDatePicker.setDate(checkInDatePicker.getDate().plusDays(1));
				
				updateAvailableRoomsList();
				
			}
		});
		checkInDatePicker.setDateToToday();
		checkInDatePicker.getComponentDateTextField().setEditable(false);
		
		comboBoxCustomers = new JComboBox<String>();
		customersModel = new DefaultComboBoxModel<String>(new String[] {""});
		
		comboBoxCustomers.setModel(customersModel);
		
		
		
		rbPenthouse = new JRadioButton("Penthouse");
		rbPenthouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				rbSingle.setEnabled(false);
				rbTriple.setEnabled(false);
				rbDouble.setSelected(true);
				
				labelBreakfastCost.setVisible(false);
				labelLaunchCost.setVisible(false);
				labelDinnerCost.setVisible(false);
				
				checkboxBreakfast.setEnabled(false);
				checkboxLaunch.setEnabled(false);
				checkboxDinner.setEnabled(false);
				
				checkboxBreakfast.setSelected(true);
				checkboxLaunch.setSelected(true);
				checkboxDinner.setSelected(true);
				
				updateAvailableRoomsList();
				
			}
		});
		roomTypeGroup.add(rbPenthouse);
		
		rbRegular = new JRadioButton("Regular");
		rbRegular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				rbSingle.setEnabled(true);
				rbTriple.setEnabled(true);
				
				labelBreakfastCost.setVisible(true);
				labelLaunchCost.setVisible(true);
				labelDinnerCost.setVisible(true);
				
				checkboxBreakfast.setEnabled(true);
				checkboxLaunch.setEnabled(true);
				checkboxDinner.setEnabled(true);
				
				checkboxBreakfast.setSelected(false);
				checkboxLaunch.setSelected(false);
				checkboxDinner.setSelected(false);
				
				updateAvailableRoomsList();
			}
		});
		rbRegular.setSelected(true);
		roomTypeGroup.add(rbRegular);
		
		rbVIP = new JRadioButton("VIP");
		rbVIP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				rbSingle.setEnabled(true);
				rbTriple.setEnabled(true);
				
				labelBreakfastCost.setVisible(false);
				labelLaunchCost.setVisible(false);
				labelDinnerCost.setVisible(false);
				
				checkboxBreakfast.setEnabled(false);
				checkboxLaunch.setEnabled(false);
				checkboxDinner.setEnabled(false);
				
				checkboxBreakfast.setSelected(true);
				checkboxLaunch.setSelected(true);
				checkboxDinner.setSelected(true);
				
				updateAvailableRoomsList();
			}
		});
		roomTypeGroup.add(rbVIP);
		
		rbSingle = new JRadioButton("Single");
		rbSingle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				updateAvailableRoomsList();
				
			}
		});
		bedsGroup.add(rbSingle);
		
		rbDouble = new JRadioButton("Double");
		rbDouble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateAvailableRoomsList();
				
			}
		});
		bedsGroup.add(rbDouble);
		rbDouble.setSelected(true);
		
		rbTriple = new JRadioButton("Triple");
		rbTriple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateAvailableRoomsList();
				
			}
		});
		bedsGroup.add(rbTriple);
		
		labelBeds = new JLabel("Beds");
		labelBeds.setHorizontalAlignment(SwingConstants.RIGHT);
		
		labelBreakfastCost = new JLabel("$/day");
		
		labelLaunchCost = new JLabel("$/day");
		
		labelDinnerCost = new JLabel("$/day");
		
		scrollPaneRoomList = new JScrollPane();
		
		lblNewLabel_3 = new JLabel("Select Room");
		
		labelSelectCustomer = new JLabel("Select Customer");
		labelSelectCustomer.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel = new JLabel("Filter Customer");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldCustomerFilter = new JTextField();
		textFieldCustomerFilter.setColumns(10);
		
		JButton btnNewButton = new JButton("Filter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!textFieldCustomerFilter.getText().equals(""))
					updateCustomersModelFilter(textFieldCustomerFilter.getText());
				else
					updateCustomersModel();
			}
		});
		
		buttonFilterClear = new JButton("Clear");
		buttonFilterClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textFieldCustomerFilter.setText("");
				updateCustomersModel();
				
			}
		});
		
		JLabel labelNotes = new JLabel("Reservation Notes");
		
		scrollPaneNotes = new JScrollPane();
		GroupLayout gl_content = new GroupLayout(content);
		gl_content.setHorizontalGroup(
			gl_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_content.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(labelCheckIn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
						.addComponent(labelRoomType, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
						.addComponent(labelMeals, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
						.addComponent(labelBeds, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
						.addComponent(labelCheckOut, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
						.addComponent(labelSelectCustomer))
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_content.createSequentialGroup()
							.addComponent(textFieldCustomerFilter, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buttonFilterClear))
						.addComponent(checkInDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_content.createSequentialGroup()
							.addGroup(gl_content.createParallelGroup(Alignment.LEADING)
								.addComponent(rbTriple)
								.addComponent(rbDouble)
								.addComponent(rbSingle)
								.addGroup(gl_content.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(gl_content.createSequentialGroup()
										.addComponent(checkboxDinner)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(labelDinnerCost))
									.addGroup(gl_content.createSequentialGroup()
										.addComponent(checkboxLaunch)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(labelLaunchCost))
									.addGroup(gl_content.createSequentialGroup()
										.addComponent(checkboxBreakfast)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(labelBreakfastCost))
									.addComponent(checkOutDatePicker, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(rbRegular)
								.addComponent(rbPenthouse)
								.addComponent(rbVIP))
							.addGap(89)
							.addGroup(gl_content.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPaneRoomList, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3)))
						.addComponent(comboBoxCustomers, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE))
					.addGap(59)
					.addGroup(gl_content.createParallelGroup(Alignment.LEADING)
						.addComponent(labelNotes)
						.addComponent(scrollPaneNotes, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		gl_content.setVerticalGroup(
			gl_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textFieldCustomerFilter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton)
						.addComponent(buttonFilterClear)
						.addComponent(labelNotes))
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_content.createSequentialGroup()
							.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxCustomers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelSelectCustomer))
							.addGap(18)
							.addGroup(gl_content.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_content.createSequentialGroup()
									.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
										.addComponent(checkInDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(labelCheckIn))
									.addGap(18)
									.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
										.addComponent(checkOutDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(labelCheckOut))
									.addGap(18)
									.addComponent(rbRegular)
									.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
										.addComponent(rbPenthouse)
										.addComponent(labelRoomType))
									.addComponent(rbVIP)
									.addGap(18)
									.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
										.addComponent(checkboxBreakfast)
										.addComponent(labelBreakfastCost))
									.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
										.addComponent(checkboxLaunch)
										.addComponent(labelLaunchCost)
										.addComponent(labelMeals))
									.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
										.addComponent(checkboxDinner)
										.addComponent(labelDinnerCost))
									.addGap(18)
									.addComponent(rbSingle)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
										.addComponent(rbDouble)
										.addComponent(labelBeds))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rbTriple))
								.addGroup(gl_content.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scrollPaneRoomList, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE))))
						.addComponent(scrollPaneNotes))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		
		textAreaNotes = new JTextArea();
		scrollPaneNotes.setViewportView(textAreaNotes);
		
		roomList = new JList<String>();
		
		roomsListModel = new DefaultListModel<String>();
		roomList.setModel(roomsListModel);
		
		scrollPaneRoomList.setViewportView(roomList);
		
		content.setLayout(gl_content);
		setLayout(groupLayout);
		

	}
	
	public void updateCustomersModel() {
		
		textAreaNotes.setText("");
		
		ArrayList<Customer> customers = RMS_Registry.getInstance().getCustomerList().getCustomers();
		
		customersModel.removeAllElements();
		
		for (Customer customer : customers) {
			
			customersModel.addElement(customer.toString());
			
		}
		
		if(customersModel.getSize()==0)
			buttonSubmitReservation.setEnabled(false);
		else
			buttonSubmitReservation.setEnabled(true);
		
	}
	
	public void updateCustomersModelFilter(String filterTerm) {
		
		ArrayList<Customer> filteredCustomers = RMS_Registry.getInstance().getCustomerList().getCustomersByTerm(filterTerm);
		
		customersModel.removeAllElements();
		
		for (Customer filteredCustomer : filteredCustomers) {
			
			customersModel.addElement(filteredCustomer.toString());
			
		}
		
		if(customersModel.getSize()==0)
			buttonSubmitReservation.setEnabled(false);
		else
			buttonSubmitReservation.setEnabled(true);
		
	}
	
	public void setSubmitEnabled(boolean enabled) {
		
		buttonSubmitReservation.setEnabled(enabled);
		
	}
	
	public void updateAvailableRoomsList() {
		
		int beds=-1;
		
		if(rbSingle.isSelected())
			beds=1;
		else if(rbDouble.isSelected())
			beds=2;
		else
			beds=3;
		
		int roomType = -1;
		
		if(rbRegular.isSelected())
			roomType=1;
		else if(rbPenthouse.isSelected())
			roomType=2;
		else
			roomType=3;
		
		ArrayList<Room> availableRooms = new ArrayList<Room>();
		availableRooms = RMS_Registry.getInstance().getRoomList().getAvailableRooms(checkInDatePicker.getDate(), 
																							checkOutDatePicker.getDate(), 
																							beds, 
																							roomType);
		
		if(availableRooms!=null && availableRooms.size()!=0 && customersModel.getSize()!=0) {
			roomsListModel.clear();
			for (Room room : availableRooms) {
				roomsListModel.addElement(String.valueOf(room.getRoomNumber()));
			}
			buttonSubmitReservation.setEnabled(true);
		}else {
			roomsListModel.clear();
			buttonSubmitReservation.setEnabled(false);
		}
		
	}
}
