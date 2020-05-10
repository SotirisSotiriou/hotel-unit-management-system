package com.hums.roomManagementSystem;

import java.time.LocalDate;
import com.hums.Condition;

public class RoomReservation {
	private int id;
	private LocalDate checkIN;
	private LocalDate checkOUT;
	private boolean isBilled;
	private int mealsPerDay;
	private EndReasonType endReason;
	private Condition condition;
	private Customer customer;
	
	private static int nextID = 0;
	
	public RoomReservation(LocalDate checkIN, LocalDate checkOUT, int mealsPerDay, Customer customer) {
		this.id = nextID;
		nextID++;
		this.checkIN = checkIN;
		this.checkOUT = checkOUT;
		this.mealsPerDay = mealsPerDay;
		this.isBilled = false;
		this.endReason = EndReasonType.NOT_ENDED;
		this.condition = Condition.FUTURE;
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getCheckIN() {
		return checkIN;
	}

	public void setCheckIN(LocalDate checkIN) {
		this.checkIN = checkIN;
	}

	public LocalDate getCheckOUT() {
		return checkOUT;
	}

	public void setCheckOUT(LocalDate checkOUT) {
		this.checkOUT = checkOUT;
	}

	public boolean isBilled() {
		return isBilled;
	}

	public void setBilled(boolean isBilled) {
		this.isBilled = isBilled;
	}

	public int getMealsPerDay() {
		return mealsPerDay;
	}

	public void setMealsPerDay(int mealsPerDay) {
		this.mealsPerDay = mealsPerDay;
	}

	public EndReasonType getEndReason() {
		return endReason;
	}

	public void setEndReason(EndReasonType endReason) {
		this.endReason = endReason;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public static int getNextID() {
		return nextID;
	}
	
	public String toString() {
		return "Room Reservation: " + this.id + ", " + "Chech in: " + this.checkIN.getDayOfMonth()
				+ "/" + this.checkIN.getMonthValue() + "/" + this.checkIN.getYear() + ", "
				+ "Check out: " + this.checkOUT.getDayOfMonth() + "/" + this.checkOUT.getMonthValue()
				+ "/" + this.checkOUT.getYear() + ", " + "Meals: " + this.mealsPerDay + "\n"
				+ this.customer.toString();
	}
}
