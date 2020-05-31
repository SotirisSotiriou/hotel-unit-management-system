package com.hums.roomManagementSystem;




public class RoomPenthouse extends Room {
	
	
	public RoomPenthouse(int number, int floor, int bedsCapacity) {
		super(number, floor, bedsCapacity);
	}
	
	public String toString() {
		
		if(this.getBedsCapacity()==1) {
			return "No "+this.roomNumber+", Floor "+this.getFloor()+", Single, Penthouse";
		}else if (this.getBedsCapacity()==2){
			return "No "+this.roomNumber+", Floor "+this.getFloor()+", Double, Penthouse";
		}else {
			return "No "+this.roomNumber+", Floor "+this.getFloor()+", Triple, Penthouse";
		}
		
	}
}
