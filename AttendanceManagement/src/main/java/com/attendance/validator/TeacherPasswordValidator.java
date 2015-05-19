package com.attendance.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.attendance.form.TeacherAddForm;
import com.attendance.repository.TeacherRepository;

@Component
public class TeacherPasswordValidator implements Validator{

    @Autowired
    private TeacherRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return TeacherAddForm.class.isAssignableFrom(clazz); //Teacherクラスであるか
    }

    @Override
    public void validate(Object target, Errors errors) {
        TeacherAddForm form = (TeacherAddForm)target;

        if(!form.getPassword().equals(form.getPasswordConfirm())){
            errors.rejectValue("passwordConfirm", null, "上記のパスワードと異なっています");
        }

        if(repository.findByTeacherId(form.getTeacherId()) == null){
            errors.rejectValue("teacherId", null, "許可されてないIDです");
        }
    }

}
