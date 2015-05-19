package com.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.repository.StudentRepository;
import com.attendance.repository.TeacherRepository;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepository studentRepository;

    //存在する生徒であり、パスワードが一致するか
    @Override
    public boolean isStudent(Integer studentId, String password) {
        if(!studentRepository.exists(studentId)) return false;
        return password.equals(studentRepository.findByStudentId(studentId).getStudentPassword());
    }

    //存在する管理者であり、パスワードが一致するか
    @Override
    public boolean isManager(Integer managerId, String password) {
        if(!teacherRepository.exists(managerId)) return false;
        return password.equals(teacherRepository.findByTeacherId(managerId).getTeacherPassword());
    }

}
