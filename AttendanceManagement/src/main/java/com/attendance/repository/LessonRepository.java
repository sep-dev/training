package com.attendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
	Lesson findByLessonId(Integer lessonId);

	List<Lesson> findByLessonNameLike(String lessonName);
}
