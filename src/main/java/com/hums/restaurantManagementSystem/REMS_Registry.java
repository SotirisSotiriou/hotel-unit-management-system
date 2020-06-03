package com.hums.restaurantManagementSystem;

import java.io.Serializable;

public class REMS_Registry implements Serializable{
	
	private static REMS_Registry registryInstance = null;
	
	private REMS_Registry() {
		
	}

	public static REMS_Registry getInstance() {
			
			if(registryInstance == null) {
				registryInstance = new REMS_Registry();
			}
			
			return registryInstance;
			
	}
	
	private Storage storage;
	private DishList menu;
	
	
	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public DishList getMenu() {
		return menu;
	}

	public void setMenu(DishList menu) {
		this.menu = menu;
	}
	
	
}
