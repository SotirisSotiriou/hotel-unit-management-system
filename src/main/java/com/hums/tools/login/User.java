package com.hums.tools.login;

import java.io.Serializable;

public abstract class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;

    public User(String firstname, String lastname, String username, String password, String email){
        this.firstname = firstname;
        this.lastname = lastname;
    	this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password; 
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
}