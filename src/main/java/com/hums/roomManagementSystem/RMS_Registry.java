package com.hums.roomManagementSystem;

import java.util.ArrayList;

public class RMS_Registry {
	private RoomList roomList;
	private RoomReservationList reservationList;
	private CustomerList customerList;
	
	public RMS_Registry(RoomList roomList, RoomReservationList reservationList, CustomerList customerList) {
		this.roomList = roomList;
		this.reservationList = reservationList;
		this.customerList = customerList;
	}
	
	public RMS_Registry(ArrayList<Room> rooms, ArrayList<RoomReservation> reservations, ArrayList<Customer> customers) {
		this.roomList = new RoomList(rooms);
		this.reservationList = new RoomReservationList(reservations);
		this.customerList = new CustomerList(customers);
	}
	
	public RMS_Registry() {
		this.roomList = new RoomList();
		this.reservationList = new RoomReservationList();
		this.customerList = new CustomerList();
	}

	public RoomList getRoomList() {
		return roomList;
	}

	public void setRoomList(RoomList roomList) {
		this.roomList = roomList;
	}

	public RoomReservationList getReservationList() {
		return reservationList;
	}

	public void setReservationList(RoomReservationList reservationList) {
		this.reservationList = reservationList;
	}

	public CustomerList getCustomerList() {
		return customerList;
	}

	public void setCustomerList(CustomerList customerList) {
		this.customerList = customerList;
	}
}
