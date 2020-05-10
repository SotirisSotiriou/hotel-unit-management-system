package com.hums.roomManagementSystem;

import java.util.ArrayList;

public class RoomVIP extends Room {
	private static double costPerBed = 100;
	
	public RoomVIP(int number, int floor, int capacity, ArrayList<RoomFeature> features) {
		super(number, floor, capacity, features);
	}

	@Override
	public double calculateCostPerDay() {
		return costPerBed * this.getCapacity();
	}
	
	public static double getCostPerBed() {
		return costPerBed;
	}
	
	public static void setCostPerBed(double cost) {
		costPerBed = cost;
	}

}
