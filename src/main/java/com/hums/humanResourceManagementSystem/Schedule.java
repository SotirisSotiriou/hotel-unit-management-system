package com.hums.humanResourceManagementSystem;

import java.util.ArrayList;

public class Schedule {
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
