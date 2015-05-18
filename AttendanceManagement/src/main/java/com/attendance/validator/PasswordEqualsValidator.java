package com.attendance.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.attendance.form.PasswordEditForm;

@Component
public class PasswordEqualsValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PasswordEditForm.class.isAssignableFrom(clazz); //チェック対象であるか判定
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordEditForm form = (PasswordEditForm)target;

        if(form.getPassword().equals(form.getNewPassword())){
            errors.rejectValue("newPassword",null,"現在のパスワードと、新しいパスワードが一緒です");
        }

        if(!form.getNewPassword().equals(form.getNewPasswordConfirm())){
            errors.rejectValue("newPasswordConfirm",null,"新しいパスワードと異なります");
        }
    }

}
