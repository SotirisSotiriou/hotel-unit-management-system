package com.hums.application.room;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.hums.application.login.LoginFrame;
import com.hums.roomManagementSystem.Customer;
import com.hums.roomManagementSystem.RMS_Registry;
import com.hums.roomManagementSystem.Room;
import com.hums.tools.data.FileHandling;



public class RMS_Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static CardLayout cl_cards;
	private JPanel panel;
	private static JPanel cards;
	private JButton buttonReservations;
	private JButton buttonNewReservation;
	private JButton buttonNewCustomer;
	private JButton buttonCustomers;
	private JButton buttonNewRoom;
	private JButton buttonRooms;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenuItem menuItemLogout;
	private static ReservationsPanel reservationsPanel;
	private static NewReservationPanel newResPanel;
	private static CustomersPanel customersPanel;
	private static NewCustomerPanel newCustomerPanel;
	private static RoomsPanel roomsPanel;
	private NewRoomPanel newRoomPanel;
	private static PricesPanel pricesPanel;
	private static RMS_Registry rMS_Registry;
	private JButton buttonPrices;
	private static EditRoomPanel editRoomPanel;
	private static EditCustomerPanel editCustomerPanel;

	

	/**
	 * Create the frame.
	 */
	public RMS_Frame() {
		
		
		
		RMS_Registry reg = (RMS_Registry) FileHandling.importFromFile("rms-registry.ser");
		RMS_Registry.setInstance(reg);
		
		
		
		rMS_Registry = RMS_Registry.getInstance();
		
		
		setTitle("Hotel Unit Management System: Room Reservation Management System");
		setMinimumSize(new Dimension(1066, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 543);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		menuItemLogout = new JMenuItem("Logout");
		menuItemLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new LoginFrame();
				dispose();
				
			}
		});
		
		menuFile.add(menuItemLogout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		cards = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cards, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(cards, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(0))
		);
		cards.setLayout(new CardLayout(0, 0));
		
		
		cl_cards = (CardLayout)(cards.getLayout());
		
		reservationsPanel = new ReservationsPanel();
		newResPanel = new NewReservationPanel();
		customersPanel = new CustomersPanel();
		newCustomerPanel = new NewCustomerPanel();
		roomsPanel = new RoomsPanel();
		newRoomPanel = new NewRoomPanel();
		pricesPanel = new PricesPanel();
		editCustomerPanel = new EditCustomerPanel();
		editRoomPanel = new EditRoomPanel();
		
		
		
		cards.add(reservationsPanel,"reservations");
		cards.add(newResPanel,"newRes");
		cards.add(customersPanel,"customers");
		cards.add(newCustomerPanel,"newCustomer");
		cards.add(roomsPanel,"rooms");
		cards.add(newRoomPanel,"newRoom");
		cards.add(pricesPanel,"prices");
		cards.add(editCustomerPanel,"editCustomer");
		cards.add(editRoomPanel,"editRoom");
		
		
		buttonReservations = new JButton("Reservations");
		buttonReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				showCard("reservations");
				
			}
		});
		
		buttonCustomers = new JButton("Customers");
		buttonCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				showCard("customers");
				
			}
		});

		
		buttonRooms = new JButton("Rooms");
		buttonRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				showCard("rooms");
				
			}
		});
		
		buttonNewReservation = new JButton("New Reservation");
		buttonNewReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				showCard("newRes");
				
			}
		});
		
		buttonNewCustomer = new JButton("New Customer");
		buttonNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				showCard("newCustomer");
				
			}
		});
		
		buttonNewRoom = new JButton("New Room");
		buttonNewRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				showCard("newRoom");
				
			}
		});
		
		buttonPrices = new JButton("Prices");
		buttonPrices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				showCard("prices");
				
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonReservations)
						.addComponent(buttonNewReservation)
						.addComponent(buttonNewCustomer)
						.addComponent(buttonCustomers)
						.addComponent(buttonNewRoom)
						.addComponent(buttonRooms)
						.addComponent(buttonPrices))
					.addContainerGap(48, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(26)
					.addComponent(buttonNewReservation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonReservations)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonNewCustomer)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonCustomers)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonNewRoom)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonRooms)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonPrices)
					.addContainerGap(305, Short.MAX_VALUE))
		);
		
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		this.setVisible(true);
		
	}

	public synchronized static RMS_Registry getReceptionistRegistry() {
		return rMS_Registry;
	}

	public static void setReceptionistRegistry(RMS_Registry rMS_Registry) {
		RMS_Frame.rMS_Registry = rMS_Registry;
	}
	
	public static void showCard(String cardName) {
		
		if(cardName.equals("newRes")) {
			
			//if you want to open 'New Reservation Panel', first check if there are customers in registry
			//if there is no customer, disable the submit button
			//if there are customers, update customers combobox and fill the list of available rooms
			
			if(rMS_Registry.getCustomerList().getCustomers().size()==0) {
				
				newResPanel.setSubmitEnabled(false);
				
			}else {
				
				newResPanel.setSubmitEnabled(true);
				
			}
			newResPanel.updateCustomersModel();
			newResPanel.updateAvailableRoomsList();
			
			//open the card
			cl_cards.show(cards, "newRes");
			
			
			
		}
		else if(cardName.equals("reservations")) {
			
			//update reservations table and show card
			reservationsPanel.updateModel();
			cl_cards.show(cards, "reservations");
			
			
		}
		
		else if(cardName.equals("newCustomer")){
			
			newCustomerPanel.clearFields();
			cl_cards.show(cards, "newCustomer");
			
		}
		
		else if(cardName.equals("customers")) {
			
			//update customers table and show card
			customersPanel.updateModel();
			cl_cards.show(cards, "customers");
			
		}
		
		else if(cardName.equals("newRoom")) {
			
			cl_cards.show(cards, "newRoom");
			
		}
		
		else if(cardName.equals("rooms")) {
			
			//update rooms table and show card
			roomsPanel.updateModel();
			cl_cards.show(cards, "rooms");
			
		}
		
		else if(cardName.equals("prices")) {
			
			pricesPanel.updatePrices();
			cl_cards.show(cards, "prices");
			
		}
		
		
	}
	
	public static void showEditCustomerCard(Customer customerToEdit) {
		
		editCustomerPanel.setCustomerToEdit(customerToEdit);
		cl_cards.show(cards, "editCustomer");
		
		
	}
	
	public static void showEditRoomCard(Room roomToEdit) {
		
		editRoomPanel.setRoomToEdit(roomToEdit);
		cl_cards.show(cards, "editRoom");
		
		
	}
	
	
}
