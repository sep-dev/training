package com.attendance.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.attendance.domain.AccessUser;
import com.attendance.entity.Lecture;
import com.attendance.repository.LectureRepository;
import com.attendance.service.SerchLecture;
/**
 * 講義削除のコントローラ
 */
@Controller
@SessionAttributes("accessUser")
@RequestMapping(value = "/manager")
public class LectureDeleteController extends AccessController{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private LectureRepository repository;
    @Autowired
    private SerchLecture search;

    @RequestMapping(value = "/lectureDelete", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String deleteConfirm(HttpServletRequest request, Model model,AccessUser user) {
        /*管理者かどうかの判定*/
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        /*対象データの取り出し*/
        int id = Integer.parseInt(request.getParameter("id"));
        model.addAttribute("message", "本当に削除しますか？");
        Lecture lecture = repository.findOne(id);
        model.addAttribute("id", id);
        model.addAttribute("lecture", lecture);
        model.addAttribute("datalist", repository.findByLectureId(id));
        return "/lectureDelete";
    }

    @RequestMapping(value = "/lectureDelete", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String deleteData(Model model, HttpServletRequest request,AccessUser user) {
        /*管理者かどうかの判定*/
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        /*データの削除*/
        Lecture lecture = repository.findOne(Integer.parseInt(request.getParameter("id")));
        repository.delete(lecture);
        model.addAttribute("datalist", search.getAll());
        return "/lectureList";
    }
}