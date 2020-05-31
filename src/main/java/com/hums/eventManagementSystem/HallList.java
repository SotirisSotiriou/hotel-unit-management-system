package com.hums.eventManagementSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;


public class HallList implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	ArrayList<Hall> halls=new ArrayList<Hall>();
	int nextCode=0;
	
	public ArrayList<Hall> getHalls() {
		return halls;
	}
	
	
	//Method for adding hall.
	public void addHall(Hall hall)
	{
		
		Collections.sort(halls);
		nextCode++;
		hall.setCode(nextCode);
		halls.add(hall);

	}
	
	
	
	
}
