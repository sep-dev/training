package main;

import helper.DatabaseHelper;
import helper.HtmlHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn = null;
	private PreparedStatement stmt = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=" + HtmlHelper.ENCORDING);
		response.setCharacterEncoding(HtmlHelper.ENCORDING);

		PrintWriter out = response.getWriter();

		HtmlHelper.firstHttp(out, "会員情報の登録", null);

		if(!checkValues(request)){
			out.println("<h1>登録失敗!!!</h1>");
			out.println("<h1>空欄を埋めてください</h1>");
			out.println("<form action=\"./\" >");
			out.println("<input type=\"submit\" value=\"新規登録\" />");
			out.println("</form>");
		}else{
			this.printConsole(request);
			try {
				conn = DatabaseHelper.getConnectionInstance();

				stmt = conn.prepareStatement("insert into " + DatabaseHelper.TABLE_NAME + " (name,address,tel) values (?,?,?)");
				stmt.setString(1, HtmlHelper.encording(request.getParameter("name")));
				stmt.setString(2, HtmlHelper.encording(request.getParameter("address")));
				stmt.setString(3, HtmlHelper.encording(request.getParameter("tel")));
				stmt.executeUpdate();
				stmt.close();
				conn.close();
				out.println("<h1>登録成功！</h1>");
				out.println("<form action=\"./AddressList\" />");
				out.println("<input type=\"submit\" value=\"一覧表示\" />");
				out.println("</form>");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		HtmlHelper.endHttp(out);
	}

	//入力値検査
	private boolean checkValues(HttpServletRequest request){
		if(request.getParameter("name").equals("")) return false;
		if(request.getParameter("address").equals("")) return false;
		if(request.getParameter("tel").equals("") || request.getParameter("tel").length() != 10) return false;
		return true;
	}

	//入力値出力（デバッグ用)
	private void printConsole(HttpServletRequest request){
		System.out.println(HtmlHelper.encording(request.getParameter("name")));
		System.out.println(HtmlHelper.encording(request.getParameter("address")));
		System.out.println(HtmlHelper.encording(request.getParameter("tel")));
	}

}
