package com.hums.restaurantManagementSystem;

import java.util.HashMap;

public class Dish {
	
	private String name;
	private int menu[];
	public HashMap<Ingredient, Double> ingredients;
	
	public Dish(String Name, HashMap<Ingredient, Double> ingredients)
	{
		//***//
		this.name = name;
		this.ingredients = ingredients;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashMap<Ingredient, Double> getIngredients() {
		return ingredients;
	}
	public void setIngredients(HashMap<Ingredient, Double> ingredients) {
		//read from file//
		this.ingredients = ingredients;
	}
	public int[] getMenu() {
		return menu;
	}
	
	
}
