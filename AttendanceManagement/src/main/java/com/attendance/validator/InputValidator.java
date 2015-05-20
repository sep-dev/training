package com.attendance.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import com.attendance.repository.ClassRepository;

public class InputValidator {
	@Autowired ClassRepository repository;
	String idErrorMessage;
    //idが未入力の場合
    public String errorId(Integer id,Errors result){
    	if (result.hasErrors()) {
            try {
            	if (id == null) {
                    return idErrorMessage="IDが未入力です";
                }
            } catch ( NumberFormatException e ) {
                System.out.println( "数値に変換できる文字を入力してください" );
            }
            return "/classAdd";
        } else if (repository.findByClassId(id) != null) {
            return idErrorMessage= "IDが重複しています";
        }
        return null;
    }
}
