package com.attendance.controller;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.attendance.entity.AttendancePastData;

public class CsvDownloadView extends AbstractView{

    @SuppressWarnings("unchecked")
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String fileName = model.get("fileName").toString();
        fileName = URLEncoder.encode(fileName,"UTF-8");
        response.setCharacterEncoding("Shift-JIS");
        response.setLocale(Locale.JAPAN);
        response.setHeader("Content-Type", "text/csv;charset=Shift-JIS");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try(PrintWriter out = response.getWriter()){

            out.print(model.get("studentName").toString());
            out.print("の過去出席情報,作成日,");
            out.print(model.get("createDate").toString());
            out.print("\r\n\r\n");
            out.print("日付,");
            out.print(model.get("startDate").toString());
            out.print(",~,");
            out.print(model.get("endDate").toString());
            out.print("\r\n");
            out.print("科目,");
            out.print(model.get("lessonName").toString());
            out.print("\r\n");
            out.print("時限,");
            out.print(model.get("hour").toString());
            out.print("\r\n");
            out.print("\r\n");
            out.print("日付,科目名,時限");
            out.print("\r\n");

            for(AttendancePastData pastData : (List<AttendancePastData>)model.get("pastDateList")){
                out.print(pastData.getDate());
                out.print(",");
                out.print(pastData.getLessonName());
                out.print(",");
                out.print(pastData.getHour());
                out.print("\r\n");
            }
        }

    }

}
