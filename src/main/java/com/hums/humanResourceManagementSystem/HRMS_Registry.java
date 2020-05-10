package com.hums.humanResourceManagementSystem;

import java.util.ArrayList;

public class HRMS_Registry {
	private EmployeeList empList;
	
	public HRMS_Registry(EmployeeList empList) {
		this.empList = empList;
	}
	
	public HRMS_Registry(ArrayList<Employee> employees) {
		this.empList = new EmployeeList(employees);
	}
	
	public HRMS_Registry() {
		this.empList = new EmployeeList();
	}

	public EmployeeList getEmpList() {
		return empList;
	}

	public void setEmpList(EmployeeList empList) {
		this.empList = empList;
	}
	
	
}
