package com.hums.restaurantManagementSystem;

import java.util.*;

public class OrderList {
	
	Queue<Order> orders;
	
	public OrderList()
	{
		orders = new LinkedList<>();
	}
	
	public void addOrder(Order o)
	{
		this.orders.add(o);
	}
	
	public Order getFirstOrder()
	{
		return this.orders.remove();
	}
	
	public Queue<Order> getOrders()
	{
		return orders;
	}

}
