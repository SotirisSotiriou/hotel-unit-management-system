package com.hums.application.room;

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

import com.hums.humanResourceManagementSystem.HRMS_Registry;
import com.hums.restaurantManagementSystem.REMS_Registry;
import com.hums.roomManagementSystem.Customer;
import com.hums.roomManagementSystem.RMS_Registry;
import com.hums.tools.data.FileHandling;



public class CustomersPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable customersTable;
	private JScrollPane scrollPane;
	private JPanel panel;
	private DefaultTableModel customersTableModel;
	private JButton buttonSearch;
	private JButton buttonDeleteCustomer;

	/**
	 * Create the panel.
	 */
	public CustomersPanel() {
		
		scrollPane = new JScrollPane();
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		buttonSearch = new JButton("Search By Term");
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String searchTerm = JOptionPane.showInputDialog(null, "Enter first/last Name or Phone to search");
				
				ArrayList<Customer> foundCustomers = RMS_Registry.getInstance().getCustomerList().getCustomersByTerm(searchTerm);
				
				if(foundCustomers.size()!=0) {
					
					StringBuilder builder = new StringBuilder(foundCustomers.size());
					
					for (Customer customer : foundCustomers) {
						builder.append(customer.toString()+"\n");
					}
					
					String foundCustomersMessageString = builder.toString();
					JOptionPane.showMessageDialog(null, "Customers that match term:\n"+foundCustomersMessageString);
					
				}else {
					JOptionPane.showMessageDialog(null, "No customers found with this term");
				}
				
				
				
				
				
			}
		});
		panel.add(buttonSearch);
		
		buttonDeleteCustomer = new JButton("Delete Customer");
		buttonDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int row = customersTable.getSelectedRow();
				if(customersTableModel.getRowCount()!=0 && row != -1) {
					int id = (int) customersTableModel.getValueAt(row, 0);
					
					RMS_Registry.getInstance().getCustomerList().removeCustomerByID(id);
					
					//Write to File
					FileHandling.exportToFile(RMS_Registry.getInstance());
					
					updateModel();
				}
				
			}
		});
		
		JButton buttonEditCustomer = new JButton("Edit Customer");
		buttonEditCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int row = customersTable.getSelectedRow();
				if(customersTableModel.getRowCount()!=0 && row != -1) {
					int id = (int) customersTableModel.getValueAt(row, 0);
					
					Customer customerToEdit = RMS_Registry.getInstance().getCustomerList().getCustomerByID(id);
					
					RMS_Frame.showEditCustomerCard(customerToEdit);
					
					
				}
				
				
			}
		});
		panel.add(buttonEditCustomer);
		panel.add(buttonDeleteCustomer);
		
		customersTableModel = new DefaultTableModel(null, new String[] {
			"ID", "Last Name", "First Name", "Phone", "First Reservation"
		}) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		customersTable = new JTable();
		customersTable.setModel(customersTableModel);
		
		scrollPane.setViewportView(customersTable);
		setLayout(groupLayout);

	}
	
	
	public void updateModel() {
		
		RMS_Registry aReg = RMS_Registry.getInstance();
		
		customersTableModel.setRowCount(0);
		
		for (Customer customer : aReg.getCustomerList().getCustomers()) {
			
			customersTableModel.addRow(new Object[]{customer.getId(),customer.getLastName(),customer.getFirstName(),customer.getPhone(), customer.getFirstReservationDate()});
			
		}
		customersTableModel.fireTableDataChanged();
		
	}
	
}
