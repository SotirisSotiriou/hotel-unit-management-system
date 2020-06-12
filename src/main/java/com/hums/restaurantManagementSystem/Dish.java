package com.hums.restaurantManagementSystem;

import java.io.Serializable;
import java.util.ArrayList;

public class Dish implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	
	public enum MealType{
		Breakfast,
		Launch,
		Dinner
	}
	
	private MealType mealType;
	
	private ArrayList<Integer> menu = new ArrayList<Integer>(7);
	
	public ArrayList<IngredientQuantityPair> ingredientsList;
	
	public Dish(String name, ArrayList<IngredientQuantityPair> ingredients, MealType mealType)
	{
		this.name = name;
		this.ingredientsList = ingredients;
		this.mealType = mealType;
		
		for (int i = 0; i < menu.size(); i++) {
			menu.set(i, 0);
		}
		
	}
	
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public MealType getMealType() {
		return mealType;
	}


	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}


	public ArrayList<IngredientQuantityPair> getIngredientsList() {
		return ingredientsList;
	}
	
	
	public void setIngredients(ArrayList<IngredientQuantityPair> ingredients) {
		//read from file//
		this.ingredientsList = ingredients;
	}
	
	public ArrayList<Integer> getMenu() {
		return menu;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void changeMenu(ArrayList<Integer> dayList) {
		
		menu = dayList;
		
	}
	
	public boolean equals(Object obj) {
		//overlap
		boolean flag = false;
		
		if (name.equals(((Dish)obj).name)) {
			flag = true;
		}
		return flag;
	}

	@Override
	public String toString() {
		return id + ". " + name;
	}
	
	
	
	
}
