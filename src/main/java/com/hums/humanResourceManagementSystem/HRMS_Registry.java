package com.hums.humanResourceManagementSystem;

import java.io.Serializable;

public class HRMS_Registry implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	private static HRMS_Registry registryInstance = null;

	
	private HRMS_Registry() {
		this.empList = new EmployeeList();
	}
	
	public synchronized static HRMS_Registry getInstance() {
			
			if(registryInstance == null) {
				registryInstance = new HRMS_Registry();
			}
			
			return registryInstance;
			
	}
	
	public static void setInstance(HRMS_Registry reg) {
		registryInstance = reg;
	}
		
	public static void resetInstance() {
		registryInstance = new HRMS_Registry();
	}
	
	private EmployeeList empList;
	

	public EmployeeList getEmpList() {
		return empList;
	}

	public void setEmpList(EmployeeList empList) {
		this.empList = empList;
	}
	
	
}
