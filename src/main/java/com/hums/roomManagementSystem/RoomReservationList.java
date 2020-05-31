package com.hums.roomManagementSystem;

import java.time.LocalDate;
import java.util.ArrayList;

public class RoomReservationList {
	private ArrayList<RoomReservation> reservations;
	
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
		this.reservations.add(reservation);
	}
	
	public void cancelReservation(RoomReservation reservation) {
		reservation.setCheckOutDate(LocalDate.now());
		reservation.setEndReason(RoomReservation.endReasonType.CANCELED);
	}
	
	
}
