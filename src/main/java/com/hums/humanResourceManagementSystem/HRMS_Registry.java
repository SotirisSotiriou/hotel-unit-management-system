package com.hums.humanResourceManagementSystem;

import java.io.Serializable;
import java.util.ArrayList;

import com.hums.eventManagementSystem.EMS_Registry;

public class HRMS_Registry implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static HRMS_Registry registryInstance = null;
	
	private HRMS_Registry() {
		
	}
	
	public static HRMS_Registry getInstance() {
			
			if(registryInstance == null) {
				registryInstance = new HRMS_Registry();
			}
			
			return registryInstance;
			
	}
	
	private EmployeeList empList;
	

	public EmployeeList getEmpList() {
		return empList;
	}

	public void setEmpList(EmployeeList empList) {
		this.empList = empList;
	}
	
	
}
