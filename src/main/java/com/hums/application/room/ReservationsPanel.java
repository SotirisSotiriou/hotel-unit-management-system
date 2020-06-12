package com.hums.application.room;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.hums.roomManagementSystem.RMS_Registry;
import com.hums.roomManagementSystem.RoomReservation;
import com.hums.tools.data.FileHandling;


public class ReservationsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable reservTable;
	private JPanel panel;
	private JButton buttonDelete;
	private JButton buttonMoreDetails;
	private JScrollPane scrollPane;
	private DefaultTableModel reservTableModel;
	private JButton buttonSearch;
	private JButton buttonShowAll;

	/**
	 * Create the panel.
	 */
	public ReservationsPanel() {
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		reservTable = new JTable();
		
		reservTableModel = new DefaultTableModel( null, new String[] {
				"ID", "Check In", "Check Out", "Customer", "Room","Price"
		}) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		reservTable.setModel(reservTableModel);
		reservTable.getColumnModel().getColumn(1).setPreferredWidth(49);
		scrollPane.setViewportView(reservTable);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		buttonDelete = new JButton("Delete Reservation");
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = reservTable.getSelectedRow();
				if(reservTableModel.getRowCount()!=0 && row != -1) {
					int id = (int) reservTableModel.getValueAt(row, 0);
					
					RMS_Registry.getInstance().getReservationList().deleteReservationByID(id);
					
					//Write to File
					FileHandling.exportToFile(RMS_Registry.getInstance());
					
					
					updateModel();
				}
				
			}
		});
		panel.add(buttonDelete);
		
		buttonMoreDetails = new JButton("Notes");
		buttonMoreDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int row = reservTable.getSelectedRow();
				if(reservTableModel.getRowCount()!=0 && row != -1) {
					int id = (int) reservTableModel.getValueAt(row, 0);
					
					RoomReservation aRes = RMS_Registry.getInstance().getReservationList().getReservationByID(id);
					
					JOptionPane.showMessageDialog(null, aRes.getNotes(), "Notes for reservation #"+aRes.getId(),JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		panel.add(buttonMoreDetails);
		
		buttonSearch = new JButton("Search By Name");
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String searchName = JOptionPane.showInputDialog(null, "Enter name to search");
				
				updateModelSearch(searchName);
				
				
			}
		});
		panel.add(buttonSearch);
		
		buttonShowAll = new JButton("Show All Reservations");
		buttonShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateModelAll();
				
			}
		});
		panel.add(buttonShowAll);
		setLayout(groupLayout);
		
		
		resizeColumns();
	}
	
	private void resizeColumns() {
		for (int column = 0; column < reservTable.getColumnCount(); column++)
		{
		    TableColumn tableColumn = reservTable.getColumnModel().getColumn(column);
		    int preferredWidth = tableColumn.getMinWidth();
		    int maxWidth = tableColumn.getMaxWidth();
		 
		    for (int row = 0; row < reservTable.getRowCount(); row++)
		    {
		        TableCellRenderer cellRenderer = reservTable.getCellRenderer(row, column);
		        Component c = reservTable.prepareRenderer(cellRenderer, row, column);
		        int width = c.getPreferredSize().width + reservTable.getIntercellSpacing().width;
		        preferredWidth = Math.max(preferredWidth, width);
		 
		        if (preferredWidth >= maxWidth)
		        {
		            preferredWidth = maxWidth;
		            break;
		        }
		    }
		 
		    tableColumn.setPreferredWidth( preferredWidth );
		}
	}
	
	public void updateModel() {
		RMS_Registry aReg = RMS_Registry.getInstance();
		
		reservTableModel.setRowCount(0);
		
		for (RoomReservation reservation : aReg.getReservationList().getReservations()) {
			
			if(reservation.getCheckOutDate().equals(LocalDate.now()) || reservation.getCheckOutDate().isAfter(LocalDate.now()) ) {
				
				reservTableModel.addRow(new Object[]{reservation.getId(),
						reservation.getCheckInDate().toString(),
						reservation.getCheckOutDate().toString(),
						reservation.getCustomer().toString(),
						reservation.getRoom(),
						reservation.getReservationCost()});
				
				
			}
			
			
			
		}
		reservTableModel.fireTableDataChanged();
	}
	
	public void updateModelSearch(String term) {
		
		reservTableModel.setRowCount(0);
		
		ArrayList<RoomReservation> reservationsByCustomerTerm = RMS_Registry.getInstance().getReservationList().getReservationsByCustomerTerm(term);
		
		if(reservationsByCustomerTerm.size()!=0) {
			
			for (RoomReservation reservation : reservationsByCustomerTerm) {
				
				reservTableModel.addRow(new Object[]{reservation.getId(),
													reservation.getCheckInDate().toString(),
													reservation.getCheckOutDate().toString(),
													reservation.getCustomer().toString(),
													reservation.getRoom(),
													reservation.getReservationCost()});
				
			}
			reservTableModel.fireTableDataChanged();
			
		}
		
		
		
		
		
		
	}
	
	public void updateModelAll() {
		RMS_Registry aReg = RMS_Registry.getInstance();
		
		reservTableModel.setRowCount(0);
		
		for (RoomReservation reservation : aReg.getReservationList().getReservations()) {
			
			reservTableModel.addRow(new Object[]{reservation.getId(),
					reservation.getCheckInDate().toString(),
					reservation.getCheckOutDate().toString(),
					reservation.getCustomer().toString(),
					reservation.getRoom(),
					reservation.getReservationCost()});
			
			
		}
		reservTableModel.fireTableDataChanged();
	}
	
}
