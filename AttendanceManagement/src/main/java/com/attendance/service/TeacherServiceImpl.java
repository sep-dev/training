package com.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.entity.Teacher;
import com.attendance.form.TeacherAddForm;
import com.attendance.helper.ShareHelper;
import com.attendance.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher teacherFindByTeacherId(Integer teacherID) {
        return teacherRepository.findByTeacherId(teacherID);
    }

    @Override
    public Teacher updateTeacher(TeacherAddForm form) {
        Teacher teacher = teacherRepository.findByTeacherId(form.getTeacherId());
        teacher.setTeacherName(form.getTeacherName());
        teacher.setTeacherAddress(form.getTeacherAddress());
        teacher.setTeacherTel(form.getTeacherTel());
        teacher.setTeacherPassword(ShareHelper.hashingMd5(form.getPasswordConfirm()));
        return updateTeacher(teacher);
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.saveAndFlush(teacher);
    }

}
