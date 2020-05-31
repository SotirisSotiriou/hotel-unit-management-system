package com.hums.eventManagementSystem;
import java.io.Serializable;
import java.util.ArrayList;


public class EventReservationList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<EventReservation> reservations=new ArrayList<EventReservation>();
	
	
	
	public ArrayList<EventReservation> getReservations() {
		return reservations;
	}
	
	public EventReservation getReservationById(int id) {
		
		
		for (EventReservation reservation : reservations) {
			
			if(reservation.getId()==id) {
				return reservation;
			}
			
		}
		
		return null;
	}
	
	public void deleteReservationByID(int id) {
		
		for (int i = 0; i < reservations.size(); i++) {
			
			if (reservations.get(i).getId()==id)
				reservations.remove(i);
			
		}
		
	}
}
