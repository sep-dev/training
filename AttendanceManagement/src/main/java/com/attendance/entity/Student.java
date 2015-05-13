package com.attendance.entity;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the students database table.
 *
 */
@Entity
@Table(name="students")
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name="student_id")
    private Integer studentId;
    @NotNull
    @Column(name="class_id")
    private Integer classId;
    @NotEmpty
    @Column(name="student_address")
    private String studentAddress;
    @NotEmpty
    @Column(name="student_name")
    private String studentName;
    @NotEmpty
    @Column(name="student_password")
    private String studentPassword;
    @NotEmpty
    @Column(name="student_tel")
    private String studentTel;

    public Student() {
    }

    public Integer getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getClassId() {
        return this.classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getStudentAddress() {
        return this.studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPassword() {
        return this.studentPassword;
    }

    public void setStudentPassword(String studentPassword) throws NoSuchAlgorithmException {
    	this.studentPassword=studentPassword;
    }

	public String getStudentTel() {
        return this.studentTel;
    }

    public void setStudentTel(String studentTel) {
        this.studentTel = studentTel;
    }


}