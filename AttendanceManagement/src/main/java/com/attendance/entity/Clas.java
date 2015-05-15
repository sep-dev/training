package com.attendance.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the clases database table.
 *
 */
@Entity
@Table(name="clases")
@NamedQuery(name="Clas.findAll", query="SELECT c FROM Clas c")
public class Clas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="class_id")
	private int classId;

	@Column(name="class_name")
	private String className;

	public Clas() {
	}

	public int getClassId() {
		return this.classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}