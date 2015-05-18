package com.attendance.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the classes database table.
 * 
 */
@Entity
@Table(name="classes")
@NamedQuery(name="Class.findAll", query="SELECT c FROM Class c")
public class Class implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="class_id")
	private int classId;

	@Column(name="class_name")
	private String className;

	public Class() {
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