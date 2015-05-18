package com.attendance.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the teachers database table.
 *
 */
@Entity
@Table(name = "teachers")
@NamedQuery(name = "Teacher.findAll", query = "SELECT t FROM Teacher t")
public class Teacher implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	@Id
	@Column(name = "teacher_id")
	private Integer teacherId;
	@NotEmpty
	@Column(name = "teacher_address")
	private String teacherAddress;
	@NotEmpty
	@Column(name = "teacher_name")
	private String teacherName;
	@NotEmpty
	@Column(name = "teacher_password")
	private String teacherPassword;
	@NotEmpty
	@Column(name = "teacher_tel")
	private String teacherTel;

	// bi-directional many-to-one association to Clas
	@ManyToOne
	@JoinColumn(name = "class_id")
	private Clas clas;

	public Teacher() {
	}

	public Integer getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public Clas getClas() {
		return this.clas;
	}

	public void setClas(Clas clas) {
		this.clas = clas;
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

	public void setTeacherPassword(String teacherPassword) {
		this.teacherPassword = teacherPassword;
	}

	public String getTeacherTel() {
		return this.teacherTel;
	}

	public void setTeacherTel(String teacherTel) {
		this.teacherTel = teacherTel;
	}

}