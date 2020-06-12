package com.hums.roomManagementSystem;

import java.io.Serializable;
import java.util.ArrayList;


public class CustomerList implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	public ArrayList<Customer> getCustomersByTerm(String term) {
		
		ArrayList<Customer> foundCustomers = new ArrayList<Customer>();
		
		for(Customer customer : this.customers) {
			if( customer.getFirstName().toUpperCase().contains(term.toUpperCase()) || customer.getLastName().toUpperCase().contains(term.toUpperCase()) || customer.getPhone().contains(term) ) {
				foundCustomers.add(customer);
			}
		}
		
		return foundCustomers;
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
		customer.setLastName(lastname);
		customer.setFirstName(firstname);
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
		boolean exists = false;
		for(Customer c : this.customers) {
			if(c.getLastName().equals(lastname) && c.getLastName().equals(firstname) && c.getPhone().equals(phone)) {
				exists = true;
			}
		}
		return exists;
	}
	
	public boolean customerExists(Customer customer) {
		return this.customers.contains(customer);
	}
	
	public void removeCustomerByID(int id) {
		
		for (int i = 0; i < customers.size(); i++) {
			
			if (customers.get(i).getId()==id)
				customers.remove(i);
			
		}
		
	}
	
	public Customer getCustomerByToString(String string) {
		
		for(int i = 0; i < customers.size(); i++) {
			
			if (customers.get(i).toString().equals(string))
				return customers.get(i);
			
		}
		
		return null;
	}
	
	public Customer getCustomerByID(int id) {
		
		for (Customer customer : customers) {
			if(customer.getId()==id)
				return customer;
		}
		
		return null;
	}
	
	
}