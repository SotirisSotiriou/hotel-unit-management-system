package com.hums.roomManagementSystem;

import java.io.Serializable;

public abstract class Room implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected int roomNumber;
	private int floor;
	private int beds;

	private int costPerDay;
	
	

	public Room(int number, int floor, int beds) {
		super();
		this.roomNumber = number;
		this.floor = floor;
		this.beds = beds;
		
	}
	
	
	//Getters and Setters
	
	public String getType() {
		return null;
	}
	

	public int getCostPerDay() {
		return costPerDay;
	}
	
	public void setCostPerDay(int costPerDay) {
		this.costPerDay = costPerDay;
	}


	public int getRoomNumber() {
		return roomNumber;
	}


	public int getFloor() {
		return floor;
	}


	public int getBeds() {
		return beds;
	}
	
	
	public void setBeds(int beds) {
		this.beds = beds;
	}


	public String toString() {
		
		return null;
		
	}

	


	


	


	


	


	
		
		
}
	