package com.hums.roomManagementSystem;

import java.util.ArrayList;

public abstract class Room {
	private int number;
	private int floor;
	private int capacity;
	private double costPerDay;
	private ArrayList<RoomFeature> features;
	
	public Room(int number, int floor, int capacity, ArrayList<RoomFeature> features) {
		this.number = number;
		this.floor = floor;
		this.capacity = capacity;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getCostPerDay() {
		return costPerDay;
	}

	public void setCostPerDay(double costPerDay) {
		this.costPerDay = costPerDay;
	}
	
	public ArrayList<RoomFeature> getFeatures() {
		return features;
	}

	public void setFeatures(ArrayList<RoomFeature> features) {
		this.features = features;
	}

	public abstract double calculateCostPerDay();
}