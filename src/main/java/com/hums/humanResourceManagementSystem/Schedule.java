package com.hums.humanResourceManagementSystem;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

import com.hums.tools.Pair;

public class Schedule implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<DailySchedule> weekSchedule;
	
	public Schedule(ArrayList<DailySchedule> weekSchedule) {
		this.weekSchedule = weekSchedule;
	}
	
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
				for(Pair<LocalTime, LocalTime> period : s.getHours()) {
					if(period.getElement1() == start && period.getElement2() == end) return s;
				}
			}
		}
		return null;
	}
	
	public void addSchedule(DailySchedule schedule) {
		for(DailySchedule daily : this.weekSchedule) {
			if(daily.getDay().equals(schedule.getDay())) {
				daily.getHours().addAll(schedule.getHours());
				return;
			}
		}
		this.weekSchedule.add(schedule);
	}
}
