package com.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.attendance.entity.Clas;
import com.attendance.repository.ClassRepository;

@Controller
public class ClassUpdateController {

	 @Autowired
     private ClassRepository repository;
	 @RequestMapping(value = "/classUpdate", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
	    public String helo(HttpServletRequest request,Model model) {
		    int id=Integer.parseInt(request.getParameter("id"));

	        model.addAttribute("title","クラス編集画面");
	        model.addAttribute("message","クラス情報の編集が可能");
	        Clas clas = repository.findOne(id);
	        model.addAttribute("class",clas);
	        return "/classUpdate";
	    }

	   @RequestMapping(value = "/classUpdate", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	    public String repo(@Valid @ModelAttribute Clas data ,Errors result,Model model) {
	    	if(result.hasErrors()){
	            model.addAttribute("title","エラー画面");
	            model.addAttribute("message","エラーが発生しました");
	            return "/classUpdate";
	    	}else{
	            repository.saveAndFlush(data);
	            model.addAttribute("title","クラス管理画面");
		        model.addAttribute("message","クラス一覧から目的のクラスを検索し、編集・削除等が可能");
		        model.addAttribute("myData",data);
		        List<Clas> list = repository.findAll();
		        model.addAttribute("datalist",list);
	            return "/classList";
	    	}
	    }
}
