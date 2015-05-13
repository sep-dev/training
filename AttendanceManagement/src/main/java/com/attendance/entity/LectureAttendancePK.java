package com.attendance.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the lecture_attendance database table.
 * 
 */
@Embeddable
public class LectureAttendancePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="lecture_id", insertable=false, updatable=false)
	private int lectureId;

	@Column(name="student_id", insertable=false, updatable=false)
	private int studentId;

	public LectureAttendancePK() {
	}
	public int getLectureId() {
		return this.lectureId;
	}
	public void setLectureId(int lectureId) {
		this.lectureId = lectureId;
	}
	public int getStudentId() {
		return this.studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LectureAttendancePK)) {
			return false;
		}
		LectureAttendancePK castOther = (LectureAttendancePK)other;
		return 
			(this.lectureId == castOther.lectureId)
			&& (this.studentId == castOther.studentId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.lectureId;
		hash = hash * prime + this.studentId;
		
		return hash;
	}
}