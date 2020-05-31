package com.hums.roomManagementSystem;

import java.time.LocalDate;

import java.util.ArrayList;

public class RoomList {
	
	
	private ArrayList<Room> rooms;
	private int regularCost=30;
	private int penthouseCost=80;
	private int vipCost=60;
	
	public RoomList(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	
	public RoomList() {
		this.rooms = new ArrayList<>();
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	
	
	public int getRegularCost() {
		return regularCost;
	}

	public void setRegularCost(int regularCost) {
		this.regularCost = regularCost;
	}

	public int getPenthouseCost() {
		return penthouseCost;
	}

	public void setPenthouseCost(int penthouseCost) {
		this.penthouseCost = penthouseCost;
	}

	public int getVipCost() {
		return vipCost;
	}

	public void setVipCost(int vipCost) {
		this.vipCost = vipCost;
	}
	
	
	
	/*public ArrayList<Room> getAvailableRooms(LocalDate checkIN, LocalDate checkOUT, ArrayList<RoomReservation> reservations){
		ArrayList<Room> availableRooms = new ArrayList<>();
		for(Room room : this.rooms) {
			boolean available = room.checkRoomAvailability(checkIN, checkOUT, reservations);
			if(available) {
				availableRooms.add(room);
			}
		}
		return availableRooms;
	}
	
	public ArrayList<Room> getRoomsWithFeatures(ArrayList<Room> rooms, ArrayList<RoomFeature> features){
		ArrayList<Room> roomsWithFeatures = new ArrayList<>();
		for(Room room : rooms) {
			boolean hasFeatures = room.checkRoomFeatures(features);
			if(hasFeatures) {
				roomsWithFeatures.add(room);
			}
		}
		return roomsWithFeatures;
	}
	*/
}
