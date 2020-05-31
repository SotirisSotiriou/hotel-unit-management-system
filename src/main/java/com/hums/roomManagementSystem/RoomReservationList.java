package com.hums.roomManagementSystem;

import java.time.LocalDate;
import java.util.ArrayList;


public class RoomReservationList {
	
	private ArrayList<RoomReservation> reservations;
	
	private int nextID;
	
	public RoomReservationList(ArrayList<RoomReservation> reservations) {
		this.reservations = reservations;
	}
	
	public RoomReservationList() {
		this.reservations = new ArrayList<>();
	}

	public ArrayList<RoomReservation> getReservations() {
		return reservations;
	}

	public void setReservations(ArrayList<RoomReservation> reservations) {
		this.reservations = reservations;
	}
	
	
	public void addReservation(RoomReservation reservation) {
		nextID++;
		
		reservation.setId(nextID);
		this.reservations.add(reservation);
	}
	
	public void cancelReservation(RoomReservation reservation) {
		reservation.setCheckOutDate(LocalDate.now());
		reservation.setEndReason(RoomReservation.endReasonType.CANCELED);
	}
	
	public void deleteReservationByID(int id) {
		
		for (int i = 0; i < reservations.size(); i++) {
			
			if (reservations.get(i).getId()==id)
				reservations.remove(i);
			
		}
	}
	
	
}
