package com.hums.roomManagementSystem;



public class RoomRegular extends Room {
	

	public RoomRegular(int number, int floor, int bedsCapacity) {
		super(number, floor, bedsCapacity);
	}

	public String toString() {
		
		if(this.getBedsCapacity()==1) {
			return "No "+this.roomNumber+", Floor "+this.getFloor()+", Single, Regular";
		}else if (this.getBedsCapacity()==2){
			return "No "+this.roomNumber+", Floor "+this.getFloor()+", Double, Regular";
		}else {
			return "No "+this.roomNumber+", Floor "+this.getFloor()+", Triple, Regular";
		}
		
	}
	
	
}
