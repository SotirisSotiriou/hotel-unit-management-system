package com.hums.restaurantManagementSystem;

public class REMS_Registry {
	private Storage storage;
	private DishList menu;
	
	public REMS_Registry(Storage storage, DishList menu) {
		this.storage = storage;
		this.menu = menu;
	}

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
