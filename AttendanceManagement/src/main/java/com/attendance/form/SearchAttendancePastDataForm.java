package com.attendance.form;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchAttendancePastDataForm implements Serializable{

    private static final long serialVersionUID = 1L;

    private String startDate;
    private String endDate;
    private String lesson_id;
    private String hour;

    public SearchAttendancePastDataForm(){
        startDate = endDate = "";
        lesson_id = hour = "0";
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        if(!isDate(startDate)) this.startDate = "";
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        if(!isDate(endDate)) this.endDate = "";
        this.endDate = endDate;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    //日付比較を行い、左右の大小関係が逆なら入れ替える
    public void dateCompare(){
        if(startDate.equals("") || endDate.equals("")) return;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try{
            Date start = format.parse(startDate);
            Date end = format.parse(endDate);
            if(!start.before(end)){
                String work = startDate;
                startDate = endDate;
                endDate = work;
            }
        }catch(ParseException e){}
    }

    //日付に変換可能かチェック
    private boolean isDate(String date){
        date = date.replaceAll("[/,.]", "-"); //置換
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try{
            format.parse(date);
            return true;
        }catch(Exception e){
            return false;
        }
    }

}
