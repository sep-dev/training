package com.attendance.service;

import java.util.List;

import com.attendance.entity.HourMst;

public interface HourMstService {
    HourMst findByHourId(Integer hourId);
    List<HourMst> findAll();
}
