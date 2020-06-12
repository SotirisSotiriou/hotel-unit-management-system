package com.hums.application.room;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.hums.roomManagementSystem.RMS_Registry;
import com.hums.roomManagementSystem.Room;
import com.hums.tools.data.FileHandling;



public class RoomsPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTable roomsTable;
	private DefaultTableModel roomsTableModel;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton buttonShowReserved;
	private JButton buttonEditRoom;
	private JButton buttonDeleteRoom;

	/**
	 * Create the panel.
	 */
	public RoomsPanel() {
		
		scrollPane = new JScrollPane();
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
		);
		
		buttonShowReserved = new JButton("Show Reserved");
		buttonShowReserved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				buttonEditRoom.setEnabled(false);
				
				
			}
		});
		panel.add(buttonShowReserved);
		
		buttonEditRoom = new JButton("Edit Room");
		buttonEditRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				int row = roomsTable.getSelectedRow();
				if(roomsTableModel.getRowCount()!=0 && row != -1) {
					int number = (int) roomsTableModel.getValueAt(row, 0);
					
					Room roomToEdit = RMS_Registry.getInstance().getRoomList().getRoomByNumber(number);
					
					RMS_Frame.showEditRoomCard(roomToEdit);
					
					
					
					
				}
				
				
			}
		});
		panel.add(buttonEditRoom);
		
		buttonDeleteRoom = new JButton("Delete Room");
		buttonDeleteRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = roomsTable.getSelectedRow();
				if(roomsTableModel.getRowCount()!=0 && row != -1) {
					int number = (int) roomsTableModel.getValueAt(row, 0);
					
					RMS_Registry.getInstance().getRoomList().deleteRoomByNumber(number);
					
					//Write to File
					FileHandling.exportToFile(RMS_Registry.getInstance());
					
					updateModel();
				}
				
				
			}
		});
		panel.add(buttonDeleteRoom);
		
		roomsTable = new JTable();
		
		roomsTableModel = new DefaultTableModel( null, new String[] {
				"Room No", "Floor", "Beds", "Type", "Cost/Day"
		}) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		
		roomsTable.setModel(roomsTableModel);
		
		scrollPane.setViewportView(roomsTable);
		setLayout(groupLayout);
		
		updateModel();

	}
	
	public void updateModel() {
		
		RMS_Registry aReg = RMS_Registry.getInstance();
		
		roomsTableModel.setRowCount(0);
		
		for (Room room : aReg.getRoomList().getRooms()) {
			
			if(room.getBeds()==1) 
				roomsTableModel.addRow(new Object[]{room.getRoomNumber(),room.getFloor(),"Single",room.getClass().getSimpleName().substring(4), room.getCostPerDay()});
			else if(room.getBeds()==2) 
				roomsTableModel.addRow(new Object[]{room.getRoomNumber(),room.getFloor(),"Double",room.getClass().getSimpleName().substring(4), room.getCostPerDay()});
			else
				roomsTableModel.addRow(new Object[]{room.getRoomNumber(),room.getFloor(),"Triple",room.getClass().getSimpleName().substring(4), room.getCostPerDay()});
				
			
		}
		
		roomsTableModel.fireTableDataChanged();
		
	}
	
	
}
