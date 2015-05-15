package com.attendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    public Student findByStudentId(Integer studentId);

	public List<Student> findByStudentNameLike(String studentName);
	public List<Student> findByStudentNameLikeOrStudentAddressLike(String studentName,String studentAddress);
}
