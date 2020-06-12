package com.hums.humanResourceManagementSystem;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DailySchedule implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private LocalDateTime start;
	private LocalDateTime end;
	
	
	
	public DailySchedule(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.end = end;
	}

	public String getDay() {
		return start.getDayOfWeek().toString();
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
	
}
