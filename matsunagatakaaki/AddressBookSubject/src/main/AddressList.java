package main;

import helper.DatabaseHelper;
import helper.HtmlHelper;
import helper.Urls;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddressList
 */
@WebServlet("/AddressList")
public class AddressList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseHelper db;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddressList() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(Urls.TOP); //強制遷移
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding(HtmlHelper.ENCORDING);
        response.setContentType("text/html; charset=" + HtmlHelper.ENCORDING);
        PrintWriter out = response.getWriter();

        HtmlHelper.firstHttp(out, "会員一覧", "./css/list.css");

        out.println("   <h1 class=\"title\">会員一覧</h1>");

        db = new DatabaseHelper();
        if(db.excuteSQL(DatabaseHelper.Query.SELECT, null, null, null, null)){
            ArrayList<HashMap<String,String>> list = db.getResultHashMap();

            if(list == null || list.size() == 0){
                out.println("<p>データが１件もありません</p>");
            }else{
                out.println("<form method=\"POST\" action=\"./Update\">");
                out.println("<table");

                for(HashMap<String,String> row : list){
                    out.println("<tr>");
                    out.println("<td><input type=\"radio\" name=\"id\" value=\"" + row.get(DatabaseHelper.Columns.ID) + "\" required /></td>");
                    out.println("<td class=\"column\">氏名  ： </td>");
                    out.println("<td class=\"name_td\">" + row.get(DatabaseHelper.Columns.NAME) + "</td>");
                    out.println("<td class=\"column\">住所  : </td>");
                    out.println("<td class=\"address_td\">" + row.get(DatabaseHelper.Columns.ADDRESS) + "</td>");
                    out.println("<td class=\"column\">電話番号  : </td>");
                    out.println("<td class=\"tel_td\">" + row.get(DatabaseHelper.Columns.TEL) + "</td>");
                    out.println("</tr>");
                }

                out.println("</table>");
                out.println("<p><input type=\"submit\" value=\"更新 or 削除\" /></p>");
                out.println("</form>");
            }
        }else{
            out.println("<p>エラーが発生しました</p>");
        }

        out.println("<form action=\"" + Urls.TOP + "\">");
        out.println("<p><input type=\"submit\" value=\"新規登録\" /></p>");
        out.println("</form>");
        HtmlHelper.endHttp(out);
    }
}
