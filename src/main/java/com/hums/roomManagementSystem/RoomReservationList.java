package com.hums.roomManagementSystem;

import java.time.LocalDate;
import java.util.ArrayList;

import com.hums.Condition;

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
	
	public ArrayList<RoomReservation> getPresentReservations(){
		ArrayList<RoomReservation> present = new ArrayList<>();
		for(RoomReservation r : this.reservations) {
			if(r.getCondition() == Condition.PRESENT) present.add(r);
		}
		return present;
	}
	
	public ArrayList<RoomReservation> getPastReservations(){
		ArrayList<RoomReservation> past = new ArrayList<>();
		for(RoomReservation r : this.reservations) {
			if(r.getCondition() == Condition.PAST) past.add(r);
		}
		
		return past;
	}
	
	public ArrayList<RoomReservation> getFutureReservations() {
		ArrayList<RoomReservation> future = new ArrayList<>();
		for(RoomReservation r : this.reservations) {
			if(r.getCondition() == Condition.FUTURE) future.add(r);
		}
		return future;
	}
	
	public void addReservation(RoomReservation reservation) {
		this.reservations.add(reservation);
	}
	
	public void cancelReservation(RoomReservation reservation) {
		reservation.setCheckOUT(LocalDate.now());
		reservation.setEndReason(EndReasonType.CANCELED);
	}
	
	public void deleteReservation(RoomReservation reservation) {
		this.reservations.remove(reservation);
	}
	
	public boolean checkDateAvailability(LocalDate checkIN, LocalDate checkOUT) {
		for(RoomReservation rr : this.reservations) {
			if(rr.checkDate(checkIN, checkOUT) == false) return false;
			
		}
		return true;
	}
}
