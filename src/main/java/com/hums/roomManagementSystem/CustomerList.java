package com.hums.roomManagementSystem;

import java.util.ArrayList;





public class CustomerList{
	private int nextID =0;
	private ArrayList<Customer> customers;
	
	public CustomerList(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	
	public CustomerList() {
		this.customers = new ArrayList<>();
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	
	public boolean addCustomer(Customer customer) {
		
		nextID++;
		this.customers.add(customer);
		customer.setId(nextID);
		return true;
	}
	
	public Customer searchCustomerByName(String lastname, String firstname) {
		Customer foundCustomer = null;
		
		for(Customer customer : this.customers) {
			if(customer.getLastname().equals(lastname) && customer.getFirstname().equals(firstname)) {
				foundCustomer = customer;
				break;
			}
		}
		
		return foundCustomer;
	}
	
	
	public Customer searchCustomerByID(int id) {
		Customer foundCustomer = null;
		
		for(Customer customer : this.customers) {
			if(customer.getId() == id) {
				foundCustomer = customer;
				break;
			}
		}
		return foundCustomer;
	}
	
	public void changeCustomerInfo(Customer customer, String lastname, String firstname, String phone) {
		customer.setLastname(lastname);
		customer.setFirstname(firstname);
		customer.setPhone(phone);
	}
	
	
	public ArrayList<RoomReservation> getReservationsByCustomer(Customer customer, ArrayList<RoomReservation> allReservations) {
		ArrayList<RoomReservation> reservationsByCustomer = new ArrayList<>();
		for(RoomReservation rr : allReservations) {
			if(customer.equals(rr.getCustomer())) {
				reservationsByCustomer.add(rr);
			}
		}
		return reservationsByCustomer;
	}
	
	public boolean customerExists(String lastname, String firstname, String phone) {
		for(Customer c : this.customers) {
			if(c.getLastname().equals(lastname) && c.getFirstname().equals(firstname) && c.getPhone().equals(phone)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean customerExists(Customer customer) {
		return this.customers.contains(customer);
	}
}
