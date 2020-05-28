package com.hums.roomManagementSystem;

import java.time.LocalDate;

public class Customer {
	private int id;
	private String lastname;
	private String firstname;
	private String phone;
	private LocalDate firstReservationDate;
	private RoomReservationList reservationHistory;
	
	private static int nextID = 0;
	
	public Customer(String lastname, String firstname, String phone, LocalDate date) {
		this.id = nextID;
		nextID++;
		this.lastname = lastname;
		this.firstname = firstname;
		this.phone = phone;
		this.firstReservationDate = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getFirstReservationDate() {
		return firstReservationDate;
	}

	public void setFirstReservationDate(LocalDate firstReservationDate) {
		this.firstReservationDate = firstReservationDate;
	}

	public static int getNextID() {
		return nextID;
	}

	public RoomReservationList getReservationHistory() {
		return reservationHistory;
	}

	public void setReservationHistory(RoomReservationList reservationHistory) {
		this.reservationHistory = reservationHistory;
	}
	
	
	public String toString() {
		return id+"."+lastname+" "+firstname+" "+phone;
	}
	
}
