package com.attendance.service;


public interface LoginService {

    public boolean isStudent(Integer studentId,String password);
    public boolean isManager(Integer managerId,String password);
}
