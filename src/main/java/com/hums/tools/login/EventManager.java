package com.hums.tools.login;

import java.io.Serializable;

public class EventManager extends User implements Serializable{

	private static final long serialVersionUID = 1L;

	public EventManager(String firstname, String lastname, String username, String password, String email){
        super(firstname, lastname, username, password, email);
    }
}