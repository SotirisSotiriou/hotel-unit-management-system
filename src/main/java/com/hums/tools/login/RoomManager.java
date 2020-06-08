package com.hums.tools.login;

import java.io.Serializable;

public class RoomManager extends User implements Serializable{

	private static final long serialVersionUID = 1L;

	public RoomManager(String firstname, String lastname, String username, String password, String email){
        super(firstname, lastname, username, password, email);
    }
}