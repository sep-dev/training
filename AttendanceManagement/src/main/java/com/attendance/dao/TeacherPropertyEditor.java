package com.attendance.dao;

import java.beans.PropertyEditorSupport;

import com.attendance.entity.Teacher;
import com.attendance.repository.TeacherRepository;

public class TeacherPropertyEditor extends PropertyEditorSupport {
	TeacherRepository repository;

	public TeacherPropertyEditor(TeacherRepository repository) {
		this.repository = repository;
	}

	public String getAsText() {
		Teacher value = (Teacher) getValue();
		System.out.println("getAsText: " + value);
		if (value == null) {
			return null;
		} else {
			return "" + value.getTeacherId();
		}
	}

	public void setAsText(String value) {
		System.out.println("value:" + value);
		System.out.println(repository);
		Teacher teacher = repository.findByTeacherId(Integer.parseInt(value));
		System.out.println("setAsText:" + teacher);
		setValue(teacher);
	}
}
