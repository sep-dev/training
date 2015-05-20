package com.attendance.form;


public class LectureForm {
	private Integer lectureId;
	private Integer lessonId;
	private Integer teacherId;
	private String lessonName;
	private String teacherName;
	private String lectureDate;
	private Integer lectureHour;

	public Integer getLectureId() {
		return this.lectureId;
	}

	public void setLectureId(Integer lectureId) {
		this.lectureId = lectureId;
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

	public Integer getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return this.teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

}