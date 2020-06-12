package com.hums.restaurantManagementSystem;

import java.io.Serializable;

public class Ingredient implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private double quantity;
	private double max_capacity;
	private double min_capacity;
	

	public Ingredient(String name, double min_capacity, double max_capacity)
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

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id + ". " + name;
	}
	
	
	

}
