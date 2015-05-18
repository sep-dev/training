package com.attendance.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

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

    public LectureAttendance(LectureAttendancePK id){
        this.id = id;
    }

    public LectureAttendancePK getId() {
        return this.id;
    }

    public void setId(LectureAttendancePK id) {
        this.id = id;
    }

}