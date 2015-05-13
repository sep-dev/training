package com.attendance.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the lecture_attendance database table.
 * 
 */
@Entity
@Table(name="lecture_attendance")
@NamedQuery(name="LectureAttendance.findAll", query="SELECT l FROM LectureAttendance l")
public class LectureAttendance implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LectureAttendancePK id;

	public LectureAttendance() {
	}

	public LectureAttendancePK getId() {
		return this.id;
	}

	public void setId(LectureAttendancePK id) {
		this.id = id;
	}

}