package main;

import helper.DatabaseHelper;
import helper.HtmlHelper;

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
		response.setCharacterEncoding(HtmlHelper.ENCORDING);
		response.setContentType("text/html; charset=" + HtmlHelper.ENCORDING);

		PrintWriter out = response.getWriter();

		HtmlHelper.firstHttp(out, "会員一覧", "./css/list.css");

		out.println("	<h1 class=\"title\">会員一覧</h1>");
		out.println("	<form method=\"POST\" action=\"./Update\">");
		out.println("	<table>");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DatabaseHelper.getConnectionInstance();
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME);
			while(result.next()){
				out.println("	<tr>");
				out.println("		<td><input type=\"radio\" name=\"id\" value=\"" + result.getString("id") + "\" required /></td>");
				out.println("		<td>氏名  ：  " + result.getString("name") + "</td>");
				out.println("		<td>住所  :  " + result.getString("address") + "</td>");
				out.println("		<td>電話番号  :  " + result.getString("tel") + "</td>");
				out.println("	</tr>");
			}
			out.println("	</table>");
			out.println("	<p><input type=\"submit\" value=\"更新 or 削除\" /></p>");
			out.println("	<p><input type=\"submit\" value=\"新規登録\" formaction=\"./\" formmethod=\"GET\" /></p>");
			out.println("</form>");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{

			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}

			HtmlHelper.endHttp(out);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
