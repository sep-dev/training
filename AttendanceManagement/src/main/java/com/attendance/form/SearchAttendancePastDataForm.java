package com.attendance.form;

import java.io.Serializable;

public class SearchAttendancePastDataForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String startDate;
	private String endDate;
	private String lesson_id;
	private String hour;

	public SearchAttendancePastDataForm() {
		startDate = endDate = "";
		lesson_id = hour = "0";
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getLesson_id() {
		return lesson_id;
	}

	public void setLesson_id(String lesson_id) {
		this.lesson_id = lesson_id;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

}
