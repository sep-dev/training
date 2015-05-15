package com.attendance.form;

import java.util.Date;

public class AttendForm {
	private Integer studentId;
	private String lectureName;
	private String studentName;
	private Date lectureDate;
	private Integer lectureHour;

	public Integer getStudentId(){
		return this.studentId;
    }
	public void setStudentId(Integer studentId){
		this.studentId=studentId;
	}

	public String getLectureName(){
		return this.lectureName;
    }
	public void setLectureName(String lectureName){
		this.lectureName=lectureName;
	}

	public String getStudentName(){
		return this.studentName;
    }
	public void setStudentName(String studentName){
		this.studentName=studentName;
	}

	public Date getLectureDate(){
		return this.lectureDate;
    }
	public void setLectureDate(Date lectureDate){
		this.lectureDate=lectureDate;
	}

	public Integer getLectureHour(){
		return this.lectureHour;
    }
	public void setLectureHour(Integer lectureHour){
		this.lectureHour=lectureHour;
	}

}
