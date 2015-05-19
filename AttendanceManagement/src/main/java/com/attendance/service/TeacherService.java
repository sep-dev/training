package com.attendance.service;

import com.attendance.entity.Teacher;
import com.attendance.form.TeacherAddForm;

public interface TeacherService {

    Teacher teacherFindByTeacherId(Integer teacherID);
    Teacher updateTeacher(Teacher teacher);
    Teacher updateTeacher(TeacherAddForm form);
}
