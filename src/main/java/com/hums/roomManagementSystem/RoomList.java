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
	
	public boolean checkRoomAvailability(Room room, LocalDate checkIN, LocalDate checkOUT, ArrayList<RoomReservation> reservations) {
		boolean available = true;
		for(RoomReservation rr : reservations) {
			//if wanted check in or check out date is between another reservation check in and check out
			if((checkIN.isAfter(rr.getCheckIN()) && checkIN.isBefore(rr.getCheckOUT())) ||
				checkOUT.isAfter(rr.getCheckIN()) && checkOUT.isBefore(rr.getCheckOUT())) {
				available = false;
			}
		}
		return available;
	}
	
	public boolean checkRoomFeatures(Room room, ArrayList<RoomFeature> wantedFeatures){
		for(RoomFeature f : wantedFeatures) {
			if(!room.getFeatures().contains(f)) 
				return false;
		}
		return true;
	}
	
	public ArrayList<Room> getAvailableRooms(ArrayList<Room> rooms, LocalDate checkIN, LocalDate checkOUT, ArrayList<RoomReservation> reservations){
		ArrayList<Room> availableRooms = new ArrayList<>();
		for(Room room : rooms) {
			boolean available = this.checkRoomAvailability(room, checkIN, checkOUT, reservations);
			if(available) {
				availableRooms.add(room);
			}
		}
		return availableRooms;
	}
	
	public ArrayList<Room> getRoomsWithFeatures(ArrayList<Room> rooms, ArrayList<RoomFeature> features){
		ArrayList<Room> roomsWithFeatures = new ArrayList<>();
		for(Room room : rooms) {
			boolean hasFeatures = this.checkRoomFeatures(room, features);
			if(hasFeatures) {
				roomsWithFeatures.add(room);
			}
		}
		return roomsWithFeatures;
	}
}
