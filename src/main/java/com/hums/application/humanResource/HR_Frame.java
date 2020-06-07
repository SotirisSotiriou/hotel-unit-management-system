package com.hums.application.humanResource;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HR_Frame extends JFrame {

	private JPanel contentPane;
	private JButton buttonEmployees;
	private JButton buttonSchedule;
	private static JPanel cards;
	private JPanel panel;
	private static CardLayout cl_cards;
	private NewEmployeePanel newEmployeePanel;
	private EmployeesPanel employeesPanel;
	
	private EditEmployeePanel editEmployeePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HR_Frame frame = new HR_Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HR_Frame() {
		setMinimumSize(new Dimension(1066, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 426);
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
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cards, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
				.addComponent(cards, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
		);
		
		cards.setLayout(new CardLayout(0, 0));
		
		
		cl_cards = (CardLayout)(cards.getLayout());
		
		newEmployeePanel = new NewEmployeePanel();
		employeesPanel = new EmployeesPanel();
		
		editEmployeePanel = new EditEmployeePanel();
		
		
		cards.add(newEmployeePanel,"newEmp");
		cards.add(employeesPanel,"employees");
		
		cards.add(editEmployeePanel,"editEmp");
		
		
		
		
		cl_cards.show(cards, "employees");
		
		
		
		buttonEmployees = new JButton("Employees");
		buttonEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cl_cards.show(cards, "employees");
				
			}
		});
		
		buttonSchedule = new JButton("Schedule");
		
		JButton buttonSalaries = new JButton("Salaries");
		
		JButton buttonNewEmployee = new JButton("New Employee");
		buttonNewEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cl_cards.show(cards, "newEmp");
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonNewEmployee)
						.addComponent(buttonEmployees)
						.addComponent(buttonSchedule)
						.addComponent(buttonSalaries))
					.addContainerGap(66, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(buttonNewEmployee)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonEmployees)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonSchedule)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonSalaries)
					.addContainerGap(256, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	
	public static void showEditEmployeePanel(/* Employee employeeToEdit */) {
		
		cl_cards.show(cards, "editEmp");
		
	}
	
	
}
