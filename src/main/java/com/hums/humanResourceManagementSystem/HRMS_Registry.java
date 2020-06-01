package com.hums.humanResourceManagementSystem;

import java.io.Serializable;
import java.util.ArrayList;

public class HRMS_Registry implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
