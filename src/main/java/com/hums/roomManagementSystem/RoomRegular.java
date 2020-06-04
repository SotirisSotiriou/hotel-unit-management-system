package com.hums.roomManagementSystem;


public class RoomRegular extends Room {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoomRegular(int number, int floor, int bedsCapacity) {
		super(number, floor, bedsCapacity);
		setDefaultCostPerDay();
	}
	
	private void setDefaultCostPerDay() {
		this.setCostPerDay( RMS_Registry.getInstance().getRoomList().getRegularCost() * this.getBeds() );
	}
	
	public String toString() {
		
		if(this.getBeds()==1) {
			return "No "+this.roomNumber+", Floor "+this.getFloor()+", Single, Regular";
		}else if (this.getBeds()==2){
			return "No "+this.roomNumber+", Floor "+this.getFloor()+", Double, Regular";
		}else {
			return "No "+this.roomNumber+", Floor "+this.getFloor()+", Triple, Regular";
		}
		
	}
	
	
}
