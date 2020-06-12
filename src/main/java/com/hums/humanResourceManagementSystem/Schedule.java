package com.hums.humanResourceManagementSystem;

import java.io.Serializable;
<<<<<<< HEAD
=======
import java.time.LocalDate;
import java.time.LocalDateTime;
>>>>>>> branch 'master' of https://github.com/SotirisSotiriou/Hotel-Unit-Management-System
import java.time.LocalTime;
import java.util.ArrayList;

import com.hums.tools.Pair;

public class Schedule implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<DailySchedule> weekSchedule = new ArrayList<DailySchedule>(7);
	
	
	public Schedule() {
		
		for (int i = 0; i <7; i++) {
			
			switch (i) {
			case 0:
				weekSchedule.add(new DailySchedule(LocalDateTime.of(LocalDate.of(2020, 6, 1), LocalTime.of(8, 0)),LocalDateTime.of(LocalDate.of(2020, 6, 1), LocalTime.of(16, 0))));
				break;
			case 1:
				weekSchedule.add(new DailySchedule(LocalDateTime.of(LocalDate.of(2020, 6, 2), LocalTime.of(8, 0)),LocalDateTime.of(LocalDate.of(2020, 6, 2), LocalTime.of(16, 0))));
				break;
			case 2:
				weekSchedule.add(new DailySchedule(LocalDateTime.of(LocalDate.of(2020, 6, 3), LocalTime.of(8, 0)),LocalDateTime.of(LocalDate.of(2020, 6, 3), LocalTime.of(16, 0))));
				break;
			case 3:
				weekSchedule.add(new DailySchedule(LocalDateTime.of(LocalDate.of(2020, 6, 4), LocalTime.of(8, 0)),LocalDateTime.of(LocalDate.of(2020, 6, 4), LocalTime.of(16, 0))));
				break;
			case 4:
				weekSchedule.add(new DailySchedule(LocalDateTime.of(LocalDate.of(2020, 6, 5), LocalTime.of(8, 0)),LocalDateTime.of(LocalDate.of(2020, 6, 5), LocalTime.of(16, 0))));
				break;
			case 5:
				weekSchedule.add(new DailySchedule(LocalDateTime.of(LocalDate.of(2020, 6, 6), LocalTime.of(8, 0)),LocalDateTime.of(LocalDate.of(2020, 6, 6), LocalTime.of(16, 0))));
				break;
			case 6:
				weekSchedule.add(new DailySchedule(LocalDateTime.of(LocalDate.of(2020, 6, 7), LocalTime.of(8, 0)),LocalDateTime.of(LocalDate.of(2020, 6, 7), LocalTime.of(16, 0))));
				break;

			default:
				break;
			}
			
			
		}
		
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
