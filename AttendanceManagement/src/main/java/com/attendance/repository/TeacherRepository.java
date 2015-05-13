package com.attendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

	 public Teacher findByTeacherId(Integer teacherId);

		public List<Teacher> findByTeacherNameLike(String teacherName);
		public List<Teacher> findByTeacherNameLikeOrTeacherAddressLike(String teacherName,String teacherAddress);
}
