package com.attendance.form;

import java.util.Date;

public class AttendForm {
	private Integer studentId;
	private Integer lectureId;
	private String lessonName;
	private String studentName;
	private Date lectureDate;
	private Integer lectureHour;

	public Integer getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getLectureId() {
		return this.lectureId;
	}

	public void setLectureId(Integer lectureId) {
		this.lectureId = lectureId;
	}

	public String getLessonName() {
		return this.lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {

		this.studentName = studentName;
	}

	public Date getLectureDate() {
		return this.lectureDate;
	}

	public void setLectureDate(Date lectureDate) {

		this.lectureDate = lectureDate;
	}

	public Integer getLectureHour() {
		return this.lectureHour;
	}

	public void setLectureHour(Integer lectureHour) {
		this.lectureHour = lectureHour;
	}

}
