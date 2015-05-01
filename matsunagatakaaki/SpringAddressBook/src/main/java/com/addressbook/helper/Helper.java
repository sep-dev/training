package com.addressbook.helper;

import org.springframework.web.servlet.ModelAndView;

public class Helper {

    //ModelAndViewインスタンス取得
    public static ModelAndView getMmodelAndView(String viewName){
        return new ModelAndView(viewName);
    }
}
