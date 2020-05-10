package com.hums.roomManagementSystem;

import java.util.ArrayList;

public class RoomRegular extends Room {
	private static double costPerBed = 50;
	
	public RoomRegular(int number, int floor, int capacity, ArrayList<RoomFeature> features) {
		super(number, floor, capacity, features);
		this.setCostPerDay(this.calculateCostPerDay());
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
