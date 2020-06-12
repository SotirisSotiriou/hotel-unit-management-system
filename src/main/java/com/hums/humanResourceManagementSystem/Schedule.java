package com.hums.humanResourceManagementSystem;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

public class Schedule implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<DailySchedule> weekSchedule = new ArrayList<DailySchedule>();
	
	public Schedule() {
		this.weekSchedule = new ArrayList<DailySchedule>();
	}

	public ArrayList<DailySchedule> getWeekSchedule() {
		return weekSchedule;
	}

	public void setWeekSchedule(ArrayList<DailySchedule> weekSchedule) {
		this.weekSchedule = weekSchedule;
	}
	
	public void removeSchedule(DailySchedule dailySchedule) {
		weekSchedule.remove(dailySchedule);
	}
	
	public DailySchedule searchSchedule(String day, LocalTime start, LocalTime end) {
		for(DailySchedule s : weekSchedule) {
			if(s.getDay().equals(day)) {
				return s;
			}
		}
		return null;
	}
	
	public void setSchedule(DailySchedule schedule) {
		for(DailySchedule daily : this.weekSchedule) {
			if(daily.getDay().equals(schedule.getDay())) {
				daily.setStart(schedule.getStart());
				daily.setEnd(schedule.getEnd());
				return;
			}
		}
		this.weekSchedule.add(schedule);
	}
}
