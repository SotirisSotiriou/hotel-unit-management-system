package com.hums.roomManagementSystem;
import java.io.Serializable;
import java.time.LocalDate;

public class RoomReservation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	//Customers of the reservation
	private Customer customer;
	
	//Check In and Check Out date
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	
	//Room of reservation
	private Room room;
	
	//if Reservation is active, finished/canceled, billed
	private boolean billed;
	public enum endReasonType{
		FINISHED,
		CANCELED
	}
	private endReasonType endReason;
	
	//Note in case it's needed
	private String notes;
	
	
	private boolean breakfast;
	private boolean launch;
	private boolean dinner;
	
	
	
	private int reservationCost;
	
	

	public RoomReservation(Customer customer, LocalDate checkInDate, LocalDate checkOutDate, Room room, boolean breakfast, boolean launch, boolean dinner, String notes) {
		super();
		this.customer = customer;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.room = room;
		
		this.breakfast = breakfast;
		this.launch = launch;
		this.dinner = dinner;
		
		this.notes = notes;
		
		if (customer.getFirstReservationDate()==null)
			customer.setFirstReservationDate(checkInDate);
		
		calculateReservationCost();
	}
	
	public void calculateReservationCost() {
		
		long daysStayed = java.time.temporal.ChronoUnit.DAYS.between(checkInDate, checkOutDate);
		
		double roomCost = room.getCostPerDay();
		
		double mealsCost = 0;
		if(room.getClass().equals(RoomRegular.class)) {
			
			if(breakfast) {
				mealsCost += RMS_Registry.getInstance().getBreakfastCost();
			}
			if(launch) {
				mealsCost += RMS_Registry.getInstance().getLaunchCost();
			}
			if(dinner) {
				mealsCost += RMS_Registry.getInstance().getDinnerCost();
			}
			
		}
		
		this.reservationCost = (int) ((int) (roomCost * daysStayed) + (mealsCost * daysStayed));
	}
	
	//getters and setters
	
	

	public Customer getCustomer() {
		return customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public boolean isBilled() {
		return billed;
	}

	public void setBilled(boolean billed) {
		this.billed = billed;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public endReasonType getEndReason() {
		return endReason;
	}

	public void setEndReason(endReasonType endReason) {
		this.endReason = endReason;
	}

	public int getReservationCost() {
		return reservationCost;
	}

	public void setReservationCost(int reservationCost) {
		this.reservationCost = reservationCost;
	}
	
	
	
	
	
	
	
	
}
