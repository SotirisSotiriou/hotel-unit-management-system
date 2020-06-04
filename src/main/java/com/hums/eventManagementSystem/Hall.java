package com.hums.eventManagementSystem;

import java.io.Serializable;


public class Hall implements Comparable<Hall>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int capacity;
	private int code;
	private String location;
	
	
	public Hall(int capacity, String location)
	{
		//code is being assigned by HallList
		this.capacity=capacity;
		this.location=location;
	}
	
	@Override
	public String toString() {
		return ""+code+". "+location+", "+capacity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int compareTo(Hall h) {
		
		if(this.getCapacity()==h.getCapacity())
		return 0;
		else if(this.getCapacity()>h.getCapacity())
			return 1;
		else
			return -1;
	}



	
	
}
