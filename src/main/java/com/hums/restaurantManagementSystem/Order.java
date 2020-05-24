package com.hums.restaurantManagementSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class Order {
	
	private ArrayList<Dish> order = new ArrayList();
	
	public Order(ArrayList<Dish> order)
	{
		this.order = order;
	}
	
	public void updateStorage(ArrayList<Dish> order, Storage s)
	{
		for(int i=0;i<order.size();i++)
		{
			for(Ingredient k: order.get(i).ingredients.keySet())
			{
				s.setIngredientQuantity(k,order.get(i).ingredients.get(k));
			}
		}
	}

}
