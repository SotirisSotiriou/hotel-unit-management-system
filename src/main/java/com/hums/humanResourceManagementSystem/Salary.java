package com.hums.humanResourceManagementSystem;

import java.io.Serializable;

public class Salary implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private double price;
	public static final double DEFAULT_RECEPTIONIST_SALARY = 2000.00;
	public static final double DEFAULT_EVENT_MANAGER_SALARY = 4500.00;
	public static final double DEFAULT_RESTAURANT_MANAGER_SALARY = 4000.00;
	public static final double DEFAULT_HUMAN_RESOURCE_MANAGER_SALARY = 3000.00;
	
	public Salary(EmpType type) {
		switch(type) {
		case RECEPTIONIST:
			this.price = Salary.DEFAULT_RECEPTIONIST_SALARY;
			break;
		case EVENT_MANAGER:
			this.price = Salary.DEFAULT_EVENT_MANAGER_SALARY;
			break;
		case RESTAURANT_MANAGER:
			this.price = Salary.DEFAULT_RESTAURANT_MANAGER_SALARY;
			break;
		case HUMAN_RESOURCE_MANAGER:
			this.price = Salary.DEFAULT_HUMAN_RESOURCE_MANAGER_SALARY;
			break;
		default:
			System.out.println("The type does not exist");
			break;
		}
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
