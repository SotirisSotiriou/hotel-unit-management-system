package com.hums.tools.login;

import java.io.Serializable;
import java.util.ArrayList;

public class UserList implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static UserList instance = null;
	
	private ArrayList<User> uList;

	private UserList(){
		this.uList = new ArrayList<>();
	}

	public synchronized static UserList getInstance() {
		
		if(instance == null) {
			instance = new UserList();
		}
		
		return instance;
	}
	
	public static void resetInstance() {
		instance = new UserList();
	}
	

    public int login(String username, String password){
        int type = -1;

        for(User u : this.uList){
            if(u.getUsername().equals(username)){
                if(u.getPassword().equals(password)){
                    if(u instanceof RoomManager){
                        type = 1;
                    }
                    else if(u instanceof HumanResourceManager){
                        type = 2;
                    }
                    else if(u instanceof EventManager){
                        type = 3;
                    }
                    else if(u instanceof RestaurantManager){
                        type = 4;
                    }
                }
                else{
                    type = 0;
                }   
            }
        }

        
        /* if type = -1 user not exists
         * if type = 0 user exists but password is wrong
         * if type = 1 user logged in as receptionist
         * if type = 2 user logged in as human resource manager
         * if type = 3 user logged in as event manager
         * if type = 4 user logged in as restaurant manager
         */
        return type;
    }
    
    
    public void register(String firstname, String lastname, String username, String password, String email, String type) {
    	switch(type) {
    	case "Receptionist":
    		User rm = new RoomManager(firstname, lastname, username, password, email);
    		this.uList.add(rm);
    		break;
    	case "HR Manager":
    		User hr = new HumanResourceManager(firstname, lastname, username, password, email);
    		this.uList.add(hr);
    		break;
    	case "Event Manager":
    		User em = new EventManager(firstname, lastname, username, password, email);
    		this.uList.add(em);
    		break;
    	case "Restaurant Manager":
    		User rem = new RestaurantManager(firstname, lastname, username, password, email);
    		this.uList.add(rem);
    		break;
    	}
    }
    
    public void addUser(User user) {
    	this.uList.add(user);
    }
    
    
    public boolean usernameExists(String username) {
    	for(User u : this.uList) {
    		if(u.getUsername().equals(username)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean emailExists(String email) {
    	for(User u : this.uList) {
    		if(u.getEmail().equals(email)) {
    			return true;
    		}
    	}
    	return false;
    }
}