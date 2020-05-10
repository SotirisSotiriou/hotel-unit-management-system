package com.hums.roomManagementSystem;

import java.util.ArrayList;

public class RoomPenthouse extends Room {
	private static double costPerBed = 70;
	
	public RoomPenthouse(int number, int floor, int capacity, ArrayList<RoomFeature> features) {
		super(number, floor, capacity, features);
		this.setCostPerDay(this.calculateCostPerDay());
	}

	@Override
	public double calculateCostPerDay() {
		return costPerBed * this.getCapacity();
	}
	
	public double getCostPerBed() {
		return costPerBed;
	}
	
	public void setCostPerBed(double cost) {
		costPerBed = cost;
	}
}
