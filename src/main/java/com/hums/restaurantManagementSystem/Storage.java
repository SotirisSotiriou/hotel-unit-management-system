package com.hums.restaurantManagementSystem;

import java.io.Serializable;
import java.util.ArrayList;

public class Storage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	private int nextID=0;
	
	public Storage()
	{
		this.ingredients = new ArrayList<Ingredient>();
	}
	
	public void addIngredient(Ingredient ingredient)
	{
		nextID++;
		ingredient.setId(nextID);
		ingredients.add(ingredient);
		//write to file//
	}
	
	public void addIngredient(Ingredient ingredient, double quantity) {
		ingredients.add(ingredient);
		ingredient.setQuantity(quantity);
	}
	
	public ArrayList<Ingredient> getIngredients()
	{
		return ingredients;
	}
	
	public Ingredient getIngredientByID(int id)
	{
		for (Ingredient ingredient : ingredients) {
			if(ingredient.getId()==id)
				return ingredient;
		}
		return null;
	}
	
	public Ingredient getIngredientByToString(String string)
	{
		for (Ingredient ingredient : ingredients) {
			if(ingredient.toString().equals(string))
				return ingredient;
		}
		return null;
	}
	
	public void setIngredientQuantity(int id, Double quantity) 
	{
		for (Ingredient ingredient : ingredients) {
			if(ingredient.getId()==id)
				ingredient.setQuantity(quantity);
		}
	}
	
	public void deleteIngredientByID(int id) {
		
		for (int i = 0; i < ingredients.size(); i++) {
			
			if(ingredients.get(i).getId()==id)
				ingredients.remove(i);
			
		}
		
		
		
	}
	
}
