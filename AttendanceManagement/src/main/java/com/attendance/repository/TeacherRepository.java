package com.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    public Teacher findByTeacherId(Integer teacherID);
}
