package com.attendance.service;

import java.util.List;

import com.attendance.entity.Lesson;

public interface LessonService {

	List<Lesson> findAll();

	Lesson findByLessonId(Integer lessonId);
}
