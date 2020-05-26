package com.hums.restaurantManagementSystem;

public class Ingredient {
	
	private String name;
	private double max_capacity;
	private double min_capacity;
	

	public Ingredient(String name, double max_capacity, double min_capacity)
	{
		this.name = name;
		this.max_capacity = max_capacity;
		this.min_capacity = min_capacity;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMax_capacity() {
		return max_capacity;
	}
	public void setMax_capacity(double max_capacity) {
		this.max_capacity = max_capacity;
	}
	public double getMin_capacity() {
		return min_capacity;
	}
	public void setMin_capacity(double min_capacity) {
		this.min_capacity = min_capacity;
	}
	

}
