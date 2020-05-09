package com.hums.humanResourceManagementSystem;

public class HRM_Registry {
	private EmployeeList empList;
	
	public HRM_Registry(EmployeeList empList) {
		this.empList = empList;
	}

	public EmployeeList getEmpList() {
		return empList;
	}

	public void setEmpList(EmployeeList empList) {
		this.empList = empList;
	}
	
	
}
