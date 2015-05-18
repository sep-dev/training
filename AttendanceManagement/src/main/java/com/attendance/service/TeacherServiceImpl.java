package com.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.entity.Teacher;
import com.attendance.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher teacherFindByTeacherId(Integer teacherID) {
        return teacherRepository.findByTeacherId(teacherID);
    }

}
