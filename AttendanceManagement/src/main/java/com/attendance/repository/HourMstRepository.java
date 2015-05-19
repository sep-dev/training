package com.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.entity.HourMst;

@Repository
public interface HourMstRepository extends JpaRepository<HourMst, Integer> {
	HourMst findByHourId(Integer hourId);
}
