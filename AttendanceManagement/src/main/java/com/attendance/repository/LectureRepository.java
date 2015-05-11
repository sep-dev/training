package com.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.entity.Lecture;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {
    public Lecture findByLectureId(Integer lectureId);
}
