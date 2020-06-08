package com.hums.tools.login;

import java.io.Serializable;

public class HumanResourceManager extends User implements Serializable{

	private static final long serialVersionUID = 1L;

	public HumanResourceManager(String firstname, String lastname, String username, String password, String email){
        super(firstname, lastname, username, password, email);
    }
}