package com.attendance.form;


public class AttendForm {
	private Integer studentId;
	private Integer lessonId;
	private String lessonName;
	private String studentName;
	private String lectureDate;
	private Integer lectureHour;

	public Integer getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getLessonId() {
		return this.lessonId;
	}

	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
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

	public String getLectureDate() {
		return this.lectureDate;
	}

	public void setLectureDate(String lectureDate) {
		lectureDate.indexOf(11);
		this.lectureDate = lectureDate;
	}

	public Integer getLectureHour() {
		return this.lectureHour;
	}

	public void setLectureHour(Integer lectureHour) {
		this.lectureHour = lectureHour;
	}

}
