package com.hums.roomManagementSystem;

public class RoomFeature {
	private String name;
	
	public RoomFeature(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean equals(RoomFeature other) {
		return this.name == other.name;
	}
}
