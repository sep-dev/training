package com.attendance.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;


/**
 * The persistent class for the hour_mst database table.
 * 
 */
@Entity
@Table(name="hour_mst")
@NamedQuery(name="HourMst.findAll", query="SELECT h FROM HourMst h")
public class HourMst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="hour_id")
	private int hourId;

	@Column(name="end_time")
	private Time endTime;

	@Column(name="start_time")
	private Time startTime;

	public HourMst() {
	}

	public int getHourId() {
		return this.hourId;
	}

	public void setHourId(int hourId) {
		this.hourId = hourId;
	}

	public Time getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Time getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

}