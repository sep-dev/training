package com.attendance.service;

import com.attendance.entity.Teacher;

public interface LoginService {

	public boolean isStudent(Integer studentId, String password);

	public boolean isManager(Integer managerId, String password);

	public boolean teacherUpdate(Teacher updateTeacher);
}
