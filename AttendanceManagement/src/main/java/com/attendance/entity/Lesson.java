package com.attendance.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the lessons database table.
 *
 */
@Entity
@Table(name = "lessons")
@NamedQuery(name = "Lesson.findAll", query = "SELECT l FROM Lesson l")
public class Lesson implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	@Id
	@Column(name = "lesson_id")
	private Integer lessonId;
	@NotEmpty
	@Column(name = "lesson_name")
	private String lessonName;

	// bi-directional many-to-one association to Lecture
	@OneToMany(mappedBy = "lesson")
	private List<Lecture> lectures;

	// bi-directional many-to-one association to Teacher
	@ManyToOne
	@JoinColumn(name = "lesson_teacher_id")
	private Teacher teacher;

	public Lesson() {
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

	public List<Lecture> getLectures() {
		return this.lectures;
	}

	public void setLectures(List<Lecture> lectures) {
		this.lectures = lectures;
	}

	public Lecture addLecture(Lecture lecture) {
		getLectures().add(lecture);
		lecture.setLesson(this);

		return lecture;
	}

	public Lecture removeLecture(Lecture lecture) {
		getLectures().remove(lecture);
		lecture.setLesson(null);

		return lecture;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}