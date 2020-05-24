package com.hums.restaurantManagementSystem;

import java.util.ArrayList;

public class Storage {

	private ArrayList<Ingredient> ingredients = new ArrayList();
	
	public Storage(ArrayList<Ingredient> ingredients)
	{
		this.ingredients = ingredients;
	}
	
	public void setIngredients(ArrayList<Ingredient> ingredients)
	{
		this.ingredients = ingredients;
	}
	
	public void addIngredient(Ingredient ingredient)
	{
		ingredients.add(ingredient);
		//write to file//
	}
	
	public ArrayList<Ingredient> getIngredients()
	{
		return ingredients;
	}
	
	public double getIngredientQuantity(Ingredient ingredient)
	{
		return ingredient.getCapacity();
	}
	
	public void setIngredientQuantity(Ingredient ingredient, double quantity) 
	{
		ingredient.setCapacity(ingredient.getCapacity() - quantity);
	}
	
}
