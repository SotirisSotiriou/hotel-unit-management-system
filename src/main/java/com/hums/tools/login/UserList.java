package com.hums.tools.login;

import java.util.ArrayList;

public class UserList{
    private ArrayList<User> uList;

    public UserList(){
        this.uList = new ArrayList<>();
    }

    public UserList(ArrayList<User> ulist){
        this.uList = ulist;
    }

    public int login(String username, String password){
        int type = -1;

        for(User u : this.uList){
            if(u.getUsername().equals(username)){
                if(u.getPassword().equals(password)){
                    if(u instanceof RoomManager){
                        type = 1;
                    }
                    else if(u instanceof EventManager){
                        type = 2;
                    }
                    else if(u instanceof RestaurantManager){
                        type = 3;
                    }
                    else if(u instanceof HumanRecourceManager){
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
}