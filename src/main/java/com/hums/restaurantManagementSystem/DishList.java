package com.hums.restaurantManagementSystem;

import java.util.ArrayList;

public class DishList {
	
	private ArrayList<Dish> dishes = new ArrayList<Dish>();
	
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
	
	public void changeDishMenu(String dishname, ArrayList<Integer> dailyMenu) {
		
	}
	
	public ArrayList<int[]> getDishMenu() {
		//gets all menu arrays from every dish in the DishList
		//returns them in an ArrayList<int[]>
		int[] menuMat = new int[7];
		ArrayList<int[]> dishMenu = new ArrayList<int[]>();
		for(int i=0;i<dishes.size();i++) {
			menuMat=dishes.get(i).getMenu();
			dishMenu.add(menuMat);
		}
		return dishMenu;
	}
}
