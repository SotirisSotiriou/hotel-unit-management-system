package com.hums.application.event;



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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.hums.application.login.LoginFrame;
import com.hums.eventManagementSystem.EMS_Registry;
import com.hums.eventManagementSystem.Hall;
import com.hums.tools.data.FileHandling;

public class EMS_Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel cards;
	private CardLayout cl_cards;
	private JMenu menuFile;
	private JMenuItem menuItemLogout;



	/**
	 * Create the frame.
	 */
	public EMS_Frame() {
		
		EMS_Registry reg = (EMS_Registry) FileHandling.importFromFile("ems-registry.ser");
		EMS_Registry.setInstance(reg);
		
		setMinimumSize(new Dimension(1066, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 473);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		menuItemLogout = new JMenuItem("Logout");
		menuItemLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				new LoginFrame();
				dispose();
				
			}
		});
		menuFile.add(menuItemLogout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		cards = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cards, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(cards, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
					.addGap(3))
		);
		cards.setLayout(new CardLayout(0, 0));
		
		cl_cards = (CardLayout)(cards.getLayout());
		
		EventsPanel eventsPanel = new EventsPanel();
		HallsPanel hallsPanel = new HallsPanel();
		NewEventPanel newEventPanel = new NewEventPanel();
		
		EventsPanel.updateModel();
		
		
		cards.add(eventsPanel, "events");
		cards.add(hallsPanel, "halls");
		cards.add(newEventPanel,"new_event");
		
		
		
		
		JButton buttonNewEvent = new JButton("New Event");
		buttonNewEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				newEventPanel.resetFields();
				cl_cards.show(cards, "new_event");
				
			}
		});
		
		JButton buttonEvents = new JButton("Events");
		buttonEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				EventsPanel.updateModel();
				cl_cards.show(cards, "events");
				
			}
		});
		
		JButton buttonNewHall = new JButton("New Hall");
		buttonNewHall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String location;
				int capacity = 0;
				String newHallCapacity;
				
				location = JOptionPane.showInputDialog("Enter hall location");
				
				if(location==null) {
					location = "9999999999999999999999";
				}
				
				if(!location.equals("9999999999999999999999") && !location.equals("")) {
					JOptionPane.showMessageDialog(null, "Location added, add capacity");
					
					
					do {
						
						newHallCapacity = JOptionPane.showInputDialog("Enter Hall capacity");
						if(newHallCapacity==null)
							newHallCapacity = "";
						if(newHallCapacity.matches("\\d+")) {
							capacity = Integer.parseInt(newHallCapacity);
							if(capacity!=0) {
								JOptionPane.showMessageDialog(null, "Hall created successfully");
								//Write to file
								FileHandling.exportToFile(EMS_Registry.getInstance());
							}
							else {
								JOptionPane.showMessageDialog(null, "Capacity Cannot be 0", "Error", JOptionPane.ERROR_MESSAGE);;
							}
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Capacity Input Error", "Error", JOptionPane.ERROR_MESSAGE);;
						}
						
					}while (!newHallCapacity.matches("\\d+") && !newHallCapacity.equals(""));
					
					
				}
				else if (location.equals("")){
					JOptionPane.showMessageDialog(null, "Location cannot be empty");
				}
				
				if( !location.equals("9999999999999999999999") && capacity!=0 ) {
					Hall aHall = new Hall(capacity,location);
					reg.getHallList().addHall(aHall);
					
					
				}
				
				

			}
		});
		
		JButton buttonHalls = new JButton("Halls");
		buttonHalls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				hallsPanel.updateModel();
				cl_cards.show(cards, "halls");
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonNewEvent)
						.addComponent(buttonEvents)
						.addComponent(buttonNewHall)
						.addComponent(buttonHalls))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(buttonNewEvent)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonEvents)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonNewHall)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonHalls)
					.addContainerGap(271, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		
		this.setVisible(true);
		
	}
	
	
}
