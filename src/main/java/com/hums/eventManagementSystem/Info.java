package com.hums.eventManagementSystem;
import java.io.Serializable;

public class Info implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String firstname;
	private String lastname;
	private String telephone;
    private String notes;
    private int capacity;
    
    public Info(String firstname,String lastname,String telephone,String notes,int capacity)
    {
    	this.firstname=firstname;
    	this.lastname=lastname;
    	this.telephone=telephone;
    	this.notes=notes;
    	this.capacity=capacity;
    }

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
    
    public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	//Ìethod for returning all the details of the holder of the reservation.
	public String toString()
	{
		return (firstname+", "+lastname+", "+telephone);
	}
    
    
}
