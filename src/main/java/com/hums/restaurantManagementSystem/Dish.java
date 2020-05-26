package com.hums.restaurantManagementSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class Dish {
	
	private String name;
	private int[] menu;
	public HashMap<Ingredient, Double> ingredients;
	
	public Dish(String name_, HashMap<Ingredient, Double> ingredients_)
	{
		this.name = name_;
		this.ingredients = ingredients_;
		menu = new int[7];
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
	
	public void changeMenu(ArrayList<Integer> dayList) {
		//gets an arraylist of integers and for each one of them makes the representing menu[x] equal to 1
		//the menu[] table is initialized to 0 so if menu[x] isn't altered by this method (due to dayList's contents) is returned as 0
		//days represented: menu[x]
		//x=0:(Monday) x=1:(Tuesday) x=2:(Wednesday) x=3:(Thursday) x=4:(Friday) x=5:(Saturday) x=6:(Sunday)
		int i;
		for (i=0;i<7;i++) {
			menu[i]=0;
		}
		for (i=0;i<dayList.size();i++) {
			menu[dayList.get(i)]=1;
		}
	}
	
	
}
