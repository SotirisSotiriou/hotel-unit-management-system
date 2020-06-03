package com.hums.humanResourceManagementSystem;

import com.hums.tools.Pair;
import java.util.ArrayList;
import java.time.LocalTime;

public class EmployeeList {
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
			for(Pair<LocalTime, LocalTime> hoursPair : ds.getHours()) {
				LocalTime newStartTime = hoursPair.getElement1().plusHours(8);
				LocalTime newEndTime = hoursPair.getElement2().plusHours(8);
				hoursPair.setElement1(newStartTime);
				hoursPair.setElement2(newEndTime);
			}
		}
	}
	
	
	public void moveSchedule() {
		for(Employee emp : this.employees) {
			this.moveEmpSchedule(emp);
		}
	}
}
