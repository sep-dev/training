package com.attendance.entity;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


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
	private Integer hourId;

	@Column(name="end_time")
	private Time endTime;

	@Column(name="start_time")
	private Time startTime;

	public HourMst() {
	}

	public Integer getHourId() {
		return this.hourId;
	}

	public void setHourId(Integer hourId) {
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