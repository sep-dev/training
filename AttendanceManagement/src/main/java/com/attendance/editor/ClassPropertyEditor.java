package com.attendance.editor;

import java.beans.PropertyEditorSupport;

import com.attendance.entity.Clas;
import com.attendance.repository.ClassRepository;

public class ClassPropertyEditor extends PropertyEditorSupport {
	ClassRepository repository;

	public ClassPropertyEditor(ClassRepository repository) {
		this.repository = repository;
	}

	public String getAsText() {
		Clas value = (Clas) getValue();
		System.out.println("getAsText: " + value);
		if (value == null) {
			return null;
		} else {
			return "" + value.getClassId();
		}
	}

	public void setAsText(String value) {
		System.out.println("value:" + value);
		System.out.println(repository);
		Clas clas = repository.findByClassId(Integer.parseInt(value));
		System.out.println("setAsText:" + clas);
		setValue(clas);
	}
}
