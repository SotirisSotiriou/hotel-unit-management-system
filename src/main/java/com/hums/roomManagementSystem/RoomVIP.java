package com.hums.roomManagementSystem;




public class RoomVIP extends Room{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoomVIP(int number, int floor, int bedsCapacity) {
		super(number, floor, bedsCapacity);
	}
	
	public String toString() {
		
		if(this.getBedsCapacity()==1) {
			return "No "+this.roomNumber+", Floor "+this.getFloor()+", Single, VIP";
		}else if (this.getBedsCapacity()==2){
			return "No "+this.roomNumber+", Floor "+this.getFloor()+", Double, VIP";
		}else {
			return "No "+this.roomNumber+", Floor "+this.getFloor()+", Triple, VIP";
		}
		
	}
}
