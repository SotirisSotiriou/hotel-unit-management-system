package com.hums.eventManagementSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;


public class HallList implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	ArrayList<Hall> halls=new ArrayList<Hall>();
	int nextCode=0;
	
	public ArrayList<Hall> getHalls() {
		return halls;
	}
	
	
	//Method for adding hall.
	public void addHall(Hall hall)
	{
		
		Collections.sort(halls);
		nextCode++;
		hall.setCode(nextCode);
		halls.add(hall);

	}
	
	public void deleteHallByCode(int code) {
		
		
		for (int i = 0; i < halls.size(); i++) {
			
			if (halls.get(i).getCode()==code)
				halls.remove(i);
			
		}
		
	}
	
	public Hall getHallByCode(int code) {
		
		
		for (Hall hall : halls) {
			if(hall.getCode() == code) {
				return hall;
			}
		}
		
		return null;
	}
	
	public  ArrayList<EventReservation> getReservationsByHall(int code)
	{
		
		EventReservationList erl = EMS_Registry.getInstance().getEventReservationList();
		Hall hall=null;
		
		for(Hall h:halls)
		{
			if(code==h.getCode())
			{
				hall=h;
			}
		}
		if(hall==null)
		{
			JOptionPane.showMessageDialog(null, "Error finding Hall", "Warning",JOptionPane.WARNING_MESSAGE);
			return null;
		}
		else
		{
			ArrayList<EventReservation> list=erl.getReservations();
			
			ArrayList<EventReservation> resForSerHall=new ArrayList<EventReservation>();
			
			for(EventReservation e:list)
			{
				if(e.getHall()==hall && (e.getCondition()==Condition.FUTURE || e.getCondition()==Condition.PRESENT))
				{
					resForSerHall.add(e);
				}
			}
			
			return resForSerHall;
			
		}
	}
	
}
