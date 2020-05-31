package com.hums.eventManagementSystem;
import java.io.Serializable;
import java.time.LocalDateTime;

public class EventReservation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private  LocalDateTime startTime;
	private  LocalDateTime endTime;
	private String eventType;
	private double cost;
	private Info info;
	private Hall hall;
	private Condition condition;
	private int id;
	
	public EventReservation(LocalDateTime startTime,LocalDateTime endTime,String eventType,Info info,Condition condition)
	{
		this.startTime=startTime;
		this.endTime=endTime;
		this.eventType=eventType;
		this.info=info;
		this.condition=condition;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}


	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	//Ìethod that returns the data of the reservation.
	 public String toString()
	 {
		 return (startTime+", "+endTime+", "+", "+eventType+", "+cost+", "+hall.getCapacity()+", "+hall.getCode()+", "+info.toString());
	 }

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	 
	 
}
