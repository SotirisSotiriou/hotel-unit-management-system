package com.hums.application.event;



import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.hums.eventManagementSystem.EMS_Registry;
import com.hums.eventManagementSystem.Hall;
import com.hums.tools.data.FileHandling;



public class HallsPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTable hallsTable;
	private DefaultTableModel hallsTableModel;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton buttonShowReserved;
	private JButton buttonEditHall;
	private JButton buttonDeleteHall;

	/**
	 * Create the panel.
	 */
	public HallsPanel() {
		
		scrollPane = new JScrollPane();
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		buttonShowReserved = new JButton("Show Reserved");
		buttonShowReserved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				updateModelReserved();
				
				
			}
		});
		panel.add(buttonShowReserved);
		
		buttonEditHall = new JButton("Edit Hall");
		buttonEditHall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int row = hallsTable.getSelectedRow();
				
				if(hallsTableModel.getRowCount()!=0 && row != -1) {
					
					int code = (int) hallsTable.getValueAt(row, 0);
					
					Hall selectedHall = EMS_Registry.getInstance().getHallList().getHallByCode(code);
					
					String newCapacityAsString = JOptionPane.showInputDialog("Enter new hall capacity");
					
					if(newCapacityAsString.matches("\\d+")) {
						int newCapacity = Integer.parseInt(newCapacityAsString);
						selectedHall.setCapacity(newCapacity);
						//Write to file
						FileHandling.exportToFile(EMS_Registry.getInstance());
						updateModel();
					}else {
						JOptionPane.showMessageDialog(null, "Capacity input error","InputError",JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
				
				
			}
		});
		panel.add(buttonEditHall);
		
		buttonDeleteHall = new JButton("Delete Hall");
		buttonDeleteHall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				int row = hallsTable.getSelectedRow();
				if(hallsTableModel.getRowCount()!=0 && row != -1) {
					int code = (int) hallsTableModel.getValueAt(row, 0);
					
					EMS_Registry.getInstance().getHallList().deleteHallByCode(code);
					
					updateModel();
				}
				
				
			}
		});
		panel.add(buttonDeleteHall);
		
		hallsTable = new JTable();
		
		hallsTableModel = new DefaultTableModel( null, new String[] {
				"Hall Code", "Capacity", "Location"
		}) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		hallsTable.setModel(hallsTableModel);
		
		scrollPane.setViewportView(hallsTable);
		setLayout(groupLayout);

	}
	
	public void updateModel() {
		
		ArrayList<Hall> halls = EMS_Registry.getInstance().getHallList().getHalls();
		
		hallsTableModel.setRowCount(0);
		
		for (Hall hall : halls) {
			
			hallsTableModel.addRow(new Object[] {hall.getCode(),hall.getCapacity(),hall.getLocation()});
			
		}
		
		hallsTableModel.fireTableDataChanged();
		
	}
	
	public void updateModelReserved() {
		
		ArrayList<Hall> halls = EMS_Registry.getInstance().getHallList().getReservedHalls();
		
		hallsTableModel.setRowCount(0);
		
		for (Hall hall : halls) {
			
			hallsTableModel.addRow(new Object[] {hall.getCode(),hall.getCapacity(),hall.getLocation()});
			
		}
		
		hallsTableModel.fireTableDataChanged();
	}

}

