package com.attendance.entity;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the teachers database table.
 *
 */
@Entity
@Table(name="teachers")
@NamedQuery(name="Teacher.findAll", query="SELECT t FROM Teacher t")
public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name="teacher_id")
    private Integer teacherId;
    @NotNull
    @Column(name="class_id")
    private Integer classId;
    @NotEmpty
    @Column(name="teacher_address")
    private String teacherAddress;
    @NotEmpty
    @Column(name="teacher_name")
    private String teacherName;
    @NotEmpty
    @Column(name="teacher_password")
    private String teacherPassword;
    @NotEmpty
    @Column(name="teacher_tel")
    private String teacherTel;

    //bi-directional many-to-one association to Lesson
    @OneToMany(mappedBy="teacher")
    private List<Lesson> lessons;

    public Teacher() {
    }

    public Integer getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getClassId() {
        return this.classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getTeacherAddress() {
        return this.teacherAddress;
    }

    public void setTeacherAddress(String teacherAddress) {
        this.teacherAddress = teacherAddress;
    }

    public String getTeacherName() {
        return this.teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPassword() {
        return this.teacherPassword;
    }
    public void setTeacherPassword(String teacherPassword) throws NoSuchAlgorithmException {
    	this.teacherPassword=teacherPassword;
    }


    public String getTeacherTel() {
        return this.teacherTel;
    }

    public void setTeacherTel(String teacherTel) {
        this.teacherTel = teacherTel;
    }

    public List<Lesson> getLessons() {
        return this.lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Lesson addLesson(Lesson lesson) {
        getLessons().add(lesson);
        lesson.setTeacher(this);

        return lesson;
    }

    public Lesson removeLesson(Lesson lesson) {
        getLessons().remove(lesson);
        lesson.setTeacher(null);

        return lesson;
    }

}