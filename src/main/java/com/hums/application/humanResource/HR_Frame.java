package com.hums.application.humanResource;

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
import com.hums.humanResourceManagementSystem.Employee;
import com.hums.humanResourceManagementSystem.HRMS_Registry;
import com.hums.tools.data.FileHandling;

public class HR_Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JButton buttonEmployees;
	private JButton buttonSchedule;
	private JButton buttonSalaries;
	private static JPanel cards;
	private JPanel panel;
	private static CardLayout cl_cards;
	private NewEmployeePanel newEmployeePanel;
	private EmployeesPanel employeesPanel;
	private static EditEmployeePanel editEmployeePanel;
	private SalaryPanel salariesPanel;
	private SchedulesPanel schedulesPanel;
	private EmpSchedulePanel empSchedulePanel;
	private AddSchedulePanel addSchedulePanel;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenuItem menuItemLogout;
	

	public HR_Frame() {
		HRMS_Registry reg = (HRMS_Registry) FileHandling.importFromFile("hrms-registry.ser");
		HRMS_Registry.setInstance(reg);
		
		setMinimumSize(new Dimension(1066, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 426);
		
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
		setVisible(true);
		
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
		salariesPanel = new SalaryPanel();
		schedulesPanel = new SchedulesPanel();
		empSchedulePanel = new EmpSchedulePanel();
		addSchedulePanel = new AddSchedulePanel();
		
		
		
		cards.add(newEmployeePanel,"newEmp");
		cards.add(employeesPanel,"employees");
		cards.add(editEmployeePanel,"editEmp");
		cards.add(salariesPanel, "salaries");
		cards.add(schedulesPanel, "schedules");
		cards.add(empSchedulePanel, "empSchedule");
		cards.add(addSchedulePanel, "addSchedule");
		
		cl_cards.show(cards, "employees");
		
		buttonEmployees = new JButton("Employees");
		buttonEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeesPanel.updateModel();
				cl_cards.show(cards, "employees");
				
			}
		});
		
		buttonSchedule = new JButton("Schedules");
		buttonSchedule.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SchedulesPanel.updateModel();
				cl_cards.show(cards, "schedules");
			}
			
		});
		
		buttonSalaries = new JButton("Salaries");
		buttonSalaries.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SalaryPanel.updateModel();
				cl_cards.show(cards, "salaries");
			}
			
		});
		
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
	
	public static void showEditEmployeePanel(Employee emp) {
		editEmployeePanel.setPanelEmployee(emp);
		cl_cards.show(cards, "editEmp");
	}
	
	public static void showEmpSchedulePanel(Employee emp) {
		EmpSchedulePanel.setEmployeeToView(emp);
		EmpSchedulePanel.updateModel();
		cl_cards.show(cards, "empSchedule");
	}
	
	public static void showAddSchedulePanel(Employee emp) {
		AddSchedulePanel.setEmployee(emp);
		cl_cards.show(cards, "addSchedule");
	}
	
	public static void showSchedulesPanel() {
		cl_cards.show(cards, "schedules");
		SchedulesPanel.updateModel();
	}
	
}
