package com.hums.application.room;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.hums.roomManagementSystem.RMS_Registry;
import com.hums.roomManagementSystem.Room;
import com.hums.roomManagementSystem.RoomPenthouse;
import com.hums.roomManagementSystem.RoomRegular;
import com.hums.tools.data.FileHandling;



public class EditRoomPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JPanel footer;
	private JButton buttonEditRoom;
	private JPanel content;
	private JLabel labelRoomNo;
	private JLabel labelFloor;
	private JLabel labelRoomType;
	private final ButtonGroup roomType = new ButtonGroup();
	private JRadioButton rbVIP;
	private JRadioButton rbPenthouse;
	private JRadioButton rbRegular;
	private final ButtonGroup bedsGroup = new ButtonGroup();
	private JLabel labelBeds;
	private JRadioButton rbTriple;
	private JRadioButton rbDouble;
	private JRadioButton rbSingle;
	@SuppressWarnings("unused")
	private SpinnerNumberModel spinnerModelRoomNumber;
	private JTextField textFieldRoomNo;
	private JTextField textFieldFloor;
	private JSpinner spinnerCost;
	private JLabel labelCostType;
	
	private Room roomToEdit;
	private int beds;
	private int cost;

	/**
	 * Create the panel.
	 */
	public EditRoomPanel() {
		
		content = new JPanel();
		content.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		footer = new JPanel();
		footer.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(footer, GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
				.addComponent(content, GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(content, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(footer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		footer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		buttonEditRoom = new JButton("Edit Room");
		buttonEditRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(rbSingle.isSelected())
					beds = 1;
				else if (rbDouble.isSelected())
					beds = 2;
				else
					beds = 3;
				
				cost = (int) spinnerCost.getValue();
				
				RMS_Registry.getInstance().getRoomList().editRoom(roomToEdit, beds, cost);
			    
				JOptionPane.showMessageDialog(null, "Room updated successfully!\nRedirecting to Rooms panel...");
				
				//Write to File
				FileHandling.exportToFile(RMS_Registry.getInstance());
				
				RMS_Frame.showCard("rooms");
				
				
			}
		});
		footer.add(buttonEditRoom);
		
		labelRoomNo = new JLabel("Room No");
		labelRoomNo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		labelRoomType = new JLabel("Room Type");
		labelRoomType.setHorizontalAlignment(SwingConstants.RIGHT);
		
		rbRegular = new JRadioButton("Regular");
		rbRegular.setEnabled(false);
		rbRegular.setSelected(true);
		roomType.add(rbRegular);
		
		rbPenthouse = new JRadioButton("Penthouse");
		rbPenthouse.setEnabled(false);
		roomType.add(rbPenthouse);
		
		rbVIP = new JRadioButton("VIP");
		rbVIP.setEnabled(false);
		roomType.add(rbVIP);
		
		labelFloor = new JLabel("Floor");
		labelFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		
		rbSingle = new JRadioButton("Single");
		rbSingle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				beds = 1;
				updateCostSpinner();
				
			}
		});
		bedsGroup.add(rbSingle);
		
		rbDouble = new JRadioButton("Double");
		rbDouble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				beds = 2;
				updateCostSpinner();
				
			}
		});
		rbDouble.setSelected(true);
		bedsGroup.add(rbDouble);
		
		rbTriple = new JRadioButton("Triple");
		rbTriple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				beds = 3;
				updateCostSpinner();
				
			}
		});
		bedsGroup.add(rbTriple);
		
		labelBeds = new JLabel("Beds");
		labelBeds.setHorizontalAlignment(SwingConstants.RIGHT);
		spinnerModelRoomNumber = new SpinnerNumberModel(1, 1, 99999, 1);
		
		textFieldRoomNo = new JTextField();
		textFieldRoomNo.setEditable(false);
		textFieldRoomNo.setColumns(10);
		
		textFieldFloor = new JTextField();
		textFieldFloor.setEditable(false);
		textFieldFloor.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Cost/Day");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spinnerCost = new JSpinner(new SpinnerNumberModel(0, 0, 99999, 1));
		((DefaultEditor) spinnerCost.getEditor()).getTextField().setEditable(false);
		
		labelCostType = new JLabel("/Day");
		GroupLayout gl_content = new GroupLayout(content);
		gl_content.setHorizontalGroup(
			gl_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_content.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(labelBeds, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(labelRoomNo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
						.addComponent(labelFloor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
						.addComponent(labelRoomType, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_content.createParallelGroup(Alignment.LEADING)
						.addComponent(rbDouble)
						.addComponent(rbSingle)
						.addComponent(rbRegular)
						.addComponent(rbVIP)
						.addComponent(rbPenthouse)
						.addComponent(textFieldRoomNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldFloor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_content.createSequentialGroup()
							.addGroup(gl_content.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(spinnerCost, Alignment.LEADING)
								.addComponent(rbTriple, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labelCostType)))
					.addContainerGap(401, Short.MAX_VALUE))
		);
		gl_content.setVerticalGroup(
			gl_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelRoomNo)
						.addComponent(textFieldRoomNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelFloor)
						.addComponent(textFieldFloor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(rbRegular)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(rbPenthouse)
						.addComponent(labelRoomType))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rbVIP)
					.addGap(18)
					.addComponent(rbSingle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(rbDouble)
						.addComponent(labelBeds))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rbTriple)
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(spinnerCost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelCostType))
					.addContainerGap(71, Short.MAX_VALUE))
		);
		content.setLayout(gl_content);
		setLayout(groupLayout);

	}
	
	public void setRoomToEdit(Room roomToEdit) {
		
		this.roomToEdit = roomToEdit;
		this.beds = roomToEdit.getBeds();
		this.cost = roomToEdit.getCostPerDay();
		
		textFieldRoomNo.setText(String.valueOf(roomToEdit.getRoomNumber()));
		textFieldFloor.setText(String.valueOf(roomToEdit.getFloor()));
		
		if(roomToEdit.getClass().equals(RoomRegular.class)) {
			rbSingle.setEnabled(true);
			rbTriple.setEnabled(true);
			
			rbRegular.setSelected(true);
		}
		else if (roomToEdit.getClass().equals(RoomPenthouse.class)) {
			rbSingle.setEnabled(false);
			rbTriple.setEnabled(false);
			
			rbPenthouse.setSelected(true);
			rbDouble.setSelected(true);
		}
		else {
			rbSingle.setEnabled(true);
			rbTriple.setEnabled(true);
			
			rbVIP.setSelected(true);
		}
		
		if(roomToEdit.getBeds()==1) {
			rbSingle.setSelected(true);
			beds = 1;
		}
		else if(roomToEdit.getBeds()==2) {
			rbDouble.setSelected(true);
			beds = 2;
		}
		else {
			rbTriple.setSelected(true);
			beds = 3;
		}
		
		spinnerCost.setValue(roomToEdit.getCostPerDay());
		
		
		
	}
	
	private void updateCostSpinner() {
		
		if(rbRegular.isSelected())
			spinnerCost.setValue( RMS_Registry.getInstance().getRoomList().getRegularCost() * beds );
		else if(rbPenthouse.isSelected())
			spinnerCost.setValue( RMS_Registry.getInstance().getRoomList().getPenthouseCost() * beds );
		else
			spinnerCost.setValue( RMS_Registry.getInstance().getRoomList().getVipCost() * beds );
		
	}
}
