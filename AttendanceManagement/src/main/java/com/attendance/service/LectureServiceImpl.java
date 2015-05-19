package com.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.entity.Lecture;
import com.attendance.repository.LectureRepository;

@Service
public class LectureServiceImpl implements LectureService {

	@Autowired
	private LectureRepository lectureRepository;

	@Override
	public Lecture findByLectureId(Integer lectureId) {
		return lectureRepository.findByLectureId(lectureId);
	}

}
