package com.hums.roomManagementSystem;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;



public class RoomReservationList implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	public RoomReservation getReservationByID(int id) {
		
		for (RoomReservation roomReservation : reservations) {
			if (roomReservation.getId()==id)
				return roomReservation;
		}
		return null;
	}
	
	public ArrayList<RoomReservation> getReservationsByCustomerTerm(String term){
		
		ArrayList<RoomReservation> reservationsByCustomersWithTerm = new ArrayList<RoomReservation>();
		
		ArrayList<Customer> customersWithTerm = RMS_Registry.getInstance().getCustomerList().getCustomersByTerm(term);
		
		
		for (RoomReservation roomReservation : reservations) {
			
			
			for (Customer customer : customersWithTerm) {
				
				if(roomReservation.getCustomer().equals(customer)) {
					
					reservationsByCustomersWithTerm.add(roomReservation);
					
				}
				
				
			}
			
			
		}
		
		
		return reservationsByCustomersWithTerm;
	}
	
	
}
