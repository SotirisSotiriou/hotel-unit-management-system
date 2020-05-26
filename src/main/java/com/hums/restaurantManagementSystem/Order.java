package com.hums.restaurantManagementSystem;

import java.util.ArrayList;

public class Order {
	
	private ArrayList<Dish> order = new ArrayList<>();
	
	public Order(ArrayList<Dish> order)
	{
		this.setOrder(order);
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

	public ArrayList<Dish> getOrder() {
		return order;
	}

	public void setOrder(ArrayList<Dish> order) {
		this.order = order;
	}

}
