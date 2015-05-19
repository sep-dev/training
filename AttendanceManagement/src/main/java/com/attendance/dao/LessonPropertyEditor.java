package com.attendance.dao;

import java.beans.PropertyEditorSupport;

import com.attendance.entity.Lesson;
import com.attendance.repository.LessonRepository;

public class LessonPropertyEditor extends PropertyEditorSupport {
	LessonRepository repository;

	public LessonPropertyEditor(LessonRepository repository) {
		this.repository = repository;
	}

	public String getAsText() {
		Lesson value = (Lesson) getValue();
		System.out.println("getAsText: " + value);
		if (value == null) {
			return null;
		} else {
			return "" + value.getLessonId();
		}
	}

	public void setAsText(String value) {
		System.out.println("value:" + value);
		System.out.println(repository);
		Lesson lesson = repository.findByLessonId(Integer.parseInt(value));
		System.out.println("setAsText:" + lesson);
		setValue(lesson);
	}
}
