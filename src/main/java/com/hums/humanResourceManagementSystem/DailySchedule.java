package com.hums.humanResourceManagementSystem;

import com.hums.tools.Pair;
import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalTime;

public class DailySchedule implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String day;
	private ArrayList<Pair<LocalTime,LocalTime>> hours;
	
	public DailySchedule(String day, ArrayList<Pair<LocalTime,LocalTime>> hours) {
		this.day = day;
		this.hours = hours;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public ArrayList<Pair<LocalTime,LocalTime>> getHours() {
		return hours;
	}

	public void setHours(ArrayList<Pair<LocalTime,LocalTime>> hours) {
		this.hours = hours;
	}
	
}
