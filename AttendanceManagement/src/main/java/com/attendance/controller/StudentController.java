package com.attendance.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.attendance.entity.LectureAttendancePK;
import com.attendance.helper.Helper;
import com.attendance.service.StudentService;

@Controller
@RequestMapping(value="/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    private ModelAndView mav;

    @RequestMapping(value="lectureList")
    public ModelAndView lectureList(){
        mav = new ModelAndView("lectureList");
        Integer studentId = 0;
        mav.addObject("lectureList", studentService.getTodayLectureList(studentId)); //一時的に0を入れているだけ
        LectureAttendancePK lap = new LectureAttendancePK();
        lap.setStudentId(studentId);
        mav.addObject("lecture_attendancePK",lap);
        mav.addObject("date", Helper.formatJapaneseDate(new Date()));
        return mav;
    }
}
