package com.hums.humanResourceManagementSystem;

import java.io.Serializable;
import java.util.ArrayList;

public class EmployeeList implements Serializable{

	private int nextID;
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Employee> employees;
	
	public EmployeeList(ArrayList<Employee> employees) {
		this.employees = employees;
	}
	
	public EmployeeList() {
		this.employees = new ArrayList<>();
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}
	
	
	public void addEmployee(Employee employee) {
		
		nextID++;
		employee.setId(nextID);

		this.employees.add(employee);
		
	}
	
	
	public void removeEmployee(Employee employee) {
		this.employees.remove(employee);
	}
	
	
	public Employee searchEmployee(String firstname, String lastname) {
		Employee foundemp = null;
		
		for(Employee emp : this.employees) {
			if(emp.getFirstname().equals(firstname) && emp.getLastname().equals(lastname)) {
				foundemp = emp;
				break;
			}
		}
		
		return foundemp;
	}
	
	
	public void changeEmpName(Employee employee, String firstname, String lastname) {
		employee.setFirstname(firstname);
		employee.setLastname(lastname);
	}
	
	
	public void changeSalary(Employee employee, double price) {
		this.searchEmployee(employee.getFirstname(), employee.getLastname()).getSalary().setPrice(price);
	}
	
	
	public void moveEmpSchedule(Employee employee) {
		for(DailySchedule ds : employee.getSchedule().getWeekSchedule()) {
			ds.setStart(ds.getStart().plusHours(8));
			ds.setEnd(ds.getEnd().plusHours(8));
		}
	}
	
	
	public void moveSchedule() {
		for(Employee emp : this.employees) {
			this.moveEmpSchedule(emp);
		}
	}
}
