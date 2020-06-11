package com.hums.humanResourceManagementSystem;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

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

	public ArrayList<DailySchedule> getWeekSchedule() {
		return weekSchedule;
	}

	public void setWeekSchedule(ArrayList<DailySchedule> weekSchedule) {
		this.weekSchedule = weekSchedule;
	}
	
	
}
