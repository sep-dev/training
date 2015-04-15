package main;

import helper.DatabaseHelper;
import helper.HtmlHelper;
import helper.ResultHelper;
import helper.Urls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	PreparedStatement stmt = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding(HtmlHelper.ENCORDING);
		response.setContentType("text/html; charset=" + HtmlHelper.ENCORDING);

		boolean result = deleteProcess(request);

		request.setAttribute(ResultHelper.TITLE, result ? "削除完了" : "削除失敗");
		request.setAttribute(ResultHelper.H1, result ?  "削除しました！！" : "削除に失敗しました");
		request.setAttribute(ResultHelper.URL, Urls.ADDRESS_LIST);
		request.setAttribute(ResultHelper.BUTTON_TEXT, "一覧表示");
		request.getRequestDispatcher("./jsp/" + Urls.RESULT).forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Urls.TOP).forward(request, response);
	}

	//削除処理
	protected boolean deleteProcess(HttpServletRequest request){
		conn = DatabaseHelper.getConnectionInstance();
		try {
			stmt = conn.prepareStatement("delete from " + DatabaseHelper.TABLE_NAME + " where id=?");
			stmt.setInt(1, Integer.parseInt(request.getParameter("id")));
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return true;
	}
}
