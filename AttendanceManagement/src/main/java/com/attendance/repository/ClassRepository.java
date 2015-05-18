package com.attendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.entity.Clas;

@Repository
public interface ClassRepository extends JpaRepository<Clas, Integer> {

	public Clas findByClassId(Integer classId);

	public List<Clas> findByClassNameLike(String className);
}
