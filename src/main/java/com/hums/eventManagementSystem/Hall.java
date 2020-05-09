package com.hums.eventManagementSystem;

public class Hall implements Comparable<Hall>{

	private int capacity;
	private int code;
	
	
	public Hall(int capacity,int code)
	{
		this.capacity=capacity;
		this.code=code;
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
		
		if(this.getCode()==h.getCode())
		return 0;
		else if(this.getCode()>h.getCode())
			return 1;
		else
			return -1;
	}



	
	
}
