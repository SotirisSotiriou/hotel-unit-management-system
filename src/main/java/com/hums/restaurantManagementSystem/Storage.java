package com.hums.restaurantManagementSystem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Storage {
	
	public HashMap<Ingredient, Double> ingredients = new HashMap<>();
	
	public Storage(HashMap<Ingredient, Double> ingredients)
	{
		this.ingredients = ingredients;
	}
	
	public void setIngredients(HashMap<Ingredient, Double> ingredients)
	{
		this.ingredients = ingredients;
	}
	
	public void addIngredient(Ingredient ingredient)
	{
		ingredients.put(ingredient, 0.0);
		//write to file//
	}
	
	public void addIngredient(Ingredient ingredient, double quantity) {
		ingredients.put(ingredient, quantity);
	}
	
	public HashMap<Ingredient, Double> getIngredients()
	{
		return ingredients;
	}
	
	public Double getIngredientQuantity(Ingredient ingredient)
	{
		return ingredients.get(ingredient);
	}
	
	public void setIngredientQuantity(Ingredient ingredient, Double quantity) 
	{
		ingredients.replace(ingredient, ingredients.get(ingredient), quantity);
	}
	
	public void createStorageOrder(HashMap<Ingredient, Double> ingredients) 
	{
		 Iterator<Entry<Ingredient, Double>> it = ingredients.entrySet().iterator();
		 while (it.hasNext()) {
				Map.Entry<Ingredient, Double> pair = (Map.Entry<Ingredient, Double>)it.next();
		        setIngredientQuantity(pair.getKey(), this.ingredients.get(pair.getKey()) - pair.getValue());
		 }
	}
}
