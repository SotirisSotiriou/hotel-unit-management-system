package com.hums.application.event;



import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import com.hums.eventManagementSystem.EMS_Registry;
import com.hums.eventManagementSystem.EventReservation;
import com.hums.tools.data.FileHandling;

public class EventsPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTable eventsTable;
	private JPanel panel;
	private JButton buttonDelete;
	private JButton buttonMoreDetails;
	private JScrollPane scrollPane;
	private static DefaultTableModel eventsTableModel;

	/**
	 * Create the panel.
	 */
	public EventsPanel() {
		
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
		
		eventsTable = new JTable();
		
		eventsTableModel = new DefaultTableModel( null, new String[] {
				"ID", "Start Time", "End Time", "Host", "Hall", "Event Type", "Condition", "Capacity"
		}) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		eventsTable.setModel(eventsTableModel);
		
		eventsTable.getColumnModel().getColumn(1).setPreferredWidth(49);
		scrollPane.setViewportView(eventsTable);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		buttonDelete = new JButton("Delete Event");
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = eventsTable.getSelectedRow();
				if(eventsTableModel.getRowCount()!=0 && row != -1) {
					int id = (int) eventsTableModel.getValueAt(row, 0);
					
					EMS_Registry.getInstance().getEventReservationList().deleteReservationByID(id);
					//Write to file
					FileHandling.exportToFile(EMS_Registry.getInstance());
					updateModel();
				}
				
				
				
			}
		});
		panel.add(buttonDelete);
		
		buttonMoreDetails = new JButton("More details");
		buttonMoreDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int row = eventsTable.getSelectedRow();
				if(eventsTableModel.getRowCount()!=0 && row != -1) {
					int id = (int) eventsTableModel.getValueAt(row, 0);
					
					String notes = EMS_Registry.getInstance().getEventReservationList().getReservationById(id).getInfo().getNotes();
					
					if(notes.length()>0) {
						JOptionPane.showMessageDialog(null, notes,"Notes for reservation #"+id,JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
			}
		});
		panel.add(buttonMoreDetails);
		setLayout(groupLayout);
		
		
		resizeColumns();
	}
	
	private void resizeColumns() {
		for (int column = 0; column < eventsTable.getColumnCount(); column++)
		{
		    TableColumn tableColumn = eventsTable.getColumnModel().getColumn(column);
		    int preferredWidth = tableColumn.getMinWidth();
		    int maxWidth = tableColumn.getMaxWidth();
		 
		    for (int row = 0; row < eventsTable.getRowCount(); row++)
		    {
		        TableCellRenderer cellRenderer = eventsTable.getCellRenderer(row, column);
		        Component c = eventsTable.prepareRenderer(cellRenderer, row, column);
		        int width = c.getPreferredSize().width + eventsTable.getIntercellSpacing().width;
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
	
	public static void updateModel() {
		
		eventsTableModel.setRowCount(0);
		
		for (EventReservation event: EMS_Registry.getInstance().getEventReservationList().getReservations()) {
			
			eventsTableModel.addRow(new Object[] {event.getId(),
													event.getStartTime(), 
													event.getEndTime(),
													event.getInfo(),
													event.getHall(),
													event.getEventType(),
													event.getCondition(),
													event.getInfo().getCapacity()});
			
		}
		
		eventsTableModel.fireTableDataChanged();
		
		
	}
	
	
}
