package com.attendance.search;

import java.io.Serializable;

//検索欄用時限
public class SearchHour implements Serializable {

	private Integer hour;
	private String hourName;

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public String getHourName() {
		return hourName;
	}

	public void setHourName(String hourName) {
		this.hourName = hourName;
	}
}
