package com.hums.humanResourceManagementSystem;

import java.io.Serializable;
import java.util.ArrayList;

public class Schedule implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<DailySchedule> weekSchedule;
	
	public Schedule(ArrayList<DailySchedule> weekSchedule) {
		this.weekSchedule = weekSchedule;
	}

	public ArrayList<DailySchedule> getWeekSchedule() {
		return weekSchedule;
	}

	public void setWeekSchedule(ArrayList<DailySchedule> weekSchedule) {
		this.weekSchedule = weekSchedule;
	}
	
	
}
