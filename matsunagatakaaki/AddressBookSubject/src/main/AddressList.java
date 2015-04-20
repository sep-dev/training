package main;

import helper.DatabaseHelper;
import helper.HtmlHelper;
import helper.Urls;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	private Connection conn = null;
	private Statement stmt = null;

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
        response.sendRedirect(Urls.TOP);
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

        try {
            conn = DatabaseHelper.getConnectionInstance();
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME);
            if(!result.next()){
                out.println("<p>データが１件もありません</p>");
            }else{
                out.println("<form method=\"POST\" action=\"./Update\">");
                out.println("<table");
                result.beforeFirst();
                while(result.next()){
                    out.println("<tr>");
                    out.println("<td><input type=\"radio\" name=\"id\" value=\"" + result.getString(DatabaseHelper.ColumnNames.ID) + "\" required /></td>");
                    out.println("<td class=\"column\">氏名  ： </td>");
                    out.println("<td class=\"name_td\">" + result.getString(DatabaseHelper.ColumnNames.NAME) + "</td>");
                    out.println("<td class=\"column\">住所  : </td>");
                    out.println("<td class=\"address_td\">" + result.getString(DatabaseHelper.ColumnNames.ADDRESS) + "</td>");
                    out.println("<td class=\"column\">電話番号  : </td>");
                    out.println("<td class=\"tel_td\">" + result.getString(DatabaseHelper.ColumnNames.TEL) + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("<p><input type=\"submit\" value=\"更新 or 削除\" /></p>");
                out.println("</form>");
            }
            out.println("<form action=\"" + Urls.TOP + "\">");
            out.println("<p><input type=\"submit\" value=\"新規登録\" /></p>");
            out.println("</form>");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DatabaseHelper.commonClose(conn, stmt);
            HtmlHelper.endHttp(out);
        }
    }
}
