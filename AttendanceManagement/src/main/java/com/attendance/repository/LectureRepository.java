package com.attendance.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.entity.Lecture;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {
    public Lecture findByLectureId(Integer lectureId);
    public List<Lecture> findByLectureDate(Date lectureDate);
}
