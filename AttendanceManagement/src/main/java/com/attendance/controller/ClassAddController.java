package com.attendance.controller;

import java.util.List;

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
public class ClassAddController {

	 @Autowired
     private ClassRepository repository;
	 @RequestMapping(value = "/classAdd", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
	    public String helo(Model model) {
	        Clas clas=new Clas();
	        model.addAttribute("title","クラス新規作成画面");
	        model.addAttribute("message","クラス情報の新規作成が可能");
	        model.addAttribute("clas",clas);

	        return "/classAdd";
	    }

	   @RequestMapping(value = "/classAdd", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	    public String repo(@Valid @ModelAttribute Clas data ,Errors result,Model model) {

	    	if(result.hasErrors()){
	    		if(data.getClassId()==null){
	    			model.addAttribute("title","エラー画面");
		            model.addAttribute("message","IDが入力されてません");
	    		}else{
	    			model.addAttribute("title","エラー画面");
		            model.addAttribute("message","エラーが発生しました");
	    		}
	            
	            return "/classAdd";
	    	}else if(repository.findByClassId(data.getClassId())!=null){
	    		model.addAttribute("title","エラー画面");
	            model.addAttribute("message","IDが重複しています");
	    		return "/classAdd";
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
