package com.hums.roomManagementSystem;

import java.time.LocalDate;
import java.util.ArrayList;

public class RoomList {
	private ArrayList<Room> rooms;
	
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
	
	
	
	public ArrayList<Room> getAvailableRooms(LocalDate checkIN, LocalDate checkOUT, ArrayList<RoomReservation> reservations){
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
}
