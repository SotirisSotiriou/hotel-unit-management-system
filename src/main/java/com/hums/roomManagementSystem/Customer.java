package com.hums.roomManagementSystem;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Customer implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lastName;
	private String firstName;
	private String phone;
	private int id;
	private LocalDate firstReservationDate = null;
	private ArrayList<RoomReservation> reservationsHistory;
	
	private boolean active;
	
	//diafores alles idiotites pelati 3enodoxeiou
	
	public Customer(String lastName, String firstName, String phone) {
		
		
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.phone = phone;
		
		
	}
	
	
	
	public void setFirstReservationDate(LocalDate firstReservationDate) {
		this.firstReservationDate = firstReservationDate;
	}


	public void addReservationToCustomerHistory(RoomReservation aReserv) {
		
		reservationsHistory.add(aReserv);
		
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getPhone() {
		return phone;
	}

	public LocalDate getFirstReservationDate() {
		return firstReservationDate;
	}

	public ArrayList<RoomReservation> getReservationsHistory() {
		return reservationsHistory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setReservationsHistory(ArrayList<RoomReservation> reservationsHistory) {
		this.reservationsHistory = reservationsHistory;
	}
	
	
	public String toString() {
		
		return id+"."+lastName+" "+firstName+" "+phone;
		
	}
	
	
	
	
	
	
}
