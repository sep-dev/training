package com.attendance.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the lectures database table.
 *
 */
@Entity
@Table(name="lectures")
@NamedQuery(name="Lecture.findAll", query="SELECT l FROM Lecture l")
public class Lecture implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="lecture_id")
    private Integer lectureId;

    @Temporal(TemporalType.DATE)
    @Column(name="lecture_date")
    private Date lectureDate;

    @Column(name="lecture_hour")
    private Integer lectureHour;

    //bi-directional many-to-one association to Lesson
    @ManyToOne
    @JoinColumn(name="lesson_id")
    private Lesson lesson;

    public Lecture() {
    }

    public Integer getLectureId() {
        return this.lectureId;
    }

    public void setLectureId(Integer lectureId) {
        this.lectureId = lectureId;
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

    public Lesson getLesson() {
        return this.lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

}