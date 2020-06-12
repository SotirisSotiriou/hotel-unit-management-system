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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.hums.roomManagementSystem.RMS_Registry;
import com.hums.roomManagementSystem.RoomPenthouse;
import com.hums.roomManagementSystem.RoomRegular;
import com.hums.roomManagementSystem.RoomVIP;
import com.hums.tools.data.FileHandling;


public class NewRoomPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JPanel footer;
	private JButton buttonAddRoom;
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
	private JSpinner spinnerRoomNumber;
	private JSpinner spinnerFloor;
	private JButton buttonStep1;
	private JButton buttonStep100;
	private SpinnerNumberModel spinnerModelRoomNumber;

	/**
	 * Create the panel.
	 */
	public NewRoomPanel() {
		
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
		
		buttonAddRoom = new JButton("Add Room");
		buttonAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				RMS_Registry aReg = RMS_Registry.getInstance();
				
				int floor =-1;
				int roomNumber=-1;

				roomNumber = Integer.parseInt(spinnerRoomNumber.getValue().toString());				
			    
				floor = Integer.parseInt(spinnerFloor.getValue().toString());				
				
				
				if( !(roomNumber == -1 || floor == -1) ) {
					int beds=2;
					
					if(rbSingle.isSelected())
						beds=1;
					else if(rbDouble.isSelected())
						beds=2;
					else
						beds=3;
					
					
					if(rbRegular.isSelected())
						aReg.getRoomList().addRoom(new RoomRegular(roomNumber,floor,beds));
					else if (rbPenthouse.isSelected())
						aReg.getRoomList().addRoom(new RoomPenthouse(roomNumber,floor,beds));
					else
						aReg.getRoomList().addRoom(new RoomVIP(roomNumber,floor,beds));
					
					
					//Write to File
					FileHandling.exportToFile(RMS_Registry.getInstance());
					
					
				}
			    
				
				
			}
		});
		footer.add(buttonAddRoom);
		
		labelRoomNo = new JLabel("Room No");
		labelRoomNo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		labelRoomType = new JLabel("Room Type");
		labelRoomType.setHorizontalAlignment(SwingConstants.RIGHT);
		
		rbRegular = new JRadioButton("Regular");
		rbRegular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				rbSingle.setEnabled(true);
				rbTriple.setEnabled(true);
				
			}
		});
		rbRegular.setSelected(true);
		roomType.add(rbRegular);
		
		rbPenthouse = new JRadioButton("Penthouse");
		rbPenthouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				rbSingle.setEnabled(false);
				rbTriple.setEnabled(false);
				rbDouble.setSelected(true);
				
			}
		});
		roomType.add(rbPenthouse);
		
		rbVIP = new JRadioButton("VIP");
		rbVIP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				rbSingle.setEnabled(true);
				rbDouble.setEnabled(true);
				rbTriple.setEnabled(true);
				
			}
		});
		roomType.add(rbVIP);
		
		labelFloor = new JLabel("Floor");
		labelFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		
		rbSingle = new JRadioButton("Single");
		bedsGroup.add(rbSingle);
		
		rbDouble = new JRadioButton("Double");
		rbDouble.setSelected(true);
		bedsGroup.add(rbDouble);
		
		rbTriple = new JRadioButton("Triple");
		bedsGroup.add(rbTriple);
		
		labelBeds = new JLabel("Beds");
		labelBeds.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spinnerRoomNumber = new JSpinner();
		spinnerModelRoomNumber = new SpinnerNumberModel(1, 1, 99999, 1);
		spinnerRoomNumber.setModel(spinnerModelRoomNumber);
		((DefaultEditor) spinnerRoomNumber.getEditor()).getTextField().setEditable(false);
		
		spinnerFloor = new JSpinner();
		spinnerFloor.setModel(new SpinnerNumberModel(0, 0, 400, 1));
		((DefaultEditor) spinnerFloor.getEditor()).getTextField().setEditable(false);
		buttonStep1 = new JButton("+/- 1");
		buttonStep1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				spinnerModelRoomNumber.setStepSize(1);
				
			}
		});
		
		buttonStep100 = new JButton("+/- 10");
		buttonStep100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				spinnerModelRoomNumber.setStepSize(10);
				
			}
		});
		GroupLayout gl_content = new GroupLayout(content);
		gl_content.setHorizontalGroup(
			gl_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_content.createParallelGroup(Alignment.LEADING, false)
						.addComponent(labelBeds, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(labelRoomNo, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
						.addComponent(labelFloor, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
						.addComponent(labelRoomType, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_content.createParallelGroup(Alignment.LEADING)
						.addComponent(rbTriple)
						.addComponent(rbDouble)
						.addComponent(rbSingle)
						.addComponent(rbRegular)
						.addComponent(rbVIP)
						.addComponent(rbPenthouse)
						.addGroup(gl_content.createSequentialGroup()
							.addGroup(gl_content.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(spinnerFloor, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinnerRoomNumber, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 62, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(buttonStep1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonStep100)
					.addContainerGap(365, Short.MAX_VALUE))
		);
		gl_content.setVerticalGroup(
			gl_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelRoomNo)
						.addComponent(spinnerRoomNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonStep1)
						.addComponent(buttonStep100))
					.addGap(18)
					.addGroup(gl_content.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelFloor)
						.addComponent(spinnerFloor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
					.addContainerGap(133, Short.MAX_VALUE))
		);
		content.setLayout(gl_content);
		setLayout(groupLayout);

	}
}
