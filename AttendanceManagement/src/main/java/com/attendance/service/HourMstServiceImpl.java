package com.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.entity.HourMst;
import com.attendance.repository.HourMstRepository;

@Service
public class HourMstServiceImpl implements HourMstService {

	@Autowired
	private HourMstRepository hourRepository;

	@Override
	public HourMst findByHourId(Integer hourId) {
		return hourRepository.findByHourId(hourId);
	}

	@Override
	public List<HourMst> findAll() {
		return hourRepository.findAll();
	}

}
