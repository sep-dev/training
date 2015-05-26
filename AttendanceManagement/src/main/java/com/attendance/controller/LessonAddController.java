package com.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.attendance.domain.AccessUser;
import com.attendance.editor.TeacherPropertyEditor;
import com.attendance.entity.Lesson;
import com.attendance.entity.Teacher;
import com.attendance.repository.LessonRepository;
import com.attendance.repository.TeacherRepository;
/**
 * 科目追加のコントローラ
 */
@Controller
@SessionAttributes("accessUser")
@RequestMapping(value = "/manager")
public class LessonAddController extends AccessController{
    @Autowired
    private TeacherRepository teacher_repository;
    @Autowired
    private LessonRepository repository;


    @RequestMapping(value = "/lessonAdd", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String newEntry(Model model,AccessUser user) {
        /*管理者かどうかの判定*/
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        Lesson lesson = new Lesson();
        model.addAttribute("lesson", lesson);
        createList(model);
        return "/lessonAdd";
    }

    @RequestMapping(value = "/lessonAdd", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String addData(@Valid @ModelAttribute Lesson data, Errors result,Model model,AccessUser user) {
        /*管理者かどうかの判定*/
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        if (isError(result, data, model)){
            createList(model);
            return "/lessonAdd";
        } else {
            repository.saveAndFlush(data);
            model.addAttribute("myData", data);
            List<Lesson> list = repository.findAll();
            model.addAttribute("datalist", list);
            return "/lessonList";
        }
    }

    /*型変換用*/
    @InitBinder
    protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Teacher.class, new TeacherPropertyEditor(teacher_repository));
    }

    /*検索用リストの生成*/
    private void createList(Model model){
        List<Teacher> teacher_list = teacher_repository.findAll();
        model.addAttribute("selectTeacher", teacher_list);
    }

    /*入力文字チェック*/
    private boolean isError(Errors result,Lesson data,Model model){
         if (result.hasErrors()) {
             model.addAttribute("message", "エラーが発生しました");
             return true;
         } else if (repository.findByLessonId(data.getLessonId()) != null) {
             model.addAttribute("message", "IDが重複しています");
             return true;
         } else {
             return false;
         }
    }
}
