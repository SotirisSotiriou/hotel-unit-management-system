package com.hums.restaurantManagementSystem;

import java.io.Serializable;

public class IngredientQuantityPair implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ingredient ingredient;
	private Double quantity;
	
	
	public IngredientQuantityPair(Ingredient ingredient, Double quantity) {
		super();
		this.ingredient = ingredient;
		this.quantity = quantity;
	}


	public Ingredient getIngredient() {
		return ingredient;
	}


	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}


	public Double getQuantity() {
		return quantity;
	}


	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
}
