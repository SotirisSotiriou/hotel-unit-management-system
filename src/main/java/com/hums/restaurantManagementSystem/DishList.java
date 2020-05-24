package com.hums.restaurantManagementSystem;

import java.util.ArrayList;

public class DishList {
	
	private ArrayList<Dish> dishes = new ArrayList();
	
	public DishList(ArrayList<Dish> dishes)
	{
		this.dishes = dishes ;
	}
	
	public void setDishes(ArrayList<Dish> dishes)
	{
		this.dishes = dishes;
	}
	
	public ArrayList<Dish> getDishes()
	{
		return dishes;
	}
	
	public void addDish(Dish dish)
	{
		dishes.add(dish);
		//write to file//
	}
	
	public void removeDish(String dishname)
	{
		for(int i=0;i<dishes.size();i++)
		{
			if(dishname.equals(dishes.get(i).getName()))
			{
				dishes.remove(i);
				break;
			}
		}
	}
}
