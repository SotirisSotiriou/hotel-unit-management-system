package com.hums.eventManagementSystem;
import java.io.Serializable;
import java.util.ArrayList;


public class EventReservationList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int nextID=0;
	
	private ArrayList<EventReservation> reservations=new ArrayList<EventReservation>();
	
	public boolean addReservation(EventReservation e)
	{
		
		if(EMS_Registry.getInstance().getHallList().getHalls().size()!=0) {
			
			
			
			e.setHall( EMS_Registry.getInstance().getHallList().getAvailableHall(e.getStartTime(), 
					e.getEndTime(), 
					e.getInfo().getCapacity()) );
			
			nextID++;
			e.setId(nextID);
			
		}else {
			return false;
		}
		
		
		
		if(e.getHall()==null) {
			return false;
		}
		
		reservations.add(e);
		return true;
		
	}
	
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
