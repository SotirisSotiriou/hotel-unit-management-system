package com.hums.roomManagementSystem;


import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class RoomList {
	
	
	private ArrayList<Room> rooms;
	private int regularCost=30;
	private int penthouseCost=80;
	private int vipCost=60;
	
	public RoomList(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	
	public RoomList() {
		this.rooms = new ArrayList<>();
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	
	
	public int getRegularCost() {
		return regularCost;
	}

	public void setRegularCost(int regularCost) {
		this.regularCost = regularCost;
	}

	public int getPenthouseCost() {
		return penthouseCost;
	}

	public void setPenthouseCost(int penthouseCost) {
		this.penthouseCost = penthouseCost;
	}

	public int getVipCost() {
		return vipCost;
	}

	public void setVipCost(int vipCost) {
		this.vipCost = vipCost;
	}
	
	public void addRoom(Room aRoom) {
		
		boolean alreadyExists = false;
		
		for (Room room : rooms) {
			if(room.getRoomNumber() == aRoom.getRoomNumber()) {
				alreadyExists = true;
				JOptionPane.showMessageDialog(null, "Room with this number already exists!","Warning",JOptionPane.WARNING_MESSAGE);
				break;
			}
		}
		
		if(alreadyExists == false) {
			rooms.add(aRoom);
			
			if(aRoom.getClass().equals(RoomRegular.class))
				aRoom.setCostPerDay(regularCost);
			else if(aRoom.getClass().equals(RoomPenthouse.class))
				aRoom.setCostPerDay(penthouseCost);
			else
				aRoom.setCostPerDay(vipCost);
			
			JOptionPane.showMessageDialog(null, "New room created successfully");
			
		}
		
			
	}
	
	public void deleteRoomByNumber(int number) {
		
		
		for (int i = 0; i < rooms.size(); i++) {
			
			if (rooms.get(i).getRoomNumber()==number)
				rooms.remove(i);
			
		}
		
	}
	
	public Room getRoomByNumber(int number) {
		
		
		for (int i = 0; i < rooms.size(); i++) {
			
			if (rooms.get(i).getRoomNumber()==number)
				return rooms.get(i);
			
		}
		return null;
		
	}
	
	public  ArrayList<RoomReservation> getReservationsByRoom(int roomNumber, RoomReservationList rrl)
	{
		Room room=null;
		
		for(Room r:rooms)
		{
			if(r.getRoomNumber() == roomNumber)
			{
				room=r;
			}
		}
		if(room==null)
		{
			JOptionPane.showMessageDialog(null, "Error finding Room", "Warning",JOptionPane.WARNING_MESSAGE);
			return null;
		}
		else
		{
			ArrayList<RoomReservation> reservationsList=rrl.getReservations();
			
			ArrayList<RoomReservation> resForSerRoom=new ArrayList<RoomReservation>();
			
			for(RoomReservation roomReservation: reservationsList)
			{
				if(roomReservation.getRoom()==room)
				{
					resForSerRoom.add(roomReservation);
				}
			}
			
			return resForSerRoom;
			
		}
	}
	
	public ArrayList<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut, int beds, int type, RoomReservationList rrl) {
		
		ArrayList<Room> filteredRooms = new ArrayList<Room>();
		
		for (Room room : rooms) {
			
			if(type==1) {
				
				if(room.getType().equals("Regular")) 
					filteredRooms.add(room);
			}
			
			else if(type==2) {
				
				if (room.getType().equals("Penthouse")) 
					filteredRooms.add(room);
			}
			
			else {
				
				if (room.getType().equals("VIP")) 
					filteredRooms.add(room);
				
			}
			
		}
		
		if(filteredRooms.size()==0)
			return null;
		
		ArrayList<Room> availableRooms = new ArrayList<Room>();
		
		for (Room room : filteredRooms) {
			
			if(room.getBedsCapacity() == beds) {
				
				ArrayList<RoomReservation> reservationsByRoom = this.getReservationsByRoom(room.getRoomNumber(), rrl);
				
				if(reservationsByRoom.size()==0){
					availableRooms.add(room);
				}else {
					
					for (RoomReservation roomReservation : reservationsByRoom) {
						
						
						if( ( checkIn.isAfter(roomReservation.getCheckInDate()) && checkIn.isBefore(roomReservation.getCheckInDate()) )
							|| ( checkOut.isAfter(roomReservation.getCheckInDate()) && checkOut.isBefore(roomReservation.getCheckOutDate()) )
							|| (checkIn.isBefore(roomReservation.getCheckInDate()) && checkOut.isAfter(roomReservation.getCheckInDate()))
							|| (checkIn.isEqual(roomReservation.getCheckInDate()) || checkOut.isEqual(roomReservation.getCheckOutDate())
								|| checkIn.isEqual(roomReservation.getCheckOutDate()) || checkOut.isEqual(roomReservation.getCheckInDate())))
							  {
								break;
						}else {
							availableRooms.add(room);
							break;
						}
						
												
					}
					
				}
				
				
					
			}
			
		}
		
		return availableRooms;
	}
	
	/*public ArrayList<Room> getAvailableRooms(LocalDate checkIN, LocalDate checkOUT, ArrayList<RoomReservation> reservations){
		ArrayList<Room> availableRooms = new ArrayList<>();
		for(Room room : this.rooms) {
			boolean available = room.checkRoomAvailability(checkIN, checkOUT, reservations);
			if(available) {
				availableRooms.add(room);
			}
		}
		return availableRooms;
	}
	
	public ArrayList<Room> getRoomsWithFeatures(ArrayList<Room> rooms, ArrayList<RoomFeature> features){
		ArrayList<Room> roomsWithFeatures = new ArrayList<>();
		for(Room room : rooms) {
			boolean hasFeatures = room.checkRoomFeatures(features);
			if(hasFeatures) {
				roomsWithFeatures.add(room);
			}
		}
		return roomsWithFeatures;
	}
	*/
}
