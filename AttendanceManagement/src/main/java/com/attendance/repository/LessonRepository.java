package com.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    Lesson findByLessonId(Integer lessonId);
}
