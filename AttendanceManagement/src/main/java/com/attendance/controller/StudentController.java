package com.attendance.controller;

import java.util.Date;

import javax.inject.Inject;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.attendance.entity.Student;
import com.attendance.repository.StudentRepository;
import com.attendance.entity.LectureAttendancePK;
import com.attendance.form.PasswordEditForm;
import com.attendance.form.SearchAttendancePastDataForm;
import com.attendance.helper.ShareHelper;
import com.attendance.service.HourMstService;
import com.attendance.service.LectureService;
import com.attendance.service.LessonService;
import com.attendance.service.StudentService;
import com.attendance.validator.PasswordEqualsValidator;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private LectureService lectureService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private HourMstService hourService;
    @Autowired
    private StudentRepository repository;

    @Inject
    private PasswordEqualsValidator passwordEqualsValidator;

    private ModelAndView mav;

    private Integer studentId = new Integer(0);

    @RequestMapping(value="student/lectureList")
    public ModelAndView lectureList(){
        mav = new ModelAndView("lectureList");
        mav.addObject("lectureList", studentService.getTodayLectureList(studentId)); //一時的に0を入れているだけ
        LectureAttendancePK lap = new LectureAttendancePK();
        lap.setStudentId(studentId); //ログインしている生徒のIDをセット
        mav.addObject("lecture_attendancePK",lap);
        mav.addObject("date", ShareHelper.getToday());
        return mav;
    }

    @RequestMapping(value="student/attendanceAdd",params="confirm")
    public String attendanceAddConfirm(LectureAttendancePK lectureAttendancePK,Model model){
        model.addAttribute("today", ShareHelper.getToday());
        model.addAttribute(lectureService.findByLectureId(lectureAttendancePK.getLectureId()));
        model.addAttribute("id",lectureAttendancePK);
        return "attendanceAddConfirm";
    }

    @RequestMapping(value="student/attendanceAdd",params="add")
    public String attendanceAdd(LectureAttendancePK id,Model model){
        if(!studentService.addLectureAtttendance(id)) return "redirect:lectureList"; //失敗画面へ
        model.addAttribute("title","出席登録完了");
        model.addAttribute("message","出席登録が完了しました");
        return "commonSuccess";
    }

    @RequestMapping(value="student/studentPasswordEdit",params="input")
    public String studentPasswordEdit(Model model){
        model.addAttribute(new PasswordEditForm(studentId));
        return "studentPassEdit";
    }

    @RequestMapping(value="student/studentPasswordEdit", params="excute",method=RequestMethod.POST)
    public String studentPasswordUpdate(@Validated PasswordEditForm editForm,BindingResult result,Model model){
        if(result.hasErrors()) return "studentPassEdit";
        boolean updateResult = studentService.updateStudentPassword(editForm.getStudentId(), editForm.getNewPassword());
        if(updateResult){
            model.addAttribute("title","パスワード変更完了");
            model.addAttribute("message", "パスワード変更完了");
        }else{
            model.addAttribute("errorMsg", "変更できませんでした");
        }
        return updateResult ? "commonSuccess" : "studentPassEdit";
    }

    @InitBinder("passwordEditForm")
    public void initBinder(WebDataBinder binder){
        binder.addValidators(passwordEqualsValidator);
    }

    //ログインしている生徒の、過去の出席情報を一覧表示する
    @RequestMapping(value="student/search",method=RequestMethod.POST)
    public String attendanceDataSearch(@ModelAttribute SearchAttendancePastDataForm sapd,Model model){
        model.addAttribute("lessonList", studentService.getSearchLessonMap());
        model.addAttribute("hourList",studentService.getSearcHourMap());

        if(sapd.getStartDate().equals("")) sapd.setStartDate(studentService.getMinDate(studentId));
        if(sapd.getEndDate().equals("")) sapd.setEndDate(ShareHelper.formatHyphenDate(new Date()));

        model.addAttribute(sapd); //検索条件をそのまま渡す
        model.addAttribute("pastDataList",studentService.getAttendancePastData(studentId, sapd));

        return "pastDataList";
    }

    @RequestMapping(value="student/search",params="download",method=RequestMethod.POST)
    public String downloadCSV(SearchAttendancePastDataForm sapd,Model model){
        model.addAttribute("fileName", "test.csv");
        model.addAttribute("studentName", studentService.studentFindByStudentId(studentId).getStudentName());
        model.addAttribute("createDate", ShareHelper.formatJapaneseDate(new Date()));
        model.addAttribute("startDate", sapd.getStartDate());
        model.addAttribute("endDate", sapd.getStartDate());
        model.addAttribute("lessonName", studentService.getSearchLessonMap().get(sapd.getLesson_id()));
        model.addAttribute("hour", studentService.getSearcHourMap().get(sapd.getHour()));
        model.addAttribute("pastDateList",studentService.getAttendancePastData(studentId, sapd));
        return "attendancePastDataDownload";
    }
     
    @RequestMapping(value = "/studentList", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
    public String helo(Model model) {
        Student data=new Student();
        model.addAttribute("title","生徒管理画面");
        model.addAttribute("message","生徒一覧から目的の生徒を検索し、編集・削除等が可能");
        model.addAttribute("myData",data);
        List<Student> list = repository.findAll();
        model.addAttribute("datalist",list);
        return "/studentList";
    }

    @RequestMapping(value = "/studentList", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
    public String search(HttpServletRequest request,Model model) {
        String param=request.getParameter("fstr");
        System.out.println(param);
        model.addAttribute("title","検索");
        model.addAttribute("message","「"+param+"」の"+"検索結果");
        //名前・住所であいまい検索
        List<Student> list = repository.findByStudentNameLikeOrStudentAddressLike("%"+param+"%","%"+param+"%");
        model.addAttribute("datalist",list);
        return "/studentList";
    }
}

