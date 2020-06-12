package com.hums.restaurantManagementSystem;

import java.io.Serializable;

public class REMS_Registry implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static REMS_Registry registryInstance = null;
	
	private REMS_Registry() {
		this.menu = new DishList();
		this.storage = new Storage();
	}

	public synchronized static REMS_Registry getInstance() {
			
			if(registryInstance == null) {
				registryInstance = new REMS_Registry();
			}
			
			return registryInstance;
			
	}
	
	public static void setInstance(REMS_Registry reg) {
		registryInstance = reg;
	}
	
	public static void resetInstance() {
		registryInstance = new REMS_Registry();
	}
	
	private Storage storage;
	private DishList menu;
	
	
	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public DishList getDishList() {
		return menu;
	}

	public void setMenu(DishList menu) {
		this.menu = menu;
	}
	
	
}
